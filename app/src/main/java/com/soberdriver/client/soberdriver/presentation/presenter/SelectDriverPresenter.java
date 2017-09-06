package com.soberdriver.client.soberdriver.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.response_objects.DriversResponse;
import com.module.network.networkmodule.utils.GsonUtil;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.SelectDriverView;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class SelectDriverPresenter extends MvpPresenter<SelectDriverView> {

    public void findFreeDriversForOrder() {
        HttpService.getInstance()
                .getOrderDrivers(SoberDriverApp.getContext())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    try {
                        DriversResponse responseObject = GsonUtil.getGson()
                                .fromJson(responseBody.string(), DriversResponse.class);

                        getViewState().showDrivers(responseObject.getDrivers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
    }


    public void cancelOrder(String orderId) {
        HttpService.getInstance()
                .cancelOrder(SoberDriverApp.getContext(), orderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    ((SoberDriverApp) SoberDriverApp.getContext()).closeSocketConnection();
                    getViewState().closeOrder();
                }, Throwable::printStackTrace);
    }

}
