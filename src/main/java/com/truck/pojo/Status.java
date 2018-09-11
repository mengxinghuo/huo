package com.truck.pojo;

import java.util.Date;

public class Status {
    private Integer logisticsId;

    private Integer logisticsStatus;

    private Date statusChangeTime;

    public Status(Integer logisticsId, Integer logisticsStatus, Date statusChangeTime) {
        this.logisticsId = logisticsId;
        this.logisticsStatus = logisticsStatus;
        this.statusChangeTime = statusChangeTime;
    }

    public Status() {
        super();
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Date getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Date statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }
}