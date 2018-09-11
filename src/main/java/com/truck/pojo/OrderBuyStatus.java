package com.truck.pojo;

import java.util.Date;

public class OrderBuyStatus {
    private Integer id;

    private Integer orderId;

    private Integer buyId;

    private Date createTime;

    private Date updateTime;

    public OrderBuyStatus(Integer id, Integer orderId, Integer buyId, Date createTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.buyId = buyId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderBuyStatus() {
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

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
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