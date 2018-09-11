package com.truck.vo;

import java.util.Date;

public class UserVo {
    private Integer userId;

    private String userName;

    private String userEmail;

    private String userPhone;

    private UserInfoVo userInfoVo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public UserInfoVo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }
}