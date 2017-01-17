package com.soberdriver.client.soberdriver.services.chat_service.models;

import static com.soberdriver.client.soberdriver.services.chat_service.MessageManager
        .getNewMessageBehaviorSubject;

import com.google.gson.annotations.SerializedName;

public class SocketMessage {

    public static final String TYPE_ECHO = "echo";

    public static final String TYPE_SEND = "send";

    public static final String TYPE_CONFIRM = "confirm";

    public static final String TYPE_SENT = "sent";

    public static final String TYPE_MESSAGE = "message";

    public static final String TYPE_MESSAGE_UPDATE = "message_update";

    public static final String TYPE_USER_ONLINE = "user_online";

    public static final String TYPE_READ = "read";

    @SerializedName("queue_id")
    private String queueId;
    @SerializedName("type")
    private String type;
    @SerializedName("server_time")
    private Double serverTime;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("code")
    private String code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getServerTime() {
        return serverTime;
    }

    public void setServerTime(Double serverTime) {
        this.serverTime = serverTime;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
