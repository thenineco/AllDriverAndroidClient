package com.soberdriver.client.soberdriver.utils;

import static com.module.network.networkmodule.TokenUtil.TOKEN;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zest
 */

public class UserUtil {
    public static final String USER = "user";
    public static final String PREFERENCE = "preference";

    public static String getUser(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String token = preferences.getString(USER, "");
        return token;
    }

    public static void saveUser(Context context, String token) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        preferences.edit().putString(USER, token)
                .apply();
    }

}
