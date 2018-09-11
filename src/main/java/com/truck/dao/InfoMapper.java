package com.truck.dao;

import com.truck.pojo.Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(Integer logisticsId);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer logisticsId);

    List<Info> selectByOrderDetailId(@Param("orderDetailId") Integer orderDetailId);

    List<Info> selectByOrderId(@Param("orderId") Integer orderId);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);

    int deleteByLisIdUserId(@Param("adminId") Integer adminId, @Param("logisticsId") Integer logisticsId);

    int updateByInfo(Info record);

    Info selectByLisIdUserId(@Param("adminId") Integer adminId, @Param("logisticsId") Integer logisticsId);

    Info selectByLisId( @Param("logisticsId") Integer logisticsId);

    List<Info> selectByUserId(@Param("adminId") Integer adminId,@Param("logisticsNo") String logisticsNo,@Param("orderId") Integer orderId);

    int checkInfoNum(@Param("orderDetailId") Integer orderDetailId );

}