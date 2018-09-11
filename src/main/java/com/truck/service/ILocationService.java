package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Location;
import com.truck.vo.LocationVo;

/**
 * Created by geely
 */
public interface ILocationService {

    ServerResponse add(LocationVo locationVo);
//    ServerResponse<String> del(Integer logisticsId);
//    ServerResponse update( Location location);
    ServerResponse<PageInfo> list(Integer logisticsId, int pageNum, int pageSize);
    ServerResponse<PageInfo> listStation(Integer logisticsId, int pageNum, int pageSize);

}
