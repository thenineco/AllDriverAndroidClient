package com.soberdriver.client.soberdriver.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface NewOrderView extends MvpView {

    void setDriverCount();

    void startDriverSelect(String orderId);

    void openDriverFilter();

    void showProgressBar();

    void hideProgressBar();
}
