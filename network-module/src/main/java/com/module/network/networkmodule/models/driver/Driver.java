package com.module.network.networkmodule.models.driver;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.Categories;

import java.io.Serializable;

/**
 * Created by zest .
 */

public class Driver implements Serializable {

    @SerializedName("_id")
    private String driverId;

    @SerializedName("categories")
    private Categories mCategories = new Categories();

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;

    @SerializedName("mark")
    private Integer mark;

    @SerializedName("gender")
    private Boolean gender;

    @SerializedName("internationalLicence")
    private Boolean internationalLicence;

    @SerializedName("isOfficial")
    private Boolean isOfficial;

    @SerializedName("isSmoking")
    private Boolean isSmoking;

    @SerializedName("isSessionOpened")
    private Boolean isSessionOpened;

    @SerializedName("appearance")
    private String appearance;

    @SerializedName("msg")
    private Driver mDriver;

    public Driver getDriver() {
        return mDriver;
    }

    public void setDriver(Driver driver) {
        mDriver = driver;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getId() {
        return driverId;
    }

    public Categories getCategories() {
        return mCategories;
    }

    public void setCategories(Categories categories) {
        mCategories = categories;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public Boolean getSmoking() {
        return isSmoking;
    }

    public void setSmoking(Boolean smoking) {
        isSmoking = smoking;
    }

    public Boolean getSessionOpened() {
        return isSessionOpened;
    }

    public void setSessionOpened(Boolean sessionOpened) {
        isSessionOpened = sessionOpened;
    }

    public void setId(String id) {
        this.driverId = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getInternationalLicence() {
        return internationalLicence;
    }

    public void setInternationalLicence(Boolean internationalLicence) {
        this.internationalLicence = internationalLicence;
    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Boolean isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Boolean getIsSmoking() {
        return isSmoking;
    }

    public void setIsSmoking(Boolean isSmoking) {
        this.isSmoking = isSmoking;
    }

    public Boolean getIsSessionOpened() {
        return isSessionOpened;
    }

    public void setIsSessionOpened(Boolean isSessionOpened) {
        this.isSessionOpened = isSessionOpened;
    }


}
