package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Status;
import com.truck.vo.StatusVo;

/**
 * Created by geely
 */
public interface IStatusService {

    ServerResponse add(StatusVo statusVo);
    ServerResponse<String> del(Integer logisticsId);
    ServerResponse update(Status status);
    ServerResponse<PageInfo> list(Integer logisticsId, int pageNum, int pageSize);

}
