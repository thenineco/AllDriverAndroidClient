package com.soberdriver.client.soberdriver.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.response_objects.DriversResponse;
import com.module.network.networkmodule.utils.GsonUtil;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.UserDriversView;

import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;

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
