package com.soberdriver.client.soberdriver.services.chat_service;

import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

/**
 * Created by zest .
 */
public interface SocketClient {
    void connect();

    void closeSocket();

    void reopenSocket();

    void sendMessage(SocketMessage socketMessage);

    boolean networkIsAvailable();

    boolean socketIsOpen();

    void setSocketIsOpen(boolean socketIsOpen);

    void release();

}
