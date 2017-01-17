package com.module.network.networkmodule.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by roman
 */

public abstract class DriverTokenUtil {

    public static final String DRIVER_TOKEN = "driver_token";
    public static final String PREFERENCE = "preference";

    public static String getToken(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String token = preferences.getString(DRIVER_TOKEN, "");
        return token;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        preferences.edit().putString(DRIVER_TOKEN, token)
                .apply();
    }

}
