package com.soberdriver.client.soberdriver.utils;

import android.content.Context;

/**
 * Created by zest .
 */

public class DisplayUtil {

    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
