package com.soberdriver.client.soberdriver.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by zest .
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface InputSecurityCodeView extends MvpView {
    void sendSecurityCode(String securityCode);
}
