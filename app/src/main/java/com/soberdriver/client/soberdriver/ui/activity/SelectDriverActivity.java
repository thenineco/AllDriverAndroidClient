package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.module.network.networkmodule.models.driver.Driver;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.SelectDriverPresenter;
import com.soberdriver.client.soberdriver.presentation.view.SelectDriverView;
import com.soberdriver.client.soberdriver.ui.adapter.drivers.DriversRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class SelectDriverActivity extends BaseAppActivity implements SelectDriverView,
        View.OnClickListener {
    public static final String TAG = "SelectDriverActivity";
    public static final String DRIVER_COUNT = "driver_count";
    @InjectPresenter
    SelectDriverPresenter mSelectDriverPresenter;
    @BindView(R.id.select_driver_info_text_view)
    AppCompatTextView mInfoTextView;
    @BindView(R.id.select_driver_cancel_the_order_btn)
    AppCompatTextView mrCancelTheOrderBtn;
    @BindView(R.id.select_driver_about_safety_btn)
    AppCompatTextView mAboutSafetyBtn;
    @BindView(R.id.select_driver_driver_list_container)
    PagerContainer mDriverListContainer;
    @BindView(R.id.select_driver_view_pager)
    ViewPager mSelectDriverViewPager;
    @BindView(R.id.select_driver_ok_btn)
    AppCompatButton mOkBtn;
    @BindView(R.id.select_driver_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.select_drivers_drivers_recycler_view)
    RecyclerView mDriversRecyclerView;
    private DriverSelectListener mDriverSelectListener;

    public static Intent getIntent(final Context context, int driverCount) {
        Intent intent = new Intent(context, SelectDriverActivity.class);
        intent.putExtra(DRIVER_COUNT, driverCount);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_driver);
        ButterKnife.bind(this);
        setStartParams();

    }

    private void setStartParams() {
        mInfoTextView.setText("Клево! Мы сейчас\n"
                + "будем подбирать\n"
                + "водителя. Хочешь\n"
                + "заранее посмотреть,\n"
                + "кто приедет -\n"
                + "оставайся на экране\n"
                + "\n"
                + "будет мэджик!\n"
                + ":)");

        mDriverSelectListener = driver -> selectDriver(driver);
    }

    private void setViewPager(List<Driver> drivers) {
        ViewPager pager = mDriverListContainer.getViewPager();
        pager.setAdapter(new DriverSelectPagerAdapter(drivers, mDriverSelectListener));
        pager.setClipChildren(false);
        pager.setOffscreenPageLimit(15);

        mDriverListContainer.setPageItemClickListener(
                (view, position) -> Toast.makeText(SelectDriverActivity.this,
                        "position:" + position,
                        Toast.LENGTH_SHORT).show());

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer", false);


        new CoverFlow.Builder()
                .with(pager)
                .scale(0.4f)
                .spaceSize(50f)
                .build();
    }


    @OnClick({R.id.select_driver_cancel_the_order_btn, R.id.select_driver_about_safety_btn,
            R.id.select_driver_ok_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_driver_cancel_the_order_btn:
                onBackPressed();
                break;
            case R.id.select_driver_about_safety_btn:
                break;
            case R.id.item_driver_select_select_btn:
                break;
            case R.id.select_driver_ok_btn:
                startDriverFind();
                break;
        }
    }

    private void startDriverFind() {
        mInfoTextView.setText("Мы ищем для вас \n водителя");
        mProgressBar.setVisibility(View.VISIBLE);
        mAboutSafetyBtn.setVisibility(View.VISIBLE);
        mOkBtn.setVisibility(View.GONE);
        mSelectDriverPresenter.findFreeDriversForOrder();
    }

    @Override
    public void showDrivers(List<Driver> drivers) {
        int driverCount = getIntent().getIntExtra(DRIVER_COUNT, 0);
        if (driverCount > 1) {
            if (driverCount == 2) {
                drivers.remove(0);
            }
            showDriversList(drivers);
        } else {
            showDriversInViewPager(drivers);
        }
    }

    private void showDriversList(List<Driver> drivers) {

        mProgressBar.setVisibility(View.GONE);
        DriversRecyclerViewAdapter adapter = new DriversRecyclerViewAdapter(this, drivers,
                mDriverSelectListener);
        mDriversRecyclerView.setAdapter(adapter);
        mDriversRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showDriversInViewPager(List<Driver> drivers) {
        mProgressBar.setVisibility(View.GONE);
        mInfoTextView.setText("Выберите водителя котороый вас заберет");
        mSelectDriverViewPager.setVisibility(View.VISIBLE);
        setViewPager(drivers);
    }

    @Override
    public void openDriverInfo() {

    }

    @Override
    public void selectDriver(Driver driver) {
        startActivity(DriverInRoadActivity.getIntent(this, driver));
        finish();
    }

    @OnClick(R.id.select_driver_ok_btn)
    public void onClick() {
    }

    private class DriverSelectPagerAdapter extends PagerAdapter {

        private DriverSelectListener mDriverSelectListener;
        private List<Driver> mDrivers;

        public DriverSelectPagerAdapter(List<Driver> drivers,
                DriverSelectListener driverSelectListener) {
            mDrivers = drivers;
            mDriverSelectListener = driverSelectListener;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final View itemView = LayoutInflater.from(SelectDriverActivity.this).inflate(
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
            itemView.setOnClickListener(view -> openDriverInfo());
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

    public interface DriverSelectListener {
        void DriverSelected(Driver driver);
    }
}

