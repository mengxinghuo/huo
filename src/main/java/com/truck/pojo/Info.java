package com.truck.pojo;

import java.util.Date;

public class Info {
    private Integer logisticsId;

    private Integer adminId;

    private Integer orderId;

    private Integer customId;

    private String staffId;

    private Date deliveryTime;

    private String destination;

    private String deliveryWarehouse;

    private String shippingDirector;

    private String logisticsNo;

    private Integer orderDetailId;

    private Date createTime;

    private Date updateTime;

    public Info(Integer logisticsId, Integer adminId, Integer orderId, Integer customId,
                String staffId, Date deliveryTime, String destination,
                String deliveryWarehouse, String shippingDirector,
                String logisticsNo, Integer orderDetailId, Date createTime, Date updateTime) {
        this.logisticsId = logisticsId;
        this.adminId = adminId;
        this.orderId = orderId;
        this.customId = customId;
        this.staffId = staffId;
        this.deliveryTime = deliveryTime;
        this.destination = destination;
        this.deliveryWarehouse = deliveryWarehouse;
        this.shippingDirector = shippingDirector;
        this.logisticsNo = logisticsNo;
        this.orderDetailId = orderDetailId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Info() {
        super();
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}