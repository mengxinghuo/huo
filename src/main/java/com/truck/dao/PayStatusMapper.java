package com.truck.dao;

import com.truck.pojo.BuyStatus;
import com.truck.pojo.PayStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayStatus record);

    int insertSelective(PayStatus record);

    PayStatus selectByPrimaryKey(Integer id);

    List<PayStatus> selectByOrderId(@Param("orderId") Integer orderId);

    List<PayStatus> list();

    int updateByPrimaryKeySelective(PayStatus record);

    int updateByPrimaryKey(PayStatus record);
}