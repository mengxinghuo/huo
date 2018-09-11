package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Info;
import com.truck.vo.InfoVo;

/**
 * Created by geely
 */
public interface IInfoService {

    ServerResponse add(Integer adminId, InfoVo infoVo);
    ServerResponse<String> del(Integer adminId, Integer logisticsId);
    ServerResponse update(Integer adminId, InfoVo infoVo);
    ServerResponse<InfoVo> select(Integer adminId, Integer logisticsId);
    ServerResponse<PageInfo> list(Integer adminId,String logisticsNo, Integer orderId,int pageNum, int pageSize);
    ServerResponse<InfoVo> selectUser( Integer logisticsId);
//    ServerResponse<PageInfo> listUser( int pageNum, int pageSize);
     InfoVo assembleInfoVo(Info info);
}
