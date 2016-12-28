package com.soberdriver.client.soberdriver.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.module.network.networkmodule.PhoneUtil;
import com.module.network.networkmodule.TokenUtil;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.AuthKey;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.mvp.models.User;
import com.soberdriver.client.soberdriver.presentation.view.InputPhoneNumberView;

import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zest .
 */

@InjectViewState
public class InputPhoneNumberPresenter extends MvpPresenter<InputPhoneNumberView> {

    public void sendUserData(User user) {
//        HttpService.getInstance()
//                .getPinCode(user.getUserPhoneNumber())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(responseBody -> {
//                    try {
//                        System.out.println(responseBody.string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }, Throwable::printStackTrace);
        getViewState().startEnterSecurity(user);

    }

    public void sendPinCodeToServer(String phoneNumber, int pinCode) {
        HttpService.getInstance()
                .getAuthKey(phoneNumber, pinCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authKey -> {
                    TokenUtil.saveToken(SoberDriverApp.getContext(), authKey.getAuthKey());
                    PhoneUtil.savePhone(SoberDriverApp.getContext(), phoneNumber);
                }, Throwable::printStackTrace);
        getViewState().startAddUserCarActivity();
    }
}
