package com.soberdriver.client.soberdriver.services.chat_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

public class SocketConnectionImpl implements SocketConnection {

    public static final String TAG = "SocketConnection";
    private static SocketClient sSocketClient;
    private Context context;
    private String mOrderId;
    private Handler socketConnectionHandler;
    private Runnable checkConnectionRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (sSocketClient != null && !sSocketClient.socketIsOpen()) {
                    openConnection();
                }
                startCheckConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private BroadcastReceiver screenStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("Websocket", "Screen ON");
                openConnection();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("Websocket", "Screen OFF");
                closeConnection();
            }
        }
    };

    public SocketConnectionImpl(Context context, String orderId) {
        this.context = context;
        mOrderId = orderId;
        socketConnectionHandler = new Handler();
    }

    private void startCheckConnection() {
        socketConnectionHandler.postDelayed(checkConnectionRunnable, 5000);
    }

    private void stopCheckConnection() {
        socketConnectionHandler.removeCallbacks(checkConnectionRunnable);
    }

    @Override
    public void openConnection() {
        if (sSocketClient != null) {
            sSocketClient.closeSocket();
            sSocketClient.connect();
        } else {
            sSocketClient = new SocketIOClient(mOrderId);
            sSocketClient.connect();
        }
        initScreenStateListener();
        startCheckConnection();
    }

    @Override
    public void closeConnection() {
        if (sSocketClient != null) {
            sSocketClient.closeSocket();
            sSocketClient.release();
            sSocketClient = null;
        }
        releaseScreenStateListener();
        stopCheckConnection();
    }

    /**
     * Screen state listener for socket live cycle
     */
    private void initScreenStateListener() {
        context.registerReceiver(screenStateReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
        context.registerReceiver(screenStateReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    private void releaseScreenStateListener() {
        try {
            context.unregisterReceiver(screenStateReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        return sSocketClient != null &&
                sSocketClient.socketIsOpen();
    }


    public static void sendMessage(SocketMessage socketMessage) {
        sSocketClient.sendMessage(socketMessage);
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }
}