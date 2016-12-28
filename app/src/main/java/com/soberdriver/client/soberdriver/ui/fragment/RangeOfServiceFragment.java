package com.soberdriver.client.soberdriver.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.RangeOfPresenter;
import com.soberdriver.client.soberdriver.presentation.view.RangeOfView;
import com.soberdriver.client.soberdriver.ui.activity.MainOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RangeOfServiceFragment extends BaseAppFragment implements RangeOfView {
    public static final String TAG = "RangeOfServiceFragment";
    @InjectPresenter
    RangeOfPresenter mRangeOfPresenter;
    @BindView(R.id.get_from_bar_btn)
    FrameLayout mGetFromBarBtn;
    @BindView(R.id.Take_the_car_to_the_car_service_btn)
    FrameLayout mTakeTheCarToTheCarServiceBtn;
    @BindView(R.id.overtake_the_car_btn)
    FrameLayout mOvertakeTheCarBtn;
    @BindView(R.id.driver_for_a_day_btn)
    FrameLayout mDriverForADayBtn;

    public static RangeOfServiceFragment newInstance() {
        RangeOfServiceFragment fragment = new RangeOfServiceFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_range_of, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.get_from_bar_btn, R.id.Take_the_car_to_the_car_service_btn,
            R.id.overtake_the_car_btn, R.id.driver_for_a_day_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_from_bar_btn:
                startNewOrderFragment();
                break;
            case R.id.Take_the_car_to_the_car_service_btn:
                startNewOrderFragment();
                break;
            case R.id.overtake_the_car_btn:
                startNewOrderFragment();
                break;
            case R.id.driver_for_a_day_btn:
                startNewOrderFragment();
                break;
        }
    }

    private void startNewOrderFragment() {
        ((MainOrderActivity) getActivity())
                .openFragment(NewOrderFragment.newInstance());
    }
}
