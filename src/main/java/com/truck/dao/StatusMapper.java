package com.truck.dao;

import com.truck.pojo.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusMapper {
    int deleteByLisId(Integer logisticsId);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer logisticsId);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);

    int deleteByLisIdUserId(@Param("userId") Integer userId, @Param("logisticsId") Integer logisticsId);

    int updateByStatus(Status record);

    Status selectByLisIdUserId(@Param("userId") Integer userId, @Param("logisticsId") Integer logisticsId);

    List<Status> selectByLisId(Integer logisticsId);

}