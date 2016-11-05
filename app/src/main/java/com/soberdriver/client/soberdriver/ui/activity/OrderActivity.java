package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.OrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.OrderView;
import com.soberdriver.client.soberdriver.ui.fragment.DriverFilterFragment;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseAppActivity implements OrderView {
    public static final String TAG = "OrderActivity";

    public static final int START_LOCATION = 1;

    public static final int FINISH_LOCATION = 2;

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
    @BindView(R.id.order_location_btn)
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
    @BindView(R.id.order_select_final_location_btn)
    LinearLayout mSelectFinalLocationBtn;
    @BindView(R.id.order_final_location_text_view)
    AppCompatTextView mFinalLocationTextView;
    @BindView(R.id.order_main_container)
    FrameLayout mMainContainer;
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
        mSelectFinalLocationBtn.setVisibility(View.VISIBLE);
        mOrderMinusBtn.setAlpha(.5f);
    }

    @Override
    public void setDriverCount() {
        mOrderDriverCount.setText(String.valueOf(mDriverCount));
    }

    @OnClick({R.id.order_minus_btn, R.id.order_plus_btn, R.id.order_client_location,
            R.id.order_location_btn, R.id.order_fast_order_btn,
            R.id.order_select_driver_options_text_view, R.id.order_create_order_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_minus_btn:
                mDriverCount -= 1;
                if (mDriverCount > 1) {
                    mOrderMinusBtn.setAlpha(1f);
                } else {
                    mOrderMinusBtn.setAlpha(.5f);
                    mDriverCount = 1;
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
            case R.id.order_location_btn:
                startSelectLocationActivity(START_LOCATION);
                break;
            case R.id.order_fast_order_btn:
                break;
            case R.id.order_select_driver_options_text_view:
                openDriverFilter();
                break;
            case R.id.order_create_order_btn:
                startDriverSelect();
                break;
        }
    }

    private void startDriverSelect() {
        startActivity(SelectDriverActivity.getIntent(this));
    }

    @Override
    public void openDriverFilter() {
        DriverFilterFragment driverFilterFragment = DriverFilterFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.order_main_container, driverFilterFragment)
                .commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String address = data.getStringExtra(SelectLocationActivity.LOCATION);
            if (requestCode == FINISH_LOCATION && !address.isEmpty()) {
                mSelectFinalLocationBtn.setVisibility(View.GONE);
                mOrderFinalLocationInfoContainer.setVisibility(View.VISIBLE);
                mFinalLocationTextView.setText(address);
            } else if (requestCode == START_LOCATION) {
                mOrderClientLocation.setText(address);
            }
        }

    }

    @OnClick({R.id.order_select_final_location_btn, R.id.order_final_location_info_container})
    public void onClick() {
        startSelectLocationActivity(FINISH_LOCATION);
    }

    private void startSelectLocationActivity(int requestCode) {
        startActivityForResult(SelectLocationActivity.getIntent(this), requestCode);
    }
}
