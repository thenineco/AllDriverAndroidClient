package com.module.network.networkmodule.models.address;

import java.util.ArrayList;

/**
 * Created by roman
 */
public class Address {
    private ArrayList<Float> center = new ArrayList<>();
    private String name;
    private float latitude;
    private float longitude;

    public Address(ArrayList<Float> center) {
        this.center = center;
    }

    public Address() {
        latitude = 33.56546f;
        longitude = 35.65657f;
        center.add(latitude);
        center.add(longitude);
    }

    public ArrayList<Float> getCenter() {
        return center;
    }

    public void setCenter(ArrayList<Float> center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCenter(float center) {
        this.center.add(center);
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
