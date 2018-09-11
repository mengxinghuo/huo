package com.truck.pojo;

import java.util.Date;

public class OrderPayStatus {
    private Integer id;

    private Integer orderId;

    private Integer payId;

    private Date createTime;

    private Date updateTime;

    public OrderPayStatus(Integer id, Integer orderId, Integer payId, Date createTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.payId = payId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderPayStatus() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
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