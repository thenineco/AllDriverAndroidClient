package com.soberdriver.client.soberdriver.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.module.network.networkmodule.models.orders.Driver;

import java.util.List;

public interface SelectDriverView extends MvpView {
    void showDrivers(List<Driver> drivers);

    void openDriverInfo();

    void selectDriver(Driver driver);
}
