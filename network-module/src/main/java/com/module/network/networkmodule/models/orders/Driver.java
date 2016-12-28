package com.module.network.networkmodule.models.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zest .
 */

public class Driver implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("gender")
    @Expose
    private Boolean gender;
    @SerializedName("internationalLicence")
    @Expose
    private Boolean internationalLicence;
    @SerializedName("isOfficial")
    @Expose
    private Boolean isOfficial;
    @SerializedName("isSmoking")
    @Expose
    private Boolean isSmoking;
    @SerializedName("isSessionOpened")
    @Expose
    private Boolean isSessionOpened;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
