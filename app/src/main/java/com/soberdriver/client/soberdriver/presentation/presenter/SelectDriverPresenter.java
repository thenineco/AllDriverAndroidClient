package com.soberdriver.client.soberdriver.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.gson.reflect.TypeToken;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.orders.DriversResponse;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.module.network.networkmodule.models.orders.Driver;
import com.soberdriver.client.soberdriver.presentation.view.SelectDriverView;
import com.soberdriver.client.soberdriver.utils.GsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class SelectDriverPresenter extends MvpPresenter<SelectDriverView> {

    public void findFreeDriversForOrder() {
        HttpService.getInstance()
                .getOrderDrivers(SoberDriverApp.getContext())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            DriversResponse responseObject = GsonUtil.getGson()
                                    .fromJson(responseBody.string(), DriversResponse.class);

                            getViewState().showDrivers(responseObject.getDrivers());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, Throwable::printStackTrace);
    }


}
