package com.truck.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.CartMapper;
import com.truck.dao.ProductMapper;
import com.truck.dao.ShopMapper;
import com.truck.pojo.Cart;
import com.truck.pojo.Product;
import com.truck.pojo.Shop;
import com.truck.service.ICartService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.PropertiesUtil;
import com.truck.vo.CartProductVo;
import com.truck.vo.CartShopListVo;
import com.truck.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ShopMapper shopMapper;


    public ServerResponse<CartVo> list(Integer userId) {
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }


    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
        if (count == null || productId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Product product = productMapper.selectByPrimaryKey(productId);
        Shop shop = shopMapper.selectByAdminId(product.getAdminId());
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {
            //这个产品不在这个购物车里，需要新增一个产品记录
            Cart cartItem = new Cart();
            cartItem.setCartUserId(userId);
            cartItem.setShopId(shop.getShopId());
            cartItem.setAmount(count);
            cartItem.setCartProductId(productId);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartMapper.insert(cartItem);
        } else {
            //这个产品已经在购物车中相加产品数量
            count = cart.getAmount() + count;
            if (count > product.getProductStock()) {
                return ServerResponse.createByErrorMessage("产品库存不足,无法继续加入购物车");
            }
            cart.setAmount(count);
            if(count <=0){
                cartMapper.deleteByPrimaryKey(cart.getCartId());
            }else {
                cartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return this.list(userId);
    }

    public ServerResponse update(Integer userId, Integer count, Integer productId) {
        if (count == null || productId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        if (count < 1)
            return ServerResponse.createByErrorMessage("数量变化不能为负");
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart != null){
            Integer real= cart.getAmount()+count;
            if(real <=0){
                cartMapper.deleteByPrimaryKey(cart.getCartId());
            }else{
                cart.setAmount(count);
                cartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return this.list(userId);
    }


    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer checked, Integer productId) {
        cartMapper.checkedOrUncheckedProduct(userId, productId, checked);
        return this.list(userId);
    }


    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds) {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList))
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        cartMapper.deleteByUserIdProductIds(userId, productList);

        return this.list(userId);
    }


    public ServerResponse<Integer> getcartCount(Integer userId) {
        if (userId == null)
            return ServerResponse.createBySuccess(0);
        Integer count = cartMapper.selectCartProductCount(userId);
        if (count == null) {
            count =0;
        }
        return ServerResponse.createBySuccess(count);
    }

    public ServerResponse cleanCart(Integer userId) {
        if (userId == null)
            return ServerResponse.createByErrorMessage("请登录");
        int result =  cartMapper.deleteByUserId(userId);
        if (result >0) {
            return ServerResponse.createBySuccess("清空购物车成功");
        }
        return ServerResponse.createBySuccess("清空购物车失败");

    }

    private CartVo getCartVoLimit(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> cartLists = cartMapper.selectCartByUserId(userId);

        List<CartShopListVo> cartShopListVoList = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");

        if (CollectionUtils.isNotEmpty(cartLists)) {
            //查询shop信息（List<shop>）
            List<Shop> shopList = shopMapper.selectShopListByUserId(userId);
            for(Shop shopItem : shopList){
                List<Cart> cartList = cartMapper.selectCartByAdminId(shopItem.getAdminId(),userId);
                CartShopListVo cartShopListVo = new CartShopListVo();
                List<CartProductVo> cartProductVoList = Lists.newArrayList();
                BigDecimal shopTotalPrice = new BigDecimal("0");
                for (Cart cartItem : cartList) {

                    CartProductVo cartProductVo = new CartProductVo();
                    cartProductVo.setCartId(cartItem.getCartId());
                    cartProductVo.setCartProductId(cartItem.getCartProductId());
                    cartProductVo.setCartUserId(cartItem.getCartUserId());
                    Product product = productMapper.selectByPrimaryKey(cartItem.getCartProductId());
                    if (product == null) {
                        cartMapper.deleteByPrimaryKey(cartItem.getCartProductId());
                    } else{
                        cartProductVo.setProductMainImage(product.getProductFirstimg());
                        cartProductVo.setProductName(product.getProductTitle());
                        cartProductVo.setProductSubtitle(product.getProductSubtitle());
                        cartProductVo.setProductPrice(product.getProductPrice());
                        cartProductVo.setProductStock(product.getProductStock());
                        cartProductVo.setProductStatus(product.getProductStatus());
                        int buyLimitCount = 0;
                        if (product.getProductStock() >= cartItem.getAmount()) {
                            //库存充足的时候
                            buyLimitCount = cartItem.getAmount();
                            cartProductVo.setLimitAmount(Const.Cart.LIMIT_NUM_SUCCESS);
                        } else {
                            buyLimitCount = product.getProductStock();
                            cartProductVo.setLimitAmount(Const.Cart.LIMIT_NUM_FAIL);

                            //购物车中更新有效库存
                            Cart cartForAmount = new Cart();
                            cartForAmount.setCartId(cartItem.getCartId());
                            cartForAmount.setAmount(product.getProductStock());

                            cartMapper.updateByPrimaryKeySelective(cartForAmount);
                        }
                        cartProductVo.setProductAmount(buyLimitCount);
                        //计算总价
                        cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getProductPrice().doubleValue(), cartProductVo.getProductAmount()));
                        cartProductVo.setProductChecked(cartItem.getChecked());

                        if (cartItem.getChecked() == Const.Cart.CHECKED)
                            //如果已经勾选，增加到整个的购物车总价中
                            cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                        shopTotalPrice = BigDecimalUtil.add(shopTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                        cartProductVoList.add(cartProductVo);
                    }
                }
                cartShopListVo.setShopId(shopItem.getShopId());
                cartShopListVo.setAdminId(shopItem.getAdminId());
                cartShopListVo.setShopName(shopItem.getShopName());
                cartShopListVo.setShopHeadimg(shopItem.getShopHeadimg());
                cartShopListVo.setCartProductVoList(cartProductVoList);
                cartShopListVo.setShopTotalPrice(shopTotalPrice);
                cartShopListVoList.add(cartShopListVo);
            }
        }
        cartVo.setCartTotalprice(cartTotalPrice);
        cartVo.setCartShopListVoList(cartShopListVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        return cartVo;
    }

    private boolean getAllCheckedStatus(Integer userId) {
        if (userId == null)
            return false;
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }
}
