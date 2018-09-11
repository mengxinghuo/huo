package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.StatusMapper;
import com.truck.pojo.Status;
import com.truck.pojo.Status;
import com.truck.service.IStatusService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StatusVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iStatusService")
public class StatusServiceImpl implements IStatusService {


    @Autowired
    private StatusMapper statusMapper;

    public ServerResponse add( StatusVo statusVo) {
        Status status = assembleStatus(statusVo);
        int rowCount = 0 ;
        if(StringUtils.isNotBlank(statusVo.getStatusChangeTime())){
            status.setStatusChangeTime(DateTimeUtil.strToDate(statusVo.getStatusChangeTime()));
            rowCount = statusMapper.insert(status);
        }else{
            rowCount = statusMapper.insertSelective(status);
        }
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("logisticsId", status.getLogisticsId());
            return ServerResponse.createBySuccess("新建物流状态成功", result);
        }
        return ServerResponse.createByErrorMessage("新建物流状态成功");
    }

    public ServerResponse<String> del( Integer logisticsId) {
        int resultCount = statusMapper.deleteByLisId(logisticsId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除物流状态成功");
        }
        return ServerResponse.createByErrorMessage("删除物流状态失败");
    }


    public ServerResponse update( Status status) {
        int rowCount = statusMapper.updateByStatus(status);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新物流状态成功");
        }
        return ServerResponse.createByErrorMessage("更新物流状态失败");
    }

    public ServerResponse<PageInfo> list( Integer logisticsId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Status> statusList = statusMapper.selectByLisId(logisticsId);
        List<StatusVo> statusVoList = Lists.newArrayList();
        for (Status status : statusList) {
            StatusVo statusVo = assembleStatusVo(status);
            statusVoList.add(statusVo);
        }
        PageInfo pageResult = new PageInfo(statusList);
        pageResult.setList(statusVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private StatusVo assembleStatusVo(Status status){
        StatusVo statusVo = new StatusVo();
        statusVo.setStatusChangeTime(DateTimeUtil.dateToStr(status.getStatusChangeTime()));
        statusVo.setLogisticsStatus(status.getLogisticsStatus());
        statusVo.setLogisticsId(status.getLogisticsId());
        return statusVo;
    }

    private Status assembleStatus(StatusVo statusVo){
        Status status = new Status();
        status.setLogisticsStatus(statusVo.getLogisticsStatus());
        status.setLogisticsId(statusVo.getLogisticsId());
        return status;
    }


}
