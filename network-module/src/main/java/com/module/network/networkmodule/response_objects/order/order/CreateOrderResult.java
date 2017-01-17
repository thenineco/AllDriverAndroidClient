package com.module.network.networkmodule.response_objects.order.order;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.orders.Order;

import java.util.List;

/**
 * Created by zest
 */

public class CreateOrderResult {

    @SerializedName("status")
    private String status;

    @SerializedName("responses")
    private List<Order> mOrders;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return mOrders;
    }

    public void setOrders(List<Order> orders) {
        mOrders = orders;
    }
}
