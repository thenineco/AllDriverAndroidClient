package com.module.network.networkmodule.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by roman
 */

public abstract class UserTokenUtil {

    public static final String USER_TOKEN = "user_token";
    public static final String PREFERENCE = "preference";

    public static String getToken(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String token = preferences.getString(USER_TOKEN, "");
        return token;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        preferences.edit().putString(USER_TOKEN, token)
                .apply();
    }

}
