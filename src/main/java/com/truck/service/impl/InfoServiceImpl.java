package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.InfoMapper;
import com.truck.dao.OrderDetailMapper;
import com.truck.dao.OrderMapper;
import com.truck.pojo.Info;
import com.truck.pojo.Order;
import com.truck.pojo.OrderDetail;
import com.truck.service.IInfoService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.InfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by geely
 */
@Service("iInfoService")
public class InfoServiceImpl implements IInfoService {


    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public ServerResponse add(Integer adminId, InfoVo infoVo) {
        if(infoVo.getOrderId() ==null){
            return ServerResponse.createByErrorMessage("请选择订单");
        }
        List<Info> infos1 = infoMapper.selectByOrderId(infoVo.getOrderId());
        if(infos1.size()>0){
            Info info = assembleInfo(infoVo);
            info.setAdminId(adminId);
            int count;
            info.setLogisticsNo(infos1.get(0).getLogisticsNo());
            if(StringUtils.isNotBlank(infoVo.getDeliveryTime())){
                info.setDeliveryTime(DateTimeUtil.strToDate(infoVo.getDeliveryTime()));
                count = infoMapper.insert(info);
            }else{
                count=  infoMapper.insertSelective(info);
            }
            if (count > 0) {
                return ServerResponse.createBySuccess("增加物流信息成功");
            }
            return ServerResponse.createByErrorMessage("增加物流信息失败");
        }

            String logisticsNo = String.valueOf(this.generateLogisticsNo());
            infoVo.setLogisticsNo(logisticsNo);
            infoVo.setAdminId(adminId);
            Info info = assembleInfo(infoVo);

            int rowCount = 0;
            if (StringUtils.isNotBlank(infoVo.getDeliveryTime())) {
                info.setDeliveryTime(DateTimeUtil.strToDate(infoVo.getDeliveryTime()));
                rowCount = infoMapper.insert(info);
            } else {
                rowCount = infoMapper.insertSelective(info);
            }
            if (rowCount > 0) {
                    Map mapResult = Maps.newHashMap();
                    mapResult.put("logisticsId", info.getLogisticsId());
                    mapResult.put("logisticsNo", info.getLogisticsNo());
                    return ServerResponse.createBySuccess("新建物流信息成功，发货成功", mapResult);
            } else {
                return ServerResponse.createByErrorMessage("新建物流信息失败");
            }
    }

//    public ServerResponse add(Integer adminId, InfoVo infoVo) {
//        Order order = orderMapper.selectByPrimaryKey(infoVo.getOrderId());
//        List<Info> infos1 = infoMapper.selectByOrderId(infoVo.getOrderId());
//        if(infos1.size()>0){
//            Info info = assembleInfo(infoVo);
//            info.setAdminId(adminId);
//            int count;
//            info.setLogisticsNo(infos1.get(0).getLogisticsNo());
//            if(StringUtils.isNotBlank(infoVo.getDeliveryTime())){
//                info.setDeliveryTime(DateTimeUtil.strToDate(infoVo.getDeliveryTime()));
//                count = infoMapper.insert(info);
//            }else{
//                count=  infoMapper.insertSelective(info);
//            }
//            if (count > 0) {
//                return ServerResponse.createBySuccess("增加物流信息成功");
//            }
//            return ServerResponse.createByErrorMessage("增加物流信息失败");
//        }
//
//        if (orderDetail.getOrderDetailStatus() == Const.OrderDetailStatusEnum.DISTRIBUTION.getCode()) {
//            infoVo.setAdminId(adminId);
//            String logisticsNo = String.valueOf(this.generateLogisticsNo());
//            infoVo.setLogisticsNo(logisticsNo);
//            Info info = assembleInfo(infoVo);
//            int resultCount = infoMapper.checkInfoNum(info.getOrderDetailId());
//
//            int rowCount = 0 ;
//            if(StringUtils.isNotBlank(infoVo.getDeliveryTime())){
//                info.setDeliveryTime(DateTimeUtil.strToDate(infoVo.getDeliveryTime()));
//                rowCount = infoMapper.insert(info);
//            }else{
//                rowCount = infoMapper.insertSelective(info);
//            }
//            if (rowCount > 0) {
//                orderDetail.setOrderDetailStatus(Const.OrderDetailStatusEnum.SHIPPED.getCode());
//                resultCount = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
//                if(resultCount > 0){
//                    int result = orderDetailMapper.selectCountDistributionOrderDetail(order.getOrderId());
//                    if(result > 0){
//                        if(order.getOrderStatus() != Const.OrderStatusEnum.SHIPPING.getCode())
//                            order.setOrderStatus(Const.OrderStatusEnum.SHIPPING.getCode());
//                    }else{
//                        order.setOrderStatus(Const.OrderStatusEnum.SHIPPED.getCode());
//                    }
//                    orderMapper.updateByPrimaryKeySelective(order);
//                    Map mapResult = Maps.newHashMap();
//                    mapResult.put("logisticsId", info.getLogisticsId());
//                    mapResult.put("logisticsNo", info.getLogisticsNo());
//                    return ServerResponse.createBySuccess("新建物流信息成功，发货成功", mapResult);
//                }
//            }else{
//                return ServerResponse.createByErrorMessage("新建物流信息失败");
//            }
//        } else if (orderDetail.getOrderDetailStatus() == Const.OrderDetailStatusEnum.CANCELED.getCode()) {
//            return ServerResponse.createByErrorMessage("商品已被取消，无法创建物流信息");
//        }else if (orderDetail.getOrderDetailStatus() == Const.OrderDetailStatusEnum.NO_SHIPPING.getCode()) {
//            return ServerResponse.createByErrorMessage("商品未配货，无法创建物流信息");
//        }else if (orderDetail.getOrderDetailStatus() == Const.OrderDetailStatusEnum.SHIPPED.getCode()) {
//            return ServerResponse.createByErrorMessage("商品已发货，无法创建物流信息");
//        }else if (orderDetail.getOrderDetailStatus() == Const.OrderDetailStatusEnum.ORDER_SUCCESS.getCode()) {
//            return ServerResponse.createByErrorMessage("商品已接收，无法创建物流信息");
//        }
//        return ServerResponse.createByErrorMessage("订单异常，无法创建物流信息");
//    }

    public ServerResponse<String> del(Integer userId, Integer logisticsId) {
        int resultCount = infoMapper.deleteByLisIdUserId(userId, logisticsId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除物流信息成功");
        }
        return ServerResponse.createByErrorMessage("删除物流信息失败");
    }


    public ServerResponse update(Integer userId, InfoVo infoVo) {
        infoVo.setAdminId(userId);
        Info info = assembleInfo(infoVo);
        int rowCount = infoMapper.updateByPrimaryKeySelective(info);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新物流信息成功");
        }
        return ServerResponse.createByErrorMessage("更新物流信息失败");
    }


    public ServerResponse<InfoVo> select(Integer userId, Integer logisticsId) {
        Info info = infoMapper.selectByLisIdUserId(userId, logisticsId);
        if (info == null) {
            return ServerResponse.createByErrorMessage("无法查询到该物流信息");
        }
        InfoVo infoVo = assembleInfoVo(info);
        return ServerResponse.createBySuccess("查询物流信息成功", infoVo);
    }
    public ServerResponse<InfoVo> selectUser( Integer logisticsId) {
        Info info = infoMapper.selectByLisId( logisticsId);
        if (info == null) {
            return ServerResponse.createByErrorMessage("无法查询到该物流信息");
        }
        InfoVo infoVo = assembleInfoVo(info);
        return ServerResponse.createBySuccess("查询物流信息成功", infoVo);
    }

    public InfoVo assembleInfoVo(Info info){
        InfoVo infoVo = new InfoVo();
        infoVo.setAdminId(info.getAdminId());
        infoVo.setOrderId(info.getOrderId());
        infoVo.setCustomId(info.getCustomId());
        infoVo.setDeliveryTime(DateTimeUtil.dateToStr(info.getDeliveryTime()));
        infoVo.setCreateTime(DateTimeUtil.dateToStr(info.getCreateTime()));
        infoVo.setUpdateTime(DateTimeUtil.dateToStr(info.getUpdateTime()));
        infoVo.setDeliveryWarehouse(info.getDeliveryWarehouse());
        infoVo.setLogisticsNo(info.getLogisticsNo());
        infoVo.setLogisticsId(info.getLogisticsId());
        infoVo.setShippingDirector(info.getShippingDirector());
        infoVo.setStaffId(info.getStaffId());
        infoVo.setDestination(info.getDestination());
        infoVo.setOrderDetailId(info.getOrderDetailId());
        return infoVo;
    }

    public Info assembleInfo(InfoVo infoVo){
        Info info = new Info();
        info.setAdminId(infoVo.getAdminId());
        info.setCustomId(infoVo.getCustomId());

        info.setOrderId(infoVo.getOrderId());
        info.setDeliveryWarehouse(infoVo.getDeliveryWarehouse());
        info.setLogisticsNo(infoVo.getLogisticsNo());
        info.setLogisticsId(infoVo.getLogisticsId());
        info.setShippingDirector(infoVo.getShippingDirector());
        info.setStaffId(infoVo.getStaffId());
        info.setDestination(infoVo.getDestination());
        info.setOrderDetailId(infoVo.getOrderDetailId());
        return info;
    }


    public ServerResponse<PageInfo> list(Integer userId, String logisticsNo,Integer orderId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(logisticsNo)) {
            logisticsNo = new StringBuilder().append("%").append(logisticsNo).append("%").toString();
        }
        List<Info> infoList = infoMapper.selectByUserId(userId,logisticsNo,orderId);
        List<InfoVo> infoVoList = Lists.newArrayList();
        for (Info info : infoList) {
            InfoVo infoVo = assembleInfoVo(info);
            infoVoList.add(infoVo);
        }
        PageInfo pageResult = new PageInfo(infoList);
        pageResult.setList(infoVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

/*    public ServerResponse<PageInfo> listUser( int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Info> infoList = infoMapper.select();
        List<InfoVo> infoVoList = Lists.newArrayList();
        for (Info info : infoList) {
            InfoVo infoVo = assembleInfoVo(info);
            infoVoList.add(infoVo);
        }
        PageInfo pageResult = new PageInfo(infoList);
        pageResult.setList(infoVoList);
        return ServerResponse.createBySuccess(pageResult);
    }*/

    /**
     * 物流编号的生成
     *
     * @return
     */
    private long generateLogisticsNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }

}
