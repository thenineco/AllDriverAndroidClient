package com.soberdriver.client.soberdriver.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.module.network.networkmodule.utils.PhoneUtil;
import com.module.network.networkmodule.utils.UserTokenUtil;
import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.User;
import com.soberdriver.client.soberdriver.SoberDriverApp;
import com.soberdriver.client.soberdriver.presentation.view.InputPhoneNumberView;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zest .
 */

@InjectViewState
public class InputPhoneNumberPresenter extends MvpPresenter<InputPhoneNumberView> {

    public void sendUserData(User user) {
        HttpService.getInstance()
                .createNewUser(user.getPhone(), user.getName())
                .flatMap(new Func1<ResponseBody, Observable<ResponseBody>>() {
                    @Override
                    public Observable<ResponseBody> call(ResponseBody responseBody) {
                        return HttpService.getInstance()
                                .getPinCode(user.getPhone());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    getViewState().startEnterSecurityCod(user);
                }, Throwable::printStackTrace);
    }

    public void sendPinCodeToServer(String phoneNumber, int pinCode) {
        HttpService.getInstance()
                .getAuthKey(phoneNumber, pinCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authKey -> {
                    UserTokenUtil.saveToken(SoberDriverApp.getContext(), authKey.getAuthKey());
                    PhoneUtil.savePhone(SoberDriverApp.getContext(), phoneNumber);
                }, Throwable::printStackTrace);
        getViewState().startAddUserCarActivity();
    }
}
