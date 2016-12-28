package com.soberdriver.client.soberdriver.presentation.presenter;


import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.orders.DriversResponse;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.UserDriversView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.soberdriver.client.soberdriver.utils.GsonUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class UserDriversPresenter extends MvpPresenter<UserDriversView> {

    public void findUserDrivers() {
        HttpService.getInstance()
                .getOrderDrivers(SoberDriverApp.getContext())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    try {
                        DriversResponse responseObject = GsonUtil.getGson()
                                .fromJson(responseBody.string(), DriversResponse.class);

                        getViewState().showUserDrivers(responseObject.getDrivers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
    }
}
