package com.soberdriver.client.soberdriver.services.chat_service;

import com.soberdriver.client.soberdriver.services.chat_service.models.SocketMessage;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by roman
 */

public class MessageManager {
    private static MessageManager mMessageManager;
    private static BehaviorSubject<SocketMessage> sNewMessageBehaviorSubject =
            BehaviorSubject.create(new SocketMessage());

    private static PublishSubject<SocketMessage> sContactStatusPublishSubject =
            PublishSubject.create();


    public static MessageManager getInstance() {
        if (mMessageManager == null) {
            mMessageManager = new MessageManager();
        }
        return mMessageManager;
    }

    private MessageManager() {

    }

    public void sendMessageReedStatus(SocketMessage socketMessage) {
        socketMessage.setType(SocketMessage.TYPE_READ);
        SocketConnectionImpl.sendMessage(socketMessage);
    }

    public static BehaviorSubject<SocketMessage> getNewMessageBehaviorSubject() {
        return sNewMessageBehaviorSubject;
    }

    public static Observable<SocketMessage> getNewMessageObservable() {
        return sNewMessageBehaviorSubject.asObservable();
    }
}
