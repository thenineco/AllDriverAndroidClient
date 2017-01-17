package com.soberdriver.client.soberdriver.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.orders.Order;
import com.module.network.networkmodule.response_objects.order.order.CreateOrderResponseBody;
import com.module.network.networkmodule.utils.GsonUtil;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.NewOrderView;

import java.io.IOException;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

@InjectViewState
public class NewOrderPresenter extends MvpPresenter<NewOrderView> {

    public void createOrder(List<Order> orders) {
        HttpService.getInstance()
                .createUserOrderList(SoberDriverApp.getContext(), orders)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    try {
                        String response = responseBody.string();

                        CreateOrderResponseBody createOrderResponseBody = GsonUtil.getGson().fromJson(
                                response,
                                CreateOrderResponseBody.class);

                        String orderId = createOrderResponseBody.getCreateOrderMessage()
                                .getCreateOrderResult()
                                .getOrders().get(0)
                                .getOrderId();
                        ((SoberDriverApp) SoberDriverApp.getContext()).startSocketByOrderId(
                                orderId);
                        getViewState().startDriverSelect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
    }
}
