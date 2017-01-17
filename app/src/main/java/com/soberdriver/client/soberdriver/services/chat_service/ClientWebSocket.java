package com.soberdriver.client.soberdriver.services.chat_service;


import com.neovisionaries.ws.client.WebSocket;
import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

/**
 * Created by zest .
 */
public interface ClientWebSocket {
    void connect();

    void closeSocket();

    void reopenSocket();

    void sendMessage(SocketMessage socketMessage);

    boolean networkIsAvailable();

    boolean isSocketIsOpen();

    void setSocketIsOpen(boolean socketIsOpen);

    WebSocket getConnection();
}
