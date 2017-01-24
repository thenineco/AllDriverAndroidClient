package com.soberdriver.client.soberdriver.services.chat_service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.module.network.networkmodule.utils.GsonUtil;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import rx.subjects.BehaviorSubject;


/**
 * Created by zest .
 */
public class WebSocketClient implements SocketClient {

    private static final String TAG = "SocketClient";
    private static final String DEV_SOCKET_HOST = "http://46.101.157.129:4001/orders/";

    private static BehaviorSubject<SocketMessage> sUserStatusBehaviorSubject =
            BehaviorSubject.create(
                    new SocketMessage());

    private WebSocket webSocket;
    private boolean socketIsOpen;
    private static WebSocketFactory mWebSocketFactory;
    private String mOrderId;

    public WebSocketClient(String orderId) {
        mOrderId = orderId;
        mWebSocketFactory = new WebSocketFactory();
    }


    @Override
    public void connect() {
        new Thread(() -> {

            if (webSocket != null) {
                reopenSocket();
            } else {
                try {
//                    String token = UserTokenUtil.getToken(SoberDriverApp.getContext());

                    SSLContext context = NaiveSSLContext.getInstance("TLS");

                    mWebSocketFactory.setSSLContext(context);

                    String socketConnectionLink = DEV_SOCKET_HOST + mOrderId;

                    webSocket = mWebSocketFactory.createSocket(
                            socketConnectionLink);

                    webSocket.addListener(new SocketListener());
                    webSocket.connect();

                    Log.d(TAG, "createWebSocket: success");
                } catch (OpeningHandshakeException e) {
                    // Get the status code.
                    int statusCode = e.getStatusLine().getStatusCode();

                    // If the status code is in the range of 300 to 399.
                    if (300 <= statusCode && statusCode <= 399) {
                        // Location header should hold the redirection URL.
                        String location = e.getHeaders().get("Location").get(0);
                    }
                } catch (WebSocketException | IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "createWebSocket: failed", e);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void closeSocket() {
        webSocket.disconnect();
    }

    @Override
    public void reopenSocket() {
        try {
            webSocket = webSocket.recreate().connect();
        } catch (WebSocketException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(SocketMessage socketMessage) {
        String messageToSend = GsonUtil.getGson().toJson(socketMessage);
        if (webSocket != null && webSocket.isOpen()) {
            webSocket.sendText(messageToSend);
        } else {
            handleSendingError(socketMessage);
        }
    }

    private void handleServerResponse(String response) {
        SocketMessage socketMessage = GsonUtil.getGson().fromJson(response, SocketMessage.class);
        switch (socketMessage.getType()) {
            case SocketMessage.TYPE_SEND:
                sendConfirmMessage(socketMessage);
                break;
            case SocketMessage.TYPE_USER_ONLINE:
                break;
            case SocketMessage.TYPE_ECHO:
                sendConfirmMessage(socketMessage);
                break;
            case SocketMessage.TYPE_MESSAGE:
                sendConfirmMessage(socketMessage);
                handleNewMessage(socketMessage);
                break;
            case SocketMessage.TYPE_SENT:
                sendConfirmMessage(socketMessage);
                break;
            case SocketMessage.TYPE_MESSAGE_UPDATE:
                sendConfirmMessage(socketMessage);
                handleMessageUpdate(socketMessage);
                break;
        }
    }

    private void sendConfirmMessage(SocketMessage socketMessage) {
//        SocketMessage answer = new SocketMessage();
//        answer.setType(SocketMessage.TYPE_CONFIRM);
//        sendMessage(answer);
    }


    @Override
    public boolean networkIsAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) SoberDriverApp.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void handleNewMessage(SocketMessage socketMessage) {
        MessageManager.getNewMessageBehaviorSubject().onNext(socketMessage);
    }

    private void handleSendingError(SocketMessage socketMessageToSend) {

    }

    private void handleMessageUpdate(SocketMessage socketMessage) {

    }


    @Override
    public boolean socketIsOpen() {
        return webSocket.isOpen();
    }

    @Override
    public void setSocketIsOpen(boolean socketIsOpen) {
        this.socketIsOpen = socketIsOpen;
    }

    @Override
    public WebSocket getConnection() {
        return webSocket;
    }

    @Override
    public void release() {
        webSocket.disconnect();
        webSocket = null;
    }


    public class SocketListener extends WebSocketAdapter {

        public void onTextMessage(WebSocket websocket, String response) {
            Log.d(TAG, "Server answer: " + response);
            handleServerResponse(response);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            socketIsOpen = true;
            webSocket.flush();
            Log.d(TAG, "startSocketByOrderId: connect SUCCESS");
        }


        @Override
        public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onFrame(websocket, frame);
        }

        @Override
        public void onFrameError(WebSocket websocket, WebSocketException cause,
                WebSocketFrame frame) throws Exception {
            super.onFrameError(websocket, cause, frame);
            Log.d(TAG, "onFrameError: ");

        }

        @Override
        public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onFrameUnsent(websocket, frame);
            if (frame.isTextFrame()) {
                Log.d(TAG, "onFrameUnsent: " + frame.getPayloadText());
                SocketMessage socketMessageToSend = GsonUtil.getGson().fromJson(
                        frame.getPayloadText()
                        , SocketMessage.class);
                handleSendingError(socketMessageToSend);
            }
        }


        @Override
        public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onPingFrame(websocket, frame);
            Log.d(TAG, "onPingFrame:");
        }

        @Override
        public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onPongFrame(websocket, frame);
            Log.d(TAG, "onPongFrame: ");
            websocket.sendPing("Ping");
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Log.d(TAG, "onConnectError: ");
            socketIsOpen = false;
            reopenSocket();

        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
                WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            socketIsOpen = false;
            Log.d(TAG, "WebSocket disconnected");
            if (closedByServer) {
                reopenSocket();
            }
        }

        @Override
        public void onUnexpectedError(WebSocket websocket, WebSocketException cause)
                throws Exception {
            super.onUnexpectedError(websocket, cause);
            reopenSocket();
            Log.d(TAG, "onUnexpectedError: ");
        }

        @Override
        public void onSendError(WebSocket websocket, WebSocketException cause,
                WebSocketFrame frame) throws Exception {
            super.onSendError(websocket, cause, frame);
            Log.d(TAG, "onSendError: ");
        }

        @Override
        public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
            super.onError(websocket, cause);
            Log.d(TAG, "onError: " + cause.getMessage());
            reopenSocket();

        }

        @Override
        public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
            super.handleCallbackError(websocket, cause);
            Log.w(TAG, "handleCallbackError: ", cause);
        }
    }


    public static BehaviorSubject<SocketMessage> getUserStatusBehaviorSubject() {
        return sUserStatusBehaviorSubject;
    }
}
