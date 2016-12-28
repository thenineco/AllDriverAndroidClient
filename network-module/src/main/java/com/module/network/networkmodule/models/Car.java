package com.module.network.networkmodule.models;

/**
 * Created by roman
 */
public class Car extends RequestBodyModel {

    private String mModel;
    private String mCarNumber;
    private String mMake;
    private String mColor;

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public String getCarNumber() {
        return mCarNumber;
    }

    public void setCarNumber(String carNumber) {
        mCarNumber = carNumber;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = make;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}
