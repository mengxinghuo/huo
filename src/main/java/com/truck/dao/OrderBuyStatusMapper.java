package com.truck.dao;

import com.truck.pojo.OrderBuyStatus;

public interface OrderBuyStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderBuyStatus record);

    int insertSelective(OrderBuyStatus record);

    OrderBuyStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderBuyStatus record);

    int updateByPrimaryKey(OrderBuyStatus record);
}