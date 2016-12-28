package com.soberdriver.client.soberdriver;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by zestxx
 */

public class SoberDriverApp extends Application {

    private static Context context;

    public SoberDriverApp() {
        context = this;
    }

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
