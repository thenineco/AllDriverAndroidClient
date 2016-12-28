package com.module.network.networkmodule.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zest
 */
public class ResponseMessage<T> {

    @SerializedName("responses")
    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
