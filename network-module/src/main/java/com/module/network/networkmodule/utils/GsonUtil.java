package com.module.network.networkmodule.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by zest
 */
public abstract class GsonUtil {
    private static final String LOG_TAG = "GsonUtil";
    private static Gson gson;

    @NonNull
    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();
        }
        return gson;
    }
}
