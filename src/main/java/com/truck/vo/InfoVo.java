package com.truck.vo;


import java.util.Date;

public class InfoVo {
    private Integer logisticsId;

    private Integer orderId;

    private String staffId;

    private String deliveryTime;

    private Integer adminId;

    private Integer customId;

    private String destination;

    private String deliveryWarehouse;

    private String shippingDirector;

    private String logisticsNo;

    private Integer orderDetailId;

    private String createTime;

    private String updateTime;


    public InfoVo() {
        super();
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse == null ? null : deliveryWarehouse.trim();
    }

    public String getShippingDirector() {
        return shippingDirector;
    }

    public void setShippingDirector(String shippingDirector) {
        this.shippingDirector = shippingDirector == null ? null : shippingDirector.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}