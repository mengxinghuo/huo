package com.truck.dao;

import com.truck.pojo.Location;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocationMapper {
    int deleteByLisId(Integer logisticsId);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Integer logisticsId);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    int deleteByLisIdUserId(@Param("userId") Integer userId, @Param("logisticsId") Integer logisticsId);

    int updateByLocation(Location record);

    Location selectByLisIdUserId(@Param("userId") Integer userId, @Param("logisticsId") Integer logisticsId);

    List<Location> selectByLisId(Integer logisticsId);

    List<Location> selectStationByLisId(Integer logisticsId);

}