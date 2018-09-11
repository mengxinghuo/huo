package com.truck.dao;

import com.truck.pojo.OrderPayStatus;

public interface OrderPayStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPayStatus record);

    int insertSelective(OrderPayStatus record);

    OrderPayStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPayStatus record);

    int updateByPrimaryKey(OrderPayStatus record);
}