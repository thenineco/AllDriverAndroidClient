package com.soberdriver.client.soberdriver.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.module.network.networkmodule.models.User;

/**
 * Created by zest .
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface InputPhoneNumberView extends MvpView {
    void showSendPhoneNumberBtn();

    void startEnterSecurityCod(User user);

    void startAddUserCarActivity();
}
