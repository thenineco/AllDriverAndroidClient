package com.module.network.networkmodule.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.module.network.networkmodule.models.User;
import com.module.network.networkmodule.models.driver.Driver;

/**
 * Created by zest
 */

public class DriverUtil {
    public static final String DRIVER = "driver";
    public static final String PREFERENCE = "preference";

    public static Driver getDriver(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String driverJson = preferences.getString(DRIVER, "");
        return GsonUtil.getGson().fromJson(driverJson, Driver.class);
    }

    public static void saveDriver(Context context, Driver driver) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        String driverJson = GsonUtil.getGson().toJson(driver);
        preferences.edit().putString(DRIVER, driverJson)
                .apply();
    }

}
