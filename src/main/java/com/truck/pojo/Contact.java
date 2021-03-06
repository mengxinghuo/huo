package com.truck.pojo;

import java.util.Date;

public class Contact {
    private Integer contactId;

    private Integer shopId;

    private String contactName;

    private String contactPosition;

    private String contactEmail;

    private String contactPhone;

    private Date createTime;

    private Date updateTime;

    public Contact(Integer contactId, Integer shopId, String contactName, String contactPosition, String contactEmail, String contactPhone, Date createTime, Date updateTime) {
        this.contactId = contactId;
        this.shopId = shopId;
        this.contactName = contactName;
        this.contactPosition = contactPosition;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Contact() {
        super();
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition == null ? null : contactPosition.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
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