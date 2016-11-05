package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.view.StartOrderView;
import com.soberdriver.client.soberdriver.presentation.presenter.StartOrderPresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class StartOrderActivity extends BaseAppActivity implements StartOrderView {
    public static final String TAG = "StartOrderActivity";
    @InjectPresenter
    StartOrderPresenter mStartOrderPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, StartOrderActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);
    }
}
