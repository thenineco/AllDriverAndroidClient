package com.soberdriver.client.soberdriver.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.ResponseObject;
import com.module.network.networkmodule.models.orders.Order;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.NewOrderView;
import com.soberdriver.client.soberdriver.utils.GsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class NewOrderPresenter extends MvpPresenter<NewOrderView> {

    public void createOrder(List<Order> orders) {
        HttpService.getInstance()
                .createUserOrderList(SoberDriverApp.getContext(), orders)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            Type itemsListType = new TypeToken<List<Order>>() {
                            }.getType();
                            ResponseObject responseObject = GsonUtil.getGson().fromJson(
                                    responseBody.string(),
                                    ResponseObject.class);
                            getViewState().startDriverSelect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, Throwable::printStackTrace);
    }
}
