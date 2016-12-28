package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.module.network.networkmodule.models.orders.Driver;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.UserDriversPresenter;
import com.soberdriver.client.soberdriver.presentation.view.UserDriversView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class UserDriversActivity extends BaseAppActivity implements UserDriversView,
        View.OnClickListener {
    public static final String TAG = "UserDriversActivity";
    @InjectPresenter
    UserDriversPresenter mUserDriversPresenter;
    @BindView(R.id.user_drivers_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.user_drivers_view_pager)
    ViewPager mDriversViewPager;
    @BindView(R.id.user_drivers_driver_list_container)
    PagerContainer mDriverListContainer;
    @BindView(R.id.user_drivers_switch)
    Switch mSwitch;
    @BindView(R.id.user_drivers_progress_bar)
    ProgressBar mUserDriversProgressBar;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserDriversActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_drivers);
        ButterKnife.bind(this);
        initToolbar();
        mUserDriversPresenter.findUserDrivers();
    }

    @Override
    public void showUserDrivers(List<Driver> driverList) {
        mUserDriversProgressBar.setVisibility(View.GONE);
        ViewPager pager = mDriverListContainer.getViewPager();
        pager.setAdapter(new DriverSelectPagerAdapter(driverList,
                driver -> {
                    startActivity(
                            DriverInRoadActivity.getIntent(UserDriversActivity.this, driver));
                    finish();
                }));
        pager.setClipChildren(false);
        pager.setOffscreenPageLimit(15);

        mDriverListContainer.setPageItemClickListener(
                (view, position) -> Toast.makeText(UserDriversActivity.this, "position:" + position,
                        Toast.LENGTH_SHORT).show());

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer", false);


        new CoverFlow.Builder()
                .with(pager)
                .scale(0.4f)
                .spaceSize(50f)
                .build();
    }

    private void initToolbar() {
        mToolbar.setToolbarTitle("Онлайн Х водителей");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.user_drivers_back_btn)
    public void onClick() {
    }

    @Override
    public void onClick(View view) {
        startOrder();
    }

    public void startOrder() {
        finish();
    }

    private class DriverSelectPagerAdapter extends PagerAdapter {

        private SelectDriverActivity.DriverSelectListener mDriverSelectListener;
        private List<Driver> mDrivers;

        public DriverSelectPagerAdapter(List<Driver> drivers,
                SelectDriverActivity.DriverSelectListener driverSelectListener) {
            mDrivers = drivers;
            mDriverSelectListener = driverSelectListener;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final View itemView = LayoutInflater.from(UserDriversActivity.this).inflate(
                    R.layout.item_driver_select, null);
            AppCompatButton selectButton = (AppCompatButton) itemView.findViewById(
                    R.id.item_driver_select_select_btn);
            AppCompatTextView driverNameTextView = (AppCompatTextView) itemView.findViewById(
                    R.id.item_driver_select_driver_name);
            AppCompatTextView driverMarkTextView = (AppCompatTextView) itemView.findViewById(
                    R.id.item_driver_select_driver_mark);
            Driver driver = mDrivers.get(position);

            driverMarkTextView.setText(String.valueOf(driver.getMark()) + " лет");
            driverNameTextView.setText(driver.getName());

            selectButton.setOnClickListener(
                    view -> mDriverSelectListener.DriverSelected(mDrivers.get(position)));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mDrivers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

    }
}
