package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.BuyStatus;
import com.truck.pojo.OrderBuyStatus;
import com.truck.pojo.OrderPayStatus;
import com.truck.pojo.PayStatus;

/**
 * Created by geely
 */
public interface IOrderStatusService {

    ServerResponse addOrderBuy(OrderBuyStatus orderBuyStatus);
    ServerResponse addOrderPay(OrderPayStatus orderPayStatus);
    ServerResponse addBuy(BuyStatus buyStatus);
    ServerResponse addPay(PayStatus payStatus);
//    ServerResponse add(OrderStatus status);
//    ServerResponse<String> del(Integer logisticsId);
//    ServerResponse update(OrderStatus status);
//    ServerResponse<PageInfo> list(Integer logisticsId, int pageNum, int pageSize);
    ServerResponse listBuy();
    ServerResponse listPay();

}
