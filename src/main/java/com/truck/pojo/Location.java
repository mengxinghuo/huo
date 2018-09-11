package com.truck.pojo;

import java.util.Date;

public class Location {
    private Integer logisticsId;

    private Double lng;

    private Double lat;

    private Date currentTimes;

    private Integer isTransferStation;

    private Integer transferStationId;

    public Location(Integer logisticsId, Double lng, Double lat, Date currentTimes, Integer isTransferStation, Integer transferStationId) {
        this.logisticsId = logisticsId;
        this.lng = lng;
        this.lat = lat;
        this.currentTimes = currentTimes;
        this.isTransferStation = isTransferStation;
        this.transferStationId = transferStationId;
    }

    public Location() {
        super();
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(Date currentTimes) {
        this.currentTimes = currentTimes;
    }

    public Integer getIsTransferStation() {
        return isTransferStation;
    }

    public void setIsTransferStation(Integer isTransferStation) {
        this.isTransferStation = isTransferStation;
    }

    public Integer getTransferStationId() {
        return transferStationId;
    }

    public void setTransferStationId(Integer transferStationId) {
        this.transferStationId = transferStationId;
    }
}