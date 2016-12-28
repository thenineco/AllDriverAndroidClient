package com.module.network.networkmodule.models;

import java.util.ArrayList;

/**
 * Created by roman
 */
public class Address {
    private ArrayList<Float> center = new ArrayList<>();
    private String name;

    public Address(ArrayList<Float> center) {
        this.center = center;
    }

    public Address() {
        center.add(33.56546f);
        center.add(35.65657f);
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
}
