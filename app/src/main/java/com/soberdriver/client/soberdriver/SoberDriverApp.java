package com.soberdriver.client.soberdriver;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.soberdriver.client.soberdriver.services.chat_service.BackgroundManager;
import com.soberdriver.client.soberdriver.services.chat_service.SocketConnectionImpl;

/**
 * Created by zestxx
 */

public class SoberDriverApp extends Application {

    private static Context context;

    public SoberDriverApp() {
        context = this;
    }

    private static SocketConnectionImpl mSocketConnection;
    private BackgroundManager.Listener appActivityListener;


    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        setAppActivityListener();
    }

    private void setAppActivityListener() {
        appActivityListener = new BackgroundManager.Listener() {
            public void onBecameForeground() {
                openSocketConnection();
                Log.i("Websocket", "Became Foreground");
            }

            public void onBecameBackground() {
                closeSocketConnection();
                Log.i("Websocket", "Became Background");
            }
        };
    }

    public void startSocketByOrderId(String orderId) {
        if (mSocketConnection == null) {
            mSocketConnection = new SocketConnectionImpl(getContext(),orderId);
            BackgroundManager.get(this).registerListener(appActivityListener);
        }
    }



    public void closeSocketConnection() {
        mSocketConnection.closeConnection();
    }

    public void openSocketConnection() {
        mSocketConnection.openConnection();
    }
}
