package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IOrderStatusService;
import com.truck.service.IStatusService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.OrderBuyStatusVo;
import com.truck.vo.OrderPayStatusVo;
import com.truck.vo.StatusVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iOrderStatusService")
public class OrderStatusServiceImpl implements IOrderStatusService {


    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private BuyStatusMapper buyStatusMapper;
    @Autowired
    private OrderBuyStatusMapper orderBuyStatusMapper;
    @Autowired
    private PayStatusMapper payStatusMapper;
    @Autowired
    private OrderPayStatusMapper orderPayStatusMapper;

    public ServerResponse addBuy(BuyStatus buyStatus){
         int rowCount = buyStatusMapper.insertSelective(buyStatus);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", buyStatus.getId());
            return ServerResponse.createBySuccess("新建订单状态成功", result);
        }
        return ServerResponse.createByErrorMessage("新建订单状态成功");
    }

    public ServerResponse addPay(PayStatus payStatus){
         int rowCount = payStatusMapper.insertSelective(payStatus);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", payStatus.getId());
            return ServerResponse.createBySuccess("新建支付状态成功", result);
        }
        return ServerResponse.createByErrorMessage("新建支付状态成功");
    }

    public ServerResponse addOrderBuy(OrderBuyStatus orderBuyStatus){
        int rowCount = orderBuyStatusMapper.insertSelective(orderBuyStatus);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", orderBuyStatus.getId());
            return ServerResponse.createBySuccess("新建订单购买状态成功", result);
        }
        return ServerResponse.createByErrorMessage("新建订单购买状态成功");
    }

    public ServerResponse addOrderPay(OrderPayStatus orderPayStatus){
        int rowCount = orderPayStatusMapper.insertSelective(orderPayStatus);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", orderPayStatus.getId());
            return ServerResponse.createBySuccess("新建订单支付状态成功", result);
        }
        return ServerResponse.createByErrorMessage("新建订单支付状态成功");
    }



//    public ServerResponse<String> del( Integer logisticsId) {
//        int resultCount = statusMapper.deleteByLisId(logisticsId);
//        if (resultCount > 0) {
//            return ServerResponse.createBySuccess("删除订单状态成功");
//        }
//        return ServerResponse.createByErrorMessage("删除订单状态失败");
//    }
//
//
//    public ServerResponse update( Status status) {
//        int rowCount = statusMapper.updateByStatus(status);
//        if (rowCount > 0) {
//            return ServerResponse.createBySuccess("更新订单状态成功");
//        }
//        return ServerResponse.createByErrorMessage("更新订单状态失败");
//    }
//
    public ServerResponse listBuy( ) {
        List<BuyStatus> buyStatusList = buyStatusMapper.list();
        return ServerResponse.createBySuccess(buyStatusList);
    }

    public ServerResponse listPay( ) {
        List<PayStatus> payStatusList = payStatusMapper.list();
        return ServerResponse.createBySuccess(payStatusList);
    }

//    public ServerResponse<PageInfo> list( Integer logisticsId ,int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<Status> statusList = statusMapper.selectByLisId(logisticsId);
//        List<StatusVo> statusVoList = Lists.newArrayList();
//        for (Status status : statusList) {
//            StatusVo statusVo = assembleStatusVo(status);
//            statusVoList.add(statusVo);
//        }
//        PageInfo pageResult = new PageInfo(statusList);
//        pageResult.setList(statusVoList);
//        return ServerResponse.createBySuccess(pageResult);
//    }
//
    public OrderPayStatusVo assembleOrderPayStatusVo(OrderPayStatus orderPayStatus){
        OrderPayStatusVo orderPayStatusVo = new OrderPayStatusVo();
        orderPayStatusVo.setId(orderPayStatus.getId());
        orderPayStatusVo.setOrderId(orderPayStatus.getOrderId());
        orderPayStatusVo.setPayId(orderPayStatus.getPayId());
        orderPayStatusVo.setCreateTime(DateTimeUtil.dateToStr(orderPayStatus.getCreateTime()));
        orderPayStatusVo.setUpdateTime(DateTimeUtil.dateToStr(orderPayStatus.getUpdateTime()));
        
        return orderPayStatusVo;
    }
    
    public OrderBuyStatusVo assembleOrderBuyStatusVo(OrderBuyStatus orderBuyStatus){
        OrderBuyStatusVo orderBuyStatusVo = new OrderBuyStatusVo();
        orderBuyStatusVo.setId(orderBuyStatus.getId());
        orderBuyStatusVo.setOrderId(orderBuyStatus.getOrderId());
        orderBuyStatusVo.setBuyId(orderBuyStatus.getBuyId());
        orderBuyStatusVo.setCreateTime(DateTimeUtil.dateToStr(orderBuyStatus.getCreateTime()));
        orderBuyStatusVo.setUpdateTime(DateTimeUtil.dateToStr(orderBuyStatus.getUpdateTime()));
        
        return orderBuyStatusVo;
    }
//
//    private Status assembleStatus(StatusVo statusVo){
//        Status status = new Status();
//        status.setLogisticsStatus(statusVo.getLogisticsStatus());
//        status.setLogisticsId(statusVo.getLogisticsId());
//        return status;
//    }


}
