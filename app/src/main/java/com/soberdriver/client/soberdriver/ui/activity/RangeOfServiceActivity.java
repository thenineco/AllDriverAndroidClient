package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.RangeOfServicePresenter;
import com.soberdriver.client.soberdriver.presentation.view.RangeOfServiceView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RangeOfServiceActivity extends BaseAppActivity implements RangeOfServiceView {
    public static final String TAG = "RangeOfServiceActivity";
    @InjectPresenter
    RangeOfServicePresenter mRangeOfServicePresenter;

    @BindView(R.id.get_from_bar_btn)
    FrameLayout mGetFromBarBtn;
    @BindView(R.id.Take_the_car_to_the_car_service_btn)
    FrameLayout mTakeTheCarToTheCarServiceBtn;
    @BindView(R.id.overtake_the_car_btn)
    FrameLayout mOvertakeTheCarBtn;
    @BindView(R.id.driver_for_a_day_btn)
    FrameLayout mDriverForADayBtn;
    @BindView(R.id.toolbar)
    AppCustomToolbar mToolbarView;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, RangeOfServiceActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_of_service);
        ButterKnife.bind(this);
        mToolbarView.setToolbarTitle("Driver in Time");
        setSupportActionBar(mToolbarView.getToolbar());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @OnClick({R.id.get_from_bar_btn, R.id.Take_the_car_to_the_car_service_btn,
            R.id.overtake_the_car_btn, R.id.driver_for_a_day_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_from_bar_btn:
                startNewOrderActivity();
                break;
            case R.id.Take_the_car_to_the_car_service_btn:
                startNewOrderActivity();
                break;
            case R.id.overtake_the_car_btn:
                startNewOrderActivity();
                break;
            case R.id.driver_for_a_day_btn:
                startNewOrderActivity();
                break;
        }
    }

    private void startNewOrderActivity() {
        startActivity(OrderActivity.getIntent(this));
    }
}
