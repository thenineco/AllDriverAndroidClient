package com.module.network.networkmodule.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zest
 */

public class ResponseObject<T> {
    @SerializedName("msg")
    private ResponseMessage mResponseMessage;

    public ResponseMessage getResponseMessage() {
        return mResponseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        mResponseMessage = responseMessage;
    }
}
