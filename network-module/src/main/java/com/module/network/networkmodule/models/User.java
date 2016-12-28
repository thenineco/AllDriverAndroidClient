package com.module.network.networkmodule.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by zestxx
 */

public class User extends RequestBodyModel {

    @SerializedName("name")
    private String mName;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("registered")
    private Date mRegistered;

    @SerializedName("activated")
    private Date mActivated;

    @SerializedName("car")
    private Car mCar;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Date getRegistered() {
        return mRegistered;
    }

    public void setRegistered(Date registered) {
        mRegistered = registered;
    }

    public Date getActivated() {
        return mActivated;
    }

    public void setActivated(Date activated) {
        mActivated = activated;
    }

    public Car getCar() {
        return mCar;
    }

    public void setCar(Car car) {
        mCar = car;
    }
}
