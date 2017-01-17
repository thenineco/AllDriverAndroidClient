package com.soberdriver.client.soberdriver.services.chat_service.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zest .
 */
public class MessageContainer {
    @SerializedName("data")
    private List<SocketMessage> mLiveChatSocketMessages;
    @SerializedName("meta")
    private Meta meta;

    public List<SocketMessage> getLiveChatSocketMessages() {
        return mLiveChatSocketMessages;
    }

    public void setLiveChatSocketMessages(List<SocketMessage> liveChatSocketMessages) {
        this.mLiveChatSocketMessages = liveChatSocketMessages;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
