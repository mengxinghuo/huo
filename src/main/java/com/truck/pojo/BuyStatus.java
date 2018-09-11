package com.truck.pojo;

import java.util.Date;

public class BuyStatus {
    private Integer id;

    private String status;

    private Date createTime;

    private Date updateTime;

    public BuyStatus(Integer id, String status, Date createTime, Date updateTime) {
        this.id = id;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BuyStatus() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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