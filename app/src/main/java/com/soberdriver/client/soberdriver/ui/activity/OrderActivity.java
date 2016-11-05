package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.OrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.OrderView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseAppActivity implements OrderView {
    public static final String TAG = "OrderActivity";
    @BindView(R.id.toolbar_view)
    AppCustomToolbar mToolbarView;
    @BindView(R.id.order_minus_btn)
    AppCompatImageView mOrderMinusBtn;
    @BindView(R.id.order_plus_btn)
    AppCompatImageView mOrderPlusBtn;
    @BindView(R.id.order_driver_count)
    AppCompatTextView mOrderDriverCount;
    @BindView(R.id.order_client_location)
    AppCompatTextView mOrderClientLocation;
    @BindView(R.id.order_ocation_btn)
    AppCompatImageView mOrderLocationBtn;
    @BindView(R.id.the_cost_of_travel_text_view)
    AppCompatTextView mTheCostOfTravelTextView;
    @BindView(R.id.order_final_location_info_container)
    LinearLayout mOrderFinalLocationInfoContainer;
    @BindView(R.id.order_fast_order_btn)
    LinearLayout mOrderFastOrderBtn;
    @BindView(R.id.order_select_driver_options_text_view)
    AppCompatTextView mOrderSelectDriverOptionsTextView;
    @BindView(R.id.order_create_order_btn)
    AppCompatButton mOrderCreateOrderBtn;
    private int mDriverCount = 1;
    @InjectPresenter
    OrderPresenter mOrderPresenter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, OrderActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setStartParameters();

    }

    private void setStartParameters() {
        setDriverCount();
        mOrderMinusBtn.setAlpha(.5f);
    }

    @Override
    public void setDriverCount() {
        mOrderDriverCount.setText(String.valueOf(mDriverCount));
    }

    @OnClick({R.id.order_minus_btn, R.id.order_plus_btn, R.id.order_client_location,
            R.id.order_ocation_btn, R.id.order_fast_order_btn,
            R.id.order_select_driver_options_text_view, R.id.order_create_order_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_minus_btn:
                if (mDriverCount > 1) {
                    mDriverCount -= 1;
                    mOrderMinusBtn.setAlpha(1f);
                } else {
                    mOrderMinusBtn.setAlpha(.5f);
                }
                setDriverCount();
                break;
            case R.id.order_plus_btn:
                mDriverCount += 1;
                if (mDriverCount > 1) {
                    mOrderMinusBtn.setAlpha(1f);
                } else {
                    mOrderMinusBtn.setAlpha(.5f);
                }
                setDriverCount();
                break;
            case R.id.order_client_location:
                break;
            case R.id.order_ocation_btn:
                break;
            case R.id.order_fast_order_btn:
                break;
            case R.id.order_select_driver_options_text_view:
                break;
            case R.id.order_create_order_btn:
                break;
        }
    }
}
