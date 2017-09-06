package com.soberdriver.client.soberdriver.services.chat_service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.EngineIOException;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by zest
 */

public class SocketIOClient implements SocketClient {

    private static final String TAG = "SocketIOClient";

    private static final String DEV_SOCKET_HOST = "http://46.101.157.129:4001/orders/";

    public static final String ORDER_ADDED = "orderAdded";

    public static final String PICK_ORDER = "pick-order";

    public static final String DRIVER_CHOSEN = "driver-chosen";

    public static final String DRIVER_ARRIVED = "driver-arrived";

    public static final String CLIENT_PICKED = "client-picked";

    public static final String ORDER_FINISHED = "order-finished";

    public static final String ORDER_CANCELED = "order-canceled";

    private static Socket mSocket;
    private boolean socketIsOpen;
    private Emitter.Listener orderAddedEventListener;
    private Emitter.Listener errorEventListener;
    private Emitter.Listener connectionErrorEventListener;
    private Emitter.Listener connectEventListener;
    private Emitter.Listener disconnectEventListener;
    private Emitter.Listener connectTimeoutEventListener;
    private Emitter.Listener reconnectErrorEventListener;
    private Emitter.Listener reconnectEventListener;
    private Emitter.Listener reconnectingEventListener;
    private Emitter.Listener pickOrderEventListener;
    private Emitter.Listener driverChosenEventListener;
    private Emitter.Listener driverArrivedEventListener;
    private Emitter.Listener clientPickedEventListener;
    private Emitter.Listener orderFinishedEventListener;
    private Emitter.Listener orderCanceledEventListener;

    public SocketIOClient(String orderId) {
        try {
            mSocket = IO.socket(DEV_SOCKET_HOST + orderId);
            setSocketEventListener();
            Log.i(TAG, "Socket for order " + orderId + " created");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setSocketEventListener() {
        connectEventListener = args -> {
            socketIsOpen = true;
            mSocket.emit("chat message",
                    "even connect: message sent from android to socketio server");
            Log.i(TAG, "Socket  connected ");
        };

        disconnectEventListener = args -> {
            socketIsOpen = false;
            Log.i(TAG, "Socket disconnected");
        };
        connectionErrorEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "Socket connection error");
            EngineIOException exception = (EngineIOException) args[0];
            Log.w(TAG, "Connection error message ---" + exception.toString());
        };
        errorEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "Socket error");
            Log.i(TAG, args[0].toString());
        };
        connectTimeoutEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "connecting timeout");
        };
        reconnectErrorEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "Reconnecting error");
        };
        reconnectEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "Socket reconnect");
        };
        reconnectingEventListener = args -> {
            socketIsOpen = false;
            Log.w(TAG, "Socket reconnecting");
        };
        orderAddedEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        pickOrderEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        driverChosenEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        driverArrivedEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        clientPickedEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        orderFinishedEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };
        orderCanceledEventListener = args -> {
            JSONObject data = (JSONObject) args[0];
            Log.i(TAG, data.toString());
        };

        mSocket.on(Socket.EVENT_CONNECT, connectEventListener)
                .on(Socket.EVENT_DISCONNECT, disconnectEventListener)
                .on(Socket.EVENT_CONNECT_ERROR, connectionErrorEventListener)
                .on(Socket.EVENT_ERROR, errorEventListener)
                .on(Socket.EVENT_CONNECT_TIMEOUT, connectTimeoutEventListener)
                .on(Socket.EVENT_RECONNECT, reconnectEventListener)
                .on(Socket.EVENT_RECONNECT_ERROR, reconnectErrorEventListener)
                .on(Socket.EVENT_RECONNECTING, reconnectingEventListener)
                .on(ORDER_ADDED, orderAddedEventListener)
                .on(PICK_ORDER, pickOrderEventListener)
                .on(DRIVER_CHOSEN, driverChosenEventListener)
                .on(DRIVER_ARRIVED, driverArrivedEventListener)
                .on(CLIENT_PICKED, clientPickedEventListener)
                .on(ORDER_FINISHED, orderFinishedEventListener);
    }

    @Override
    public void connect() {
        mSocket.connect();
    }

    @Override
    public void closeSocket() {
        mSocket.disconnect();
    }

    @Override
    public void reopenSocket() {
//        mSocket.open();
    }

    @Override
    public void sendMessage(SocketMessage socketMessage) {

    }

    @Override
    public boolean networkIsAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) SoberDriverApp.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean socketIsOpen() {
        return mSocket.connected();
    }


    @Override
    public void setSocketIsOpen(boolean socketIsOpen) {

    }

    @Override
    public void release() {
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, connectEventListener)
                .off(Socket.EVENT_DISCONNECT, disconnectEventListener)
                .off(Socket.EVENT_CONNECT_ERROR, connectionErrorEventListener)
                .off(Socket.EVENT_ERROR, errorEventListener)
                .off(Socket.EVENT_MESSAGE, orderAddedEventListener)
                .off(Socket.EVENT_CONNECT_TIMEOUT, connectTimeoutEventListener)
                .off(Socket.EVENT_RECONNECT, reconnectEventListener)
                .off(Socket.EVENT_RECONNECT_ERROR, reconnectErrorEventListener)
                .off(Socket.EVENT_RECONNECTING, reconnectingEventListener)
                .off(ORDER_ADDED, orderAddedEventListener)
                .off(PICK_ORDER, pickOrderEventListener)
                .off(DRIVER_CHOSEN, driverChosenEventListener)
                .off(DRIVER_ARRIVED, driverArrivedEventListener)
                .off(CLIENT_PICKED, clientPickedEventListener)
                .off(ORDER_FINISHED, orderFinishedEventListener);
    }
}
