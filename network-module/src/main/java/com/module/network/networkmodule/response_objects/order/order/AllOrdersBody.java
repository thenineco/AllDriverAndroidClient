package com.module.network.networkmodule.response_objects.order.order;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.orders.Order;

import java.util.List;

/**
 * Created by zest
 */

public class AllOrdersBody {

    @SerializedName("msg")
    private List<Order> mOrderList;

    public List<Order> getOrderList() {
        return mOrderList;
    }
}
