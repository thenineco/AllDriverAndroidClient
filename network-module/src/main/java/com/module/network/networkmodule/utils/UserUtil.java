package com.module.network.networkmodule.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.module.network.networkmodule.models.User;
import com.module.network.networkmodule.utils.GsonUtil;

/**
 * Created by zest
 */

public class UserUtil {
    public static final String USER = "user";
    public static final String PREFERENCE = "preference";

    public static User getUser(Context context) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String userJson = preferences.getString(USER, "");
        return GsonUtil.getGson().fromJson(userJson, User.class);
    }

    public static void saveUser(Context context, User user) {
        SharedPreferences preferences = context
                .getSharedPreferences(PREFERENCE,
                        Context.MODE_PRIVATE);
        String userJson = GsonUtil.getGson().toJson(user);
        preferences.edit().putString(USER, userJson)
                .apply();
    }

}
