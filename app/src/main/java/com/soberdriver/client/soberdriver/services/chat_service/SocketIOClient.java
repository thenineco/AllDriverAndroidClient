package com.soberdriver.client.soberdriver.services.chat_service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.EngineIOException;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.neovisionaries.ws.client.WebSocket;
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
    private static Socket mSocket;
    private boolean socketIsOpen;
    private Emitter.Listener messageEventListener;
    private Emitter.Listener errorEventListener;
    private Emitter.Listener connectionErrorEventListener;
    private Emitter.Listener connectEventListener;
    private Emitter.Listener disconnectEventListener;
    private Emitter.Listener connectTimeoutEventListener;
    private Emitter.Listener reconnectErrorEventListener;
    private Emitter.Listener reconnectEventListener;
    private Emitter.Listener reconnectingEventListener;

    public SocketIOClient(String orderId) {
        try {
            mSocket = IO.socket(DEV_SOCKET_HOST + orderId);
            setSocketEventListener();
            Log.i(TAG, "Socket created");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setSocketEventListener() {
        connectEventListener = args -> {
            socketIsOpen = true;
            Log.i(TAG, "Socket connected");
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
        messageEventListener = args -> {
            JSONObject jsonObject = (JSONObject) args[0];
            Log.i(TAG, jsonObject.toString());
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

        mSocket.on(Socket.EVENT_CONNECT, connectEventListener)
                .on(Socket.EVENT_DISCONNECT, disconnectEventListener)
                .on(Socket.EVENT_CONNECT_ERROR, connectionErrorEventListener)
                .on(Socket.EVENT_ERROR, errorEventListener)
                .on("new message", messageEventListener)
                .on(Socket.EVENT_CONNECT_TIMEOUT, connectTimeoutEventListener)
                .on(Socket.EVENT_RECONNECT, reconnectEventListener)
                .on(Socket.EVENT_RECONNECT_ERROR, reconnectErrorEventListener)
                .on(Socket.EVENT_RECONNECTING, reconnectingEventListener);

    }

    @Override
    public void connect() {
        mSocket.connect();
    }

    @Override
    public void closeSocket() {
        mSocket.close();
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
    public WebSocket getConnection() {
        return null;
    }

    @Override
    public void release() {
        mSocket.off(Socket.EVENT_CONNECT, connectEventListener)
                .off(Socket.EVENT_DISCONNECT, disconnectEventListener)
                .off(Socket.EVENT_CONNECT_ERROR, connectionErrorEventListener)
                .off(Socket.EVENT_ERROR, errorEventListener)
                .off(Socket.EVENT_MESSAGE, messageEventListener)
                .off(Socket.EVENT_CONNECT_TIMEOUT, connectTimeoutEventListener)
                .off(Socket.EVENT_RECONNECT, reconnectEventListener)
                .off(Socket.EVENT_RECONNECT_ERROR, reconnectErrorEventListener)
                .off(Socket.EVENT_RECONNECTING, reconnectingEventListener);
        mSocket.disconnect();
    }
}
