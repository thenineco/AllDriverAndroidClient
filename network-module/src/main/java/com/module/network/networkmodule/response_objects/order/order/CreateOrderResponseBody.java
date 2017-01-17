package com.module.network.networkmodule.response_objects.order.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zest
 */

public class CreateOrderResponseBody {
    @SerializedName("msg")
    private CreateOrderMessage mCreateOrderMessage;

    public CreateOrderMessage getCreateOrderMessage() {
        return mCreateOrderMessage;
    }

    public void setCreateOrderMessage(
            CreateOrderMessage createOrderMessage) {
        mCreateOrderMessage = createOrderMessage;
    }
}
