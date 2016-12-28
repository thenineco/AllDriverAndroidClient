package com.soberdriver.client.soberdriver.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.module.network.networkmodule.models.orders.Driver;

import java.util.List;

public interface UserDriversView extends MvpView {

    void showUserDrivers(List<Driver> driverList);
}
