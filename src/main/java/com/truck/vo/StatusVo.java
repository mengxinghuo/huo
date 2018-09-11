package com.truck.vo;


public class StatusVo {
    private Integer logisticsId;

    private Integer logisticsStatus;

    private String statusChangeTime;

    public StatusVo(Integer logisticsId, Integer logisticsStatus, String statusChangeTime) {
        this.logisticsId = logisticsId;
        this.logisticsStatus = logisticsStatus;
        this.statusChangeTime = statusChangeTime;
    }

    public StatusVo() {
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

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(String statusChangeTime) {

        this.statusChangeTime = statusChangeTime == null ? null : statusChangeTime.trim();
    }

}