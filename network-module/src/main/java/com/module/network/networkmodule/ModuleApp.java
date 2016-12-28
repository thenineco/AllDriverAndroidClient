package com.module.network.networkmodule;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by zestxx
 */

public class ModuleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
