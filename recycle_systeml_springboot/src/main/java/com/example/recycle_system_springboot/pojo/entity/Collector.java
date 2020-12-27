package com.example.recycle_system_springboot.pojo.entity;

import java.io.Serializable;

/**
 * collector
 * @author 
 */
public class Collector implements Serializable {
    private Integer collectorPersonId;

    private Integer siteId;

    private String collectorName;

    private String userName;

    private String password;

    private String phone;

    private String idcardNumber;

    private String photo;

    private String userStatus;

    private static final long serialVersionUID = 1L;

    public Integer getCollectorPersonId() {
        return collectorPersonId;
    }

    public void setCollectorPersonId(Integer collectorPersonId) {
        this.collectorPersonId = collectorPersonId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}