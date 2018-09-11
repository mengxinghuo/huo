package com.truck.vo;


public class LocationVo {
    private Integer logisticsId;

    private Double lng;

    private Double lat;

    private String currentTimes;

    private Integer isTransferStation;

    private Integer transferStationId;

    public LocationVo(Integer logisticsId, Double lng, Double lat , String currentTimes, Integer isTransferStation, Integer transferStationId) {
        this.logisticsId = logisticsId;
        this.lng = lng ;
        this.lat = lat ;
        this.currentTimes = currentTimes;
        this.isTransferStation = isTransferStation;
        this.transferStationId = transferStationId;
    }

    public LocationVo() {
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

    public String getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(String currentTimes) {

        this.currentTimes = currentTimes == null ? null : currentTimes.trim();
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