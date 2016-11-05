package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.SelectDriverPresenter;
import com.soberdriver.client.soberdriver.presentation.view.SelectDriverView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class SelectDriverActivity extends BaseAppActivity implements SelectDriverView,
        View.OnClickListener {
    public static final String TAG = "SelectDriverActivity";
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

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SelectDriverActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_driver);
        ButterKnife.bind(this);
        ViewPager pager = mDriverListContainer.getViewPager();
        pager.setAdapter(new DriverSelectPagerAdapter());
        pager.setClipChildren(false);
        pager.setOffscreenPageLimit(15);

        mDriverListContainer.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SelectDriverActivity.this, "position:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer", false);


        new CoverFlow.Builder()
                .with(pager)
                .scale(0.4f)
                .spaceSize(50f)
                .build();

    }


    @OnClick({R.id.select_driver_cancel_the_order_btn, R.id.select_driver_about_safety_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_driver_cancel_the_order_btn:
                onBackPressed();
                break;
            case R.id.select_driver_about_safety_btn:
                break;
            case R.id.item_driver_select_select_btn:
                selectDriver();
                break;
        }
    }

    @Override
    public void openDriverInfo() {

    }

    @Override
    public void selectDriver() {
        startActivity(StartOrderActivity.getIntent(this));
    }

    private class DriverSelectPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final View itemView = LayoutInflater.from(SelectDriverActivity.this).inflate(
                    R.layout.item_driver_select, null);
            AppCompatButton selectButton = (AppCompatButton) itemView.findViewById(
                    R.id.item_driver_select_select_btn);
            selectButton.setOnClickListener(SelectDriverActivity.this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDriverInfo();
                }
            });
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}

