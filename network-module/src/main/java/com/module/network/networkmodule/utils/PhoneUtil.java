package com.module.network.networkmodule.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by roman
 */

public abstract class PhoneUtil {

    public static final String PHONE = "phone";
    public static final String PREFERENCE = "preference";

    public static String getPhone(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString(PHONE, "");
    }

    public static void savePhone(Context context, String phone) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        preferences.edit().putString(PHONE, phone)
                .apply();
    }
}
