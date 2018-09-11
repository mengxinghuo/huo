package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.LocationMapper;
import com.truck.pojo.Info;
import com.truck.pojo.Location;
import com.truck.pojo.Location;
import com.truck.pojo.Status;
import com.truck.service.ILocationService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.InfoVo;
import com.truck.vo.LocationVo;
import com.truck.vo.LocationVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iLocationService")
public class LocationServiceImpl implements ILocationService {


    @Autowired
    private LocationMapper locationMapper;

    public ServerResponse add( LocationVo locationVo) {
        Location location = assembleLocation(locationVo);
        int rowCount = 0 ;
        if(StringUtils.isNotBlank(locationVo.getCurrentTimes())){
            location.setCurrentTimes(DateTimeUtil.strToDate(locationVo.getCurrentTimes()));
            rowCount = locationMapper.insert(location);
        }else{
            rowCount = locationMapper.insertSelective(location);
        }
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("logisticsId", location.getLogisticsId());
            return ServerResponse.createBySuccess("新建物流位置成功", result);
        }
        return ServerResponse.createByErrorMessage("新建物流位置失败");
    }

//    public ServerResponse<String> del( Integer logisticsId) {
//        int resultCount = locationMapper.deleteByLisId(logisticsId);
//        if (resultCount > 0) {
//            return ServerResponse.createBySuccess("删除物流信息成功");
//        }
//        return ServerResponse.createByErrorMessage("删除物流信息失败");
//    }
//
//
//    public ServerResponse update( Location location) {
//        int rowCount = locationMapper.updateByLocation(location);
//        if (rowCount > 0) {
//            return ServerResponse.createBySuccess("更新物流信息成功");
//        }
//        return ServerResponse.createByErrorMessage("更新物流信息失败");
//    }


    public ServerResponse<PageInfo> list( Integer logisticsId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Location> locationList = locationMapper.selectByLisId(logisticsId);
        List<LocationVo> locationVoList = Lists.newArrayList();
        for (Location location : locationList) {
            LocationVo locationVo = assembleLocationVo(location);
            locationVoList.add(locationVo);
        }
        PageInfo pageResult = new PageInfo(locationList);
        pageResult.setList(locationVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<PageInfo> listStation( Integer logisticsId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Location> locationList = locationMapper.selectStationByLisId(logisticsId);
        List<LocationVo> locationVoList = Lists.newArrayList();
        for (Location location : locationList) {
            LocationVo locationVo = assembleLocationVo(location);
            locationVoList.add(locationVo);
        }
        PageInfo pageResult = new PageInfo(locationList);
        pageResult.setList(locationVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private LocationVo assembleLocationVo(Location location){
        LocationVo locationVo = new LocationVo();
        locationVo.setCurrentTimes(DateTimeUtil.dateToStr(location.getCurrentTimes()));
        locationVo.setIsTransferStation(location.getIsTransferStation());
        locationVo.setLat(location.getLat());
        locationVo.setLng(location.getLng());
        locationVo.setLogisticsId(location.getLogisticsId());
        locationVo.setTransferStationId(location.getTransferStationId());
        return locationVo;
    }

    private Location assembleLocation(LocationVo locationVo){
        Location location = new Location();
        location.setIsTransferStation(locationVo.getIsTransferStation());
        location.setLat(locationVo.getLat());
        location.setLng(locationVo.getLng());
        location.setLogisticsId(locationVo.getLogisticsId());
        location.setLogisticsId(locationVo.getLogisticsId());
        return location;
    }

}
