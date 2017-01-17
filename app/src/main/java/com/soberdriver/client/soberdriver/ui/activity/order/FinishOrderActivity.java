package com.soberdriver.client.soberdriver.ui.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.view.FinishOrderView;
import com.soberdriver.client.soberdriver.presentation.presenter.FinishOrderPresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;

public class FinishOrderActivity extends BaseAppActivity implements FinishOrderView {
    public static final String TAG = "FinishOrderActivity";
    @InjectPresenter
    FinishOrderPresenter mFinishOrderPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, FinishOrderActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_order);
    }
}
