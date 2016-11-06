package com.soberdriver.client.soberdriver.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.NewOrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.NewOrderView;
import com.soberdriver.client.soberdriver.ui.activity.DriverFilterActivity;
import com.soberdriver.client.soberdriver.ui.activity.SelectDriverActivity;
import com.soberdriver.client.soberdriver.ui.activity.SelectLocationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewOrderFragment extends BaseAppFragment implements NewOrderView {

    public static final String TAG = "NewOrderFragment";

    private int mDriverCount = 1;

    public static final int START_LOCATION = 1;

    public static final int FINISH_LOCATION = 2;

    @InjectPresenter
    NewOrderPresenter mNewOrderPresenter;
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
    @BindView(R.id.order_final_location_text_view)
    AppCompatTextView mOrderFinalLocationTextView;
    @BindView(R.id.the_cost_of_travel_text_view)
    AppCompatTextView mTheCostOfTravelTextView;
    @BindView(R.id.order_final_location_info_container)
    LinearLayout mOrderFinalLocationInfoContainer;
    @BindView(R.id.order_select_final_location_btn)
    LinearLayout mOrderSelectFinalLocationBtn;
    @BindView(R.id.order_fast_order_btn)
    LinearLayout mOrderFastOrderBtn;
    @BindView(R.id.order_select_driver_options_text_view)
    AppCompatTextView mOrderSelectDriverOptionsTextView;
    @BindView(R.id.order_create_order_btn)
    AppCompatButton mOrderCreateOrderBtn;

    public static NewOrderFragment newInstance() {
        NewOrderFragment fragment = new NewOrderFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_order, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStartParameters();
    }


    @Override
    public void setDriverCount() {
        mOrderDriverCount.setText(String.valueOf(mDriverCount));
    }

    private void setStartParameters() {
        setDriverCount();
        mOrderSelectFinalLocationBtn.setVisibility(View.VISIBLE);
        mOrderMinusBtn.setAlpha(.5f);
    }

    @OnClick({R.id.order_minus_btn, R.id.order_plus_btn, R.id.order_location_btn,
            R.id.order_select_final_location_btn, R.id.order_fast_order_btn,
            R.id.order_create_order_btn, R.id.order_select_driver_options_text_view,})
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
            case R.id.order_location_btn:
                startSelectLocationActivity(START_LOCATION);
                break;
            case R.id.order_select_final_location_btn:
                startSelectLocationActivity(FINISH_LOCATION);
                break;
            case R.id.order_fast_order_btn:
                break;
            case R.id.order_create_order_btn:
                startDriverSelect();
                break;
            case R.id.order_select_driver_options_text_view:
                openDriverFilter();
                break;
        }
    }

    private void startDriverSelect() {
        startActivity(SelectDriverActivity.getIntent(getContext()));
    }

    @Override
    public void openDriverFilter() {
        startActivity(DriverFilterActivity.getIntent(getContext()));
    }

    private void startSelectLocationActivity(int requestCode) {
        startActivityForResult(SelectLocationActivity.getIntent(getContext()), requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String address = data.getStringExtra(SelectLocationActivity.LOCATION);
            if (requestCode == FINISH_LOCATION && !address.isEmpty()) {
                mOrderSelectFinalLocationBtn.setVisibility(View.GONE);
                mOrderFinalLocationInfoContainer.setVisibility(View.VISIBLE);
                mOrderFinalLocationTextView.setText(address);
            } else if (requestCode == START_LOCATION) {
                mOrderClientLocation.setText(address);
            }
        }
    }
}
