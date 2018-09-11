package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.*;
import com.truck.util.BigDecimalUtil;
import com.truck.util.DateTimeUtil;
import com.truck.vo.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    private static  final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ShippingMapper shippingMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private IInfoService infoService;
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IContactService iContactService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StockAlterationMapper stockAlterationMapper;
    @Autowired
    private BalanceAlterationMapper balanceAlterationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private BuyStatusMapper buyStatusMapper;
    @Autowired
    private OrderBuyStatusMapper orderBuyStatusMapper;
    @Autowired
    private PayStatusMapper payStatusMapper;
    @Autowired
    private OrderPayStatusMapper orderPayStatusMapper;

    /**
     * 创建订单
     *
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse createOrder(Integer userId, Integer shippingId, Integer paymentType) {
        UserInfo checkUserInfo = userInfoMapper.selectCountByUserId(userId);
        if(checkUserInfo == null){
            return ServerResponse.createByErrorMessage("请完善个人信息后重试");
        }
        //分组购物车按照shopId  提取shop
        List<Shop> shopList = shopMapper.selectShopIdByGroupCart(userId);
        if(shopList.size() == 0){
            return ServerResponse.createByErrorMessage("请选择购物车商品");
        }
        List<OrderVo> orderVoList = Lists.newArrayList();
        for(Shop shopItem : shopList){
            //根据shopid查询购物车，并生成订单 一个店铺一个订单 一个订单多个详情
            //从购物车获取数据
            UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
            Integer id = shopItem.getShopId();
            List<Cart> cartList = cartMapper.selectCheckedCartByUserIdShopId(userId, shopItem.getShopId());
            ServerResponse<List<OrderDetail>> serverResponse = this.getCartOrderDetail(userId, cartList);
            if (!serverResponse.isSuccess()) {
                //false 返回信息
                return serverResponse;
            }
            List<OrderDetail> orderDetailList = (List<OrderDetail>) serverResponse.getData();
            if (CollectionUtils.isEmpty(orderDetailList)) {
                return serverResponse.createByErrorMessage("数据提取异常");
            }
            BigDecimal payment = this.getOrderTotalPrice(orderDetailList);
            //校验余额
            Order order = new Order();
            if(org.springframework.util.StringUtils.isEmpty(paymentType)){
                order.setPaymentType(Const.PaymentTypeEnum.ARRIVE_PAY.getCode());
             }else{
                order.setPaymentType(paymentType);
             }
            if(Const.PaymentTypeEnum.ONLINE_PAY.getCode() == order.getPaymentType()){
                int resultCom = userInfo.getAccountBalance().compareTo(payment);
                if(resultCom < 0){
                    return ServerResponse.createByErrorMessage("账户余额不足，请充值");
                }
            }
            //生成订单
            order = this.assembleOrder(userId, shopItem.getShopId(), payment, order.getPaymentType());
            if (order == null) {
                return serverResponse.createByErrorMessage("生成订单错误");
            }
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setShippingId(shippingId);
                orderDetail.setOrderDetailStatus(Const.OrderDetailStatusEnum.NO_SHIPPING.getCode());
            }
            //刷新累计采购额
            UserInfo checkUser = userInfoMapper.selectCountByUserId(userId);
            if(checkUser != null){
                List<Order> orderList = orderMapper.selectPurchaseByUserId(userId);
                BigDecimal cumulativePurchase = new BigDecimal("0");
                if(orderList != null){
                    for(Order orderItem : orderList){
                        cumulativePurchase = BigDecimalUtil.add(cumulativePurchase.doubleValue(), orderItem.getPaymentPrice().doubleValue());
                    }
                }
                checkUser.setCumulativePurchase(payment);
                userInfoMapper.updateByPrimaryKeySelective(checkUser);
            }
            //mybatis 批量插入
            orderDetailMapper.batchInsert(orderDetailList);
            //生成成功，减少产品的库存
            this.reduceProductStock(orderDetailList);
            //生成成功，减少当前用户的余额
            if(Const.PaymentTypeEnum.ONLINE_PAY.getCode() == order.getPaymentType()){
                BigDecimal balance = BigDecimalUtil.sub(userInfo.getAccountBalance().doubleValue(),payment.doubleValue());
                userInfo.setAccountBalance(balance);
                int subResult = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                if(subResult > 0){
                    order.setOrderStatus(Const.OrderStatusEnum.PAID.getCode());
                    order.setPaymentTime(new Date());
                    orderMapper.updateByPrimaryKeySelective(order);
                    BalanceAlteration balanceAlteration = new BalanceAlteration();
                    balanceAlteration.setUserInfoId(userInfo.getCustomerId());
                    balanceAlteration.setAlterationMoney(payment);
                    balanceAlteration.setAlterationStatus(Const.AlterationStatusEnum.EXPENDITURE.getCode());
                    balanceAlteration.setAlterationReason(Const.AlterationReasonEnum.PLACE_ORDER.getCode());
                    balanceAlteration.setOrderId(order.getOrderId());
                    int resultCount = balanceAlterationMapper.insertSelective(balanceAlteration);
                    if(resultCount == 0){
                        return ServerResponse.createByErrorMessage("变动记录失败");
                    }
                }else{
                    return ServerResponse.createByErrorMessage("余额扣除失败");
                }
            }
            //清空一下购物车
            this.cleanCart(cartList);
            order = orderMapper.selectByPrimaryKey(order.getOrderId());
            OrderVo orderVo = assembleOrderVo(order);
            orderVoList.add(orderVo);
        }
        return ServerResponse.createBySuccess(orderVoList);
    }

    private OrderVo assembleOrderVo(Order order) {
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(order.getOrderId());
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setUserId(order.getUserId());
        orderVo.setShopId(order.getShopId());
        orderVo.setOrderPrice(order.getOrderPrice());
        orderVo.setPaymentPrice(order.getPaymentPrice());
        orderVo.setPaymentType(order.getPaymentType());
//        orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());
        orderVo.setFreight(order.getFreight());
        List<BuyStatus> buyStatuses = buyStatusMapper.selectByOrderId(order.getOrderId());
        if(buyStatuses.size()>0)
            orderVo.setStatusDesc(buyStatuses.get(0).getStatus());
        List<PayStatus> payStatuses = payStatusMapper.selectByOrderId(order.getOrderId());
        if(payStatuses.size()>0)
            orderVo.setPaymentTypeDesc(payStatuses.get(0).getStatus());
        orderVo.setOrderStatus(order.getOrderStatus());
//        orderVo.setStatusDesc(Const.OrderStatusEnum.codeOf(order.getOrderStatus()).getValue());
        //货到付款已付款
        orderVo.setTotalStatusDesc(orderVo.getPaymentTypeDesc()+orderVo.getStatusDesc());
        orderVo.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
        orderVo.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
        orderVo.setUpdateTime(DateTimeUtil.dateToStr(order.getUpdateTime()));
        //存入店铺基本信息 shopDetailVo
        Shop shop = shopMapper.selectByPrimaryKey(order.getShopId());
        ShopDetailVo shopDetailVo = this.assembleShopDetail(shop);
        orderVo.setShopDetailVo(shopDetailVo);
        //orderDetailList 订单详情列表
        List<OrderDetail> orderDetailList = orderDetailMapper.selectListByOrderId(order.getOrderId());
        List<OrderDetailVo> orderDetailVoList = Lists.newArrayList();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailVo orderDetailVo = assembleOrderDetailVo(orderDetail);

            List<Warehouse> warehouseList = warehouseMapper.selectByShopId(order.getShopId());
            if(warehouseList != null){
                List<WarehouseVo> warehouseVoList = Lists.newArrayList();
                for (Warehouse warehouse : warehouseList) {
                    WarehouseVo warehouseVo = iWarehouseService.assembleWarehouseVo(warehouse,orderDetail.getProductId());
                    warehouseVoList.add(warehouseVo);
                }
                orderDetailVo.setWarehouseVoList(warehouseVoList);
            }

            List<Contact> contactList = contactMapper.selectByShopId(order.getShopId());
            if(contactList != null){
                List<ContactVo> contactVoList = Lists.newArrayList();
                for (Contact contact : contactList) {
                    ContactVo contactVo = iContactService.assembleContactVo(contact);
                    contactVoList.add(contactVo);
                }
                orderDetailVo.setContactVoList(contactVoList);
            }
            orderDetailVoList.add(orderDetailVo);
        }
        if(orderDetailVoList.size() > 0){
            orderVo.setShippingVo(orderDetailVoList.get(0).getShippingVo());
        }
        orderVo.setOrderDetailVoList(orderDetailVoList);

        List<Info> infos = infoMapper.selectByOrderId(order.getOrderId());
        List<InfoVo> infoVos = Lists.newArrayList();
        for (Info info : infos) {
            InfoVo infoVo = new InfoVo();
            infoVo = infoService.assembleInfoVo(info);
            infoVos.add(infoVo);
        }
        orderVo.setInfoVoList(infoVos);
        return orderVo;
    }

    private ShopDetailVo assembleShopDetail(Shop shop){
        ShopDetailVo shopDetailVo = new ShopDetailVo();
        shopDetailVo.setShopId(shop.getShopId());
        shopDetailVo.setAdminId(shop.getAdminId());
        shopDetailVo.setShopName(shop.getShopName());
        shopDetailVo.setShopDesc(shop.getShopDesc());
        shopDetailVo.setShopEmail(shop.getShopEmail());
        shopDetailVo.setShopPhone(shop.getShopPhone());
        shopDetailVo.setShopAddress(shop.getShopAddress());
        shopDetailVo.setShopHeadimg(shop.getShopHeadimg());
        shopDetailVo.setShopFirstimg(shop.getShopFirstimg());
        List<String> subimgList = Lists.newArrayList();
        if(StringUtils.isNotBlank(shop.getShopSubimg())){
            String[] subImageArray = shop.getShopSubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }
        shopDetailVo.setShopSubimg(subimgList);
        shopDetailVo.setCreateTime(DateTimeUtil.dateToStr(shop.getCreateTime()));
        shopDetailVo.setUpdateTime(DateTimeUtil.dateToStr(shop.getUpdateTime()));
        shopDetailVo.setShopAccount(shop.getShopAccount());
        shopDetailVo.setShopTaxCard(shop.getShopTaxCard());
        shopDetailVo.setShopSppkp(shop.getShopSppkp());
        shopDetailVo.setShopLicence(shop.getShopLicence());
        shopDetailVo.setShopProxyCertificate(shop.getShopProxyCertificate());
        shopDetailVo.setShopStatus(shop.getShopStatus());
        return shopDetailVo;
    }

    private OrderDetailVo assembleOrderDetailVo(OrderDetail orderDetail) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrderDetailId(orderDetail.getOrderDetailId());
        orderDetailVo.setUserId(orderDetail.getUserId());
        orderDetailVo.setOrderId(orderDetail.getOrderId());
        orderDetailVo.setProductId(orderDetail.getProductId());
        orderDetailVo.setProductNo(orderDetail.getProductNo());
        orderDetailVo.setProductName(orderDetail.getProductName());
        orderDetailVo.setProductImage(orderDetail.getProductImage());
        orderDetailVo.setCurrentUnitPrice(orderDetail.getCurrentUnitPrice());
        orderDetailVo.setQuantity(orderDetail.getQuantity());
        orderDetailVo.setTotalPrice(orderDetail.getTotalPrice());
        orderDetailVo.setCreateTime(DateTimeUtil.dateToStr(orderDetail.getCreateTime()));
        orderDetailVo.setOrderDetailStatus(orderDetail.getOrderDetailStatus());
        if(!org.springframework.util.StringUtils.isEmpty(orderDetail.getShippingId())){
            Shipping shipping = shippingMapper.selectByPrimaryKey(orderDetail.getShippingId());
            ShippingVo shippingVo = assembleShippingVo(shipping);
            orderDetailVo.setShippingVo(shippingVo);
        }
        orderDetailVo.setStatusDesc(Const.OrderDetailStatusEnum.codeOf(orderDetail.getOrderDetailStatus()).getValue());
        return orderDetailVo;

    }

    private ShippingVo assembleShippingVo(Shipping shipping) {
        ShippingVo shippingVo = new ShippingVo();
        shippingVo.setReceiverName(shipping.getReceiverName());
        shippingVo.setReceiverAddress(shipping.getReceiverAddress());
        shippingVo.setreceiverProvince(shipping.getReceiverProvince());
        shippingVo.setReceiverCity(shipping.getReceiverCity());
        shippingVo.setReceiverDistrict(shipping.getReceiverDistrict());
        shippingVo.setReceiverMobile(shipping.getReceiverMobile());
        shippingVo.setReceiverZip(shipping.getReceiverZip());
        shippingVo.setReceiverPhone(shipping.getReceiverPhone());
        return shippingVo;
    }

    /**
     * 清空购物车
     *
     * @param cartList
     */
    private void cleanCart(List<Cart> cartList) {
        for (Cart cart : cartList) {
            cartMapper.deleteByPrimaryKey(cart.getCartId());
        }
    }

    /**
     * 订单生成成功，减少产品库存
     *
     * @param orderDetailList
     */
    private void reduceProductStock(List<OrderDetail> orderDetailList) {
        for (OrderDetail orderDetail : orderDetailList) {
            Product product = productMapper.selectByPrimaryKey(orderDetail.getProductId());
            product.setProductStock(product.getProductStock() - orderDetail.getQuantity());
            if(product.getProductStock()==0){
                product.setStockStatus(Const.ProductStockStatusEnum.STOCK_ZERO.getCode());
            }else{
                if(product.getProductStock()>product.getPicketLine()){
                    product.setStockStatus(Const.ProductStockStatusEnum.STOCK_NORMAL.getCode());
                }else{
                    product.setStockStatus(Const.ProductStockStatusEnum.STOCK_LIMIT.getCode());
                }
            }
            productMapper.updateByPrimaryKeySelective(product);
        }
    }


    private Order assembleOrder(Integer userId, Integer shopId, BigDecimal payment, Integer paymentType) {
        Order order = new Order();
        String orderNo = String.valueOf(this.generateOrderNo());
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setShopId(shopId);
        order.setOrderPrice(payment);
        order.setPaymentPrice(payment);
        order.setPaymentType(paymentType);
        order.setOrderStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        //发货时间
        //付款时间
        int rowCount = orderMapper.insertSelective(order);
        if (rowCount > 0) {
            return orderMapper.selectByPrimaryKey(order.getOrderId());
        }
        return null;
    }

    /**
     * 订单编号的生成
     *
     * @return
     */
    private long generateOrderNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }

    /**
     * 计算金额
     *
     * @param orderDetailList
     * @return
     */
    private BigDecimal getOrderTotalPrice(List<OrderDetail> orderDetailList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderDetail orderDetail : orderDetailList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderDetail.getTotalPrice().doubleValue());
        }
        return payment;
    }

    private ServerResponse<List<OrderDetail>> getCartOrderDetail(Integer userId, List<Cart> cartList) {
        List<OrderDetail> orderDetailList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(cartList)) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        //校验购物车的数据，包括产品的状态和数量
        for (Cart cartItem : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productMapper.selectByPrimaryKey(cartItem.getCartProductId());
            if (Const.ProductStatusEnum.ON_SALE.getCode() != product.getProductStatus()) {
                return ServerResponse.createByErrorMessage("产品" + product.getProductTitle() + "不是在线售卖状态");
            }
            //校验库存
            if (cartItem.getAmount() > product.getProductStock()) {
                return ServerResponse.createByErrorMessage("产品" + product.getProductTitle() + "库存不足");
            }
            orderDetail.setUserId(userId);
            orderDetail.setProductId(product.getProductId());
            orderDetail.setProductName(product.getProductTitle());
            orderDetail.setProductImage(product.getProductFirstimg());
            orderDetail.setCurrentUnitPrice(product.getProductPrice());
            orderDetail.setQuantity(cartItem.getAmount());
            orderDetail.setTotalPrice(BigDecimalUtil.mul(product.getProductPrice().doubleValue(), cartItem.getAmount()));
            orderDetailList.add(orderDetail);
        }
        return ServerResponse.createBySuccess(orderDetailList);
    }


    /**
     * 取消订单
     *
     * @param userId
     * @return
     */
    public ServerResponse<String> cancel(Integer userId,Integer orderDetailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByUserIdAndOrderDetailId(userId,orderDetailId);
        if (orderDetail == null) {
            return ServerResponse.createByErrorMessage("该用户没有该商品");
        }
        if (orderDetail.getOrderDetailStatus() != Const.OrderDetailStatusEnum.NO_SHIPPING.getCode()) {
            return ServerResponse.createByErrorMessage("该订单商品无法取消");
        }
        orderDetail.setOrderDetailStatus(Const.OrderDetailStatusEnum.CANCELED.getCode());
        int row = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
        if (row > 0) {
            int resultCount = orderDetailMapper.selectCountUnCanceled(orderDetail.getOrderId());
            Order order = orderMapper.selectByPrimaryKey(orderDetail.getOrderId());
            if(resultCount == 0){
                order.setOrderStatus(Const.OrderStatusEnum.CANCELED.getCode());
            }
            order.setPaymentPrice(BigDecimalUtil.sub(order.getPaymentPrice().doubleValue(),orderDetail.getTotalPrice().doubleValue()));
            orderMapper.updateByPrimaryKeySelective(order);
            this.backProductStock(orderDetail);
            //刷新累计采购额
            UserInfo checkUser = userInfoMapper.selectCountByUserId(userId);
            if(checkUser != null){
                List<Order> orderList = orderMapper.selectPurchaseByUserId(userId);
                BigDecimal payment = new BigDecimal("0");
                if(orderList != null){
                    for(Order orderItem : orderList){
                        payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getPaymentPrice().doubleValue());
                    }
                }
                checkUser.setCumulativePurchase(payment);
                userInfoMapper.updateByPrimaryKeySelective(checkUser);
            }
            //返还余额  变动记录待定记录订单id  实际变动金额记录详情的金额
            if(order.getPaymentType() == Const.PaymentTypeEnum.ONLINE_PAY.getCode()){
                UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
                if(userInfo == null){
                    return ServerResponse.createByErrorMessage("该用户信息不完善，请完善信息");
                }
                BigDecimal balance = BigDecimalUtil.add(userInfo.getAccountBalance().doubleValue(),orderDetail.getTotalPrice().doubleValue());
                userInfo.setAccountBalance(balance);
                int subResult = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                if(subResult > 0){
                    BalanceAlteration balanceAlteration = new BalanceAlteration();
                    balanceAlteration.setUserInfoId(userInfo.getCustomerId());
                    balanceAlteration.setAlterationMoney(orderDetail.getTotalPrice());
                    balanceAlteration.setAlterationStatus(Const.AlterationStatusEnum.INCOME.getCode());
                    balanceAlteration.setAlterationReason(Const.AlterationReasonEnum.BACK_ORDER.getCode());
                    balanceAlteration.setOrderId(order.getOrderId());
                    resultCount = balanceAlterationMapper.insertSelective(balanceAlteration);
                    if(resultCount == 0){
                        return ServerResponse.createByErrorMessage("变动记录失败");
                    }
                }else{
                    return ServerResponse.createByErrorMessage("余额返还失败");
                }
            }
            return ServerResponse.createBySuccess("订单取消成功");
        }
        return ServerResponse.createByErrorMessage("订单取消失败");
    }

    /**
     * 取消订单，返回库存
     *
     * @param orderDetail
     */
    private void backProductStock(OrderDetail orderDetail) {
        Product product = productMapper.selectByPrimaryKey(orderDetail.getProductId());
        product.setProductStock(product.getProductStock() + orderDetail.getQuantity());
        if(product.getProductStock()==0){
            product.setStockStatus(Const.ProductStockStatusEnum.STOCK_ZERO.getCode());
        }else{
            if(product.getProductStock()>product.getPicketLine()){
                product.setStockStatus(Const.ProductStockStatusEnum.STOCK_NORMAL.getCode());
            }else{
                product.setStockStatus(Const.ProductStockStatusEnum.STOCK_LIMIT.getCode());
            }
        }
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * 预览订单购物车商品
     *
     * @param userId
     * @return
     */
    public ServerResponse getOrderCartProduct(Integer userId) {
        OrderProductVo orderProductVo = new OrderProductVo();
        List<Cart> cartList = cartMapper.selectCheckedCartByUserId(userId);
        ServerResponse serverResponse = this.getCartOrderDetail(userId, cartList);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        List<OrderDetail> orderDetailList = (List<OrderDetail>) serverResponse.getData();
        List<OrderDetailVo> orderDetailVoList = Lists.newArrayList();
        BigDecimal payment = new BigDecimal("0");
        for (OrderDetail orderDetail : orderDetailList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderDetail.getTotalPrice().doubleValue());
            orderDetailVoList.add(assembleOrderDetailVo(orderDetail));
        }
        orderProductVo.setProductTotalPrice(payment);
        orderProductVo.setOrderDetailList(orderDetailList);
        return serverResponse.createBySuccess(orderProductVo);
    }

    /**
     * 顾客根据订单编号获取订单详情
     *
     * @param userId
     * @param orderNo
     * @return
     */
    public ServerResponse getOrderDetail(Integer userId, String orderNo) {
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (order != null) {
            OrderVo orderVo = assembleOrderVo(order);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByErrorMessage("没有找到该订单");
    }

    /**
     * 获取当前用户所有的订单信息
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectByUserId(userId);
        List<OrderVo> orderVoList = assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private List<OrderVo> assembleOrderVoList(List<Order> orderList) {
        List<OrderVo> orderVoList = Lists.newArrayList();
        for (Order order : orderList) {
            OrderVo orderVo = assembleOrderVo(order);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    //backend

    /**
     * 超级管理员获取所有订单信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAllOrder();
        List<OrderVo> orderVoList = this.assembleOrderVoByList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 店家获取所有订单信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageList(Integer adminId , int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Shop shop = shopMapper.selectByAdminId(adminId);
        List<Order> orderList = orderMapper.selectOrderByShopId(shop.getShopId());
        List<OrderVo> orderVoList = this.assembleOrderVoByList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     *
     *
     * @return
     */
    public List<OrderVo> assembleOrderVoByList( List<Order> orderList) {
        List<OrderVo> orderVos = Lists.newArrayList();
        for (Order order : orderList) {
            Shop shop = shopMapper.selectByPrimaryKey(order.getShopId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailByShop(order.getOrderId());
            List<OrderDetailVo> orderDetailVoList = Lists.newArrayList();
            BigDecimal shopTotalPrice = new BigDecimal("0");
            for (OrderDetail orderDetail : orderDetailList) {
                OrderDetailVo orderDetailVo = assembleOrderDetailVo(orderDetail);
                List<Warehouse> warehouseList = warehouseMapper.selectByShopId(order.getShopId());
                if(warehouseList != null){
                    List<WarehouseVo> warehouseVoList = Lists.newArrayList();
                    for (Warehouse warehouse : warehouseList) {
                        WarehouseVo warehouseVo = iWarehouseService.assembleWarehouseVo(warehouse,orderDetail.getProductId());
                        warehouseVoList.add(warehouseVo);
                    }
                    orderDetailVo.setWarehouseVoList(warehouseVoList);
                }

                List<Contact> contactList = contactMapper.selectByShopId(order.getShopId());
                if(contactList != null){
                    List<ContactVo> contactVoList = Lists.newArrayList();
                    for (Contact contact : contactList) {
                        ContactVo contactVo = iContactService.assembleContactVo(contact);
                        contactVoList.add(contactVo);
                    }
                    orderDetailVo.setContactVoList(contactVoList);
                }
                orderDetailVoList.add(orderDetailVo);
            }
            OrderVo orderVo = new OrderVo();
            orderVo.setOrderId(order.getOrderId());
            orderVo.setOrderNo(order.getOrderNo());
            orderVo.setUserId(order.getUserId());
            orderVo.setShopId(order.getShopId());

            List<BuyStatus> buyStatuses = buyStatusMapper.selectByOrderId(order.getOrderId());
            if(buyStatuses.size()>0)
                orderVo.setStatusDesc(buyStatuses.get(0).getStatus());
            List<PayStatus> payStatuses = payStatusMapper.selectByOrderId(order.getOrderId());
            if(payStatuses.size()>0)
                orderVo.setPaymentTypeDesc(payStatuses.get(0).getStatus());
            orderVo.setPaymentType(order.getPaymentType());
            orderVo.setOrderPrice(order.getOrderPrice());
            orderVo.setPaymentPrice(order.getPaymentPrice());
//            orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());
            orderVo.setFreight(order.getFreight());
            orderVo.setOrderStatus(order.getOrderStatus());
//            orderVo.setStatusDesc(Const.OrderStatusEnum.codeOf(order.getOrderStatus()).getValue());
            //todo
            orderVo.setTotalStatusDesc(orderVo.getPaymentTypeDesc()+orderVo.getStatusDesc());
            orderVo.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
            orderVo.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
            orderVo.setUpdateTime(DateTimeUtil.dateToStr(order.getUpdateTime()));
            ShopDetailVo shopDetailVo = this.assembleShopDetail(shop);
            orderVo.setShopDetailVo(shopDetailVo);
            orderVo.setOrderDetailVoList(orderDetailVoList);

            List<Info> infos = infoMapper.selectByOrderId(order.getOrderId());
            List<InfoVo> infoVos = Lists.newArrayList();
            for (Info info : infos) {
                InfoVo infoVo = new InfoVo();
                infoVo = infoService.assembleInfoVo(info);
                infoVos.add(infoVo);
            }
            orderVo.setInfoVoList(infoVos);

            if(orderDetailVoList.size() > 0){
                orderVo.setShippingVo(orderDetailVoList.get(0).getShippingVo());
            }
            orderVos.add(orderVo);
        }
        return orderVos;
    }

    /**
     * 模糊查询订单（订单编号）
     *
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageSearch(String orderNo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(orderNo)) {
            orderNo = new StringBuilder().append("%").append(orderNo).append("%").toString();
        }
        List<Order> orderList = orderMapper.searchByOrderNo(orderNo);
        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 店家模糊查询订单（订单编号）
     *
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> manageSearchShop(Integer adminId, String orderNo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(orderNo)) {
            orderNo = new StringBuilder().append("%").append(orderNo).append("%").toString();
        }
        Shop shop = shopMapper.selectByAdminId(adminId);
        List<Order> orderList = orderMapper.selectOrderByShopIdAndOrderNo(shop.getShopId(),orderNo);
        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 配货
     *
     * @return
     */
    public ServerResponse<String> manageSendGoods(Integer orderDetailId, String stockSendList ) {
        logger.info("传过来的参数:{}",stockSendList);
        JSONArray json = JSONArray.fromObject(stockSendList);
        List<StockSend> stockSends = JSONArray.toList(json,StockSend.class);

        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderDetailId);
        if(orderDetail == null){
            return ServerResponse.createByErrorMessage("该订单商品不存在");
        }
        if(orderDetail.getOrderDetailStatus() != Const.OrderDetailStatusEnum.NO_SHIPPING.getCode()){
            return ServerResponse.createByErrorMessage("该订单商品无法配货");
        }
        int num =0;
        List<Stock> stocks = Lists.newArrayList();
        for (StockSend stockSend : stockSends) {
            Stock stock = stockMapper.selectByStockIdAndProductId(stockSend.getStockId(),orderDetail.getProductId());
            if(stock == null){
                return ServerResponse.createByErrorMessage("该商品没有该库存");
            }
            if (stockSend.getStockNum() > stock.getStockNum()) {
                return ServerResponse.createByErrorMessage(stockSend.getStockId()+"批次产品库存不足");
            }
            num+=stockSend.getStockNum();
            stocks.add(stock);
        }
        if (orderDetail.getQuantity() != num) {
            return ServerResponse.createByErrorMessage("产品配货数量与订单不符！！！");
        }
        int resultCount = this.reduceProductWarehouseStock(orderDetail,stockSends);
        if(resultCount > 0){
            for (StockSend stockSend : stockSends) {
                StockAlteration stockAlteration = new StockAlteration();
                stockAlteration.setStockId(stockSend.getStockId());
                stockAlteration.setOrderDetailId(orderDetailId);
                stockAlteration.setAlterationNum(stockSend.getStockNum());
                stockAlteration.setAlterationStatus(Const.StockAlterationStatusEnum.OUTSTOCK.getCode());
                stockAlteration.setAlterationReason(Const.StockAlterationReasonEnum.SALE_PRODUCT.getCode());
                stockAlteration.setAlterationProductPrice(orderDetail.getCurrentUnitPrice());
                stockAlterationMapper.insertSelective(stockAlteration);
            }
            orderDetail.setOrderDetailStatus(Const.OrderDetailStatusEnum.DISTRIBUTION.getCode());
            resultCount = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
            if(resultCount > 0){
                return ServerResponse.createBySuccess("已完成配货");
            }else{
                return ServerResponse.createByErrorMessage("详情修改失败");
            }
        }else{
            return ServerResponse.createByErrorMessage("配货失败");
        }
    }

    /**
     * 卖家发货，减少产品仓库库存
     *
     */
    private int reduceProductWarehouseStock(OrderDetail orderDetail , List<StockSend> stockSends) {
        for (StockSend stockSend : stockSends) {
            Stock stock = stockMapper.selectByStockIdAndProductId(stockSend.getStockId(),orderDetail.getProductId());
            stock.setStockNum(stock.getStockNum() - stockSend.getStockNum());
            int count = stockMapper.updateByPrimaryKeySelective(stock);
            if(count<=0){
                return count;
            }
        }
        return 1;
    }



    //查询客户信息

    public ServerResponse getShopCustomers(Integer adminId){
        Shop shop = shopMapper.selectByAdminId(adminId);
        if(shop == null){
            return ServerResponse.createByErrorMessage("数据异常，当前店铺不存在");
        }
        List<User> userList = userMapper.selectUserByShopId(shop.getShopId());
        if(userList.size() == 0){
            return ServerResponse.createByErrorMessage("没有客户在本商店购买过东西");
        }
        List<UserVo> userVoList = Lists.newArrayList();
        for(User userItem : userList){
            UserVo userVo = this.assembleUserVo(userItem);
            userVoList.add(userVo);
        }
        return ServerResponse.createBySuccess(userVoList);
    }

    public ServerResponse getCustomerOrders(Integer userId,Integer adminId){
        Shop shop = shopMapper.selectByAdminId(adminId);
        List<Order> orderList = orderMapper.selectOrderByUserIdAndShopId(userId,shop.getShopId());
        if(orderList.size() == 0){
            return ServerResponse.createByErrorMessage("该客户在本商店没有下过单");
        }
        List<OrderVo> orderVoList = Lists.newArrayList();
        orderVoList = assembleOrderVoByList(orderList);
        return ServerResponse.createBySuccess(orderVoList);
    }

    public ServerResponse getCustomers(){
        List<User> userList = userMapper.selectAllUser();
        if(userList.size() == 0){
            return ServerResponse.createByErrorMessage("没有客户");
        }
        List<UserVo> userVoList = Lists.newArrayList();
        for(User userItem : userList){
            UserVo userVo = this.assembleUserVo(userItem);
            userVoList.add(userVo);
        }
        return ServerResponse.createBySuccess(userVoList);
    }

    public ServerResponse getCustomerOrder(Integer userId){
        List<Order> orderList = orderMapper.selectByUserId(userId);
        if(orderList.size() == 0){
            return ServerResponse.createByErrorMessage("该客户在本商店没有下过单");
        }
        List<OrderVo> orderVoList = Lists.newArrayList();
        orderVoList = assembleOrderVoByList(orderList);
        return ServerResponse.createBySuccess(orderVoList);
    }


    public UserVo assembleUserVo(User user){
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserName(user.getUserName());
        userVo.setUserEmail(user.getEmail());
        userVo.setUserPhone(user.getPhone());
        UserInfo userInfo = userInfoMapper.selectCountByUserId(user.getUserId());
        UserInfoVo userInfoVo = iUserInfoService.assembleUserInfoVo(userInfo);
        userVo.setUserInfoVo(userInfoVo);
        return userVo;
    }
}
