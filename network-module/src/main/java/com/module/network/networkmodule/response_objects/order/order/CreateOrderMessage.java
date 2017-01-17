package com.module.network.networkmodule.response_objects.order.order;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.User;

/**
 * Created by zest
 */

public class CreateOrderMessage {

    @SerializedName("results")
    private CreateOrderResult mCreateOrderResult;

    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public CreateOrderResult getCreateOrderResult() {
        return mCreateOrderResult;
    }

    public void setCreateOrderResult(CreateOrderResult createOrderResult) {
        mCreateOrderResult = createOrderResult;
    }
}
