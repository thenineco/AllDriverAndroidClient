package com.module.network.networkmodule;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by roman
 */

public abstract class TokenUtil {

    public static final String TOKEN = "token";
    public static final String PREFERENCE = "preference";

    public static String getToken(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String token = preferences.getString(TOKEN, "");
        return token;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        preferences.edit().putString(TOKEN, token)
                .apply();
    }

}
