package com.module.network.networkmodule.response_objects;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.driver.Driver;


import java.util.List;

/**
 * Created by zest
 */

public class DriversResponse {
    @SerializedName("msg")
    private List<Driver> mDrivers;

    public List<Driver> getDrivers() {
        return mDrivers;
    }

    public void setDrivers(List<Driver> drivers) {
        mDrivers = drivers;
    }
}
