package com.truck.dao;

import com.truck.pojo.BuyStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuyStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyStatus record);

    int insertSelective(BuyStatus record);

    BuyStatus selectByPrimaryKey(Integer id);

    List<BuyStatus> selectByOrderId(@Param("orderId") Integer orderId);

    List<BuyStatus> list();

    int updateByPrimaryKeySelective(BuyStatus record);

    int updateByPrimaryKey(BuyStatus record);
}