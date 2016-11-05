package com.soberdriver.client.soberdriver.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.soberdriver.client.soberdriver.mvp.models.User;
import com.soberdriver.client.soberdriver.presentation.view.InputPhoneNumberView;

/**
 * Created by zest .
 */

@InjectViewState
public class InputPhoneNumberPresenter extends MvpPresenter<InputPhoneNumberView> {

    public void registerUser(User user) {
//        TODO  регистрация юзера

        getViewState().startEnterSecurity(user);
    }
}
