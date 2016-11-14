package com.soberdriver.client.soberdriver.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.NewOrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.NewOrderView;
import com.soberdriver.client.soberdriver.ui.activity.DriverFilterActivity;
import com.soberdriver.client.soberdriver.ui.activity.SelectDriverActivity;
import com.soberdriver.client.soberdriver.ui.activity.SelectLocationActivity;
import com.soberdriver.client.soberdriver.ui.adapter.order.DriverItem;
import com.soberdriver.client.soberdriver.ui.adapter.order.OrderDriversAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewOrderFragment extends BaseAppFragment implements NewOrderView,
        OrderDriversAdapter.OnItemClickListener {

    public static final String TAG = "NewOrderFragment";

    @BindView(R.id.order_main_container)
    FrameLayout mOrderMainContainer;
    @BindView(R.id.order_drivers_recycler_view)
    RecyclerView mDriversRecyclerView;
    @BindView(R.id.order_select_driver_options_container)
    LinearLayout mOrderSelectDriverOptionsContainer;

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

    private List<DriverItem> mDriverList = new ArrayList<>();

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
        mDriversRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    mDriverList.remove(mDriverList.size() - 1);
                    mDriversRecyclerView.setAdapter(
                            new OrderDriversAdapter(getContext(), mDriverList, this));
                    mOrderMinusBtn.setAlpha(1f);
                } else {
                    mDriversRecyclerView.setVisibility(View.GONE);
                    mDriverList.clear();
                    mOrderSelectDriverOptionsContainer.setVisibility(View.VISIBLE);
                    mOrderMinusBtn.setAlpha(.5f);
                    mDriverCount = 1;
                }
                setDriverCount();
                break;
            case R.id.order_plus_btn:
                mDriverCount += 1;
                if (mDriverCount > 1) {
                    if (mDriversRecyclerView.getVisibility() == View.GONE) {
                        mDriversRecyclerView.setVisibility(View.VISIBLE);
                        mOrderSelectDriverOptionsContainer.setVisibility(View.GONE);
                    }
                    if (mDriverList.size()==0){
                        mDriverList.add(new DriverItem());
                        mDriverList.add(new DriverItem());
                    }else {
                        mDriverList.add(new DriverItem());
                    }
                    mDriversRecyclerView.setAdapter(
                            new OrderDriversAdapter(getContext(), mDriverList, this));
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

    @Override
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.order_select_start_location_btn:
                startSelectLocationActivity(START_LOCATION);
                break;
        }
    }
}
