package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.MainOrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.MainOrderView;
import com.soberdriver.client.soberdriver.ui.fragment.RangeOfServiceFragment;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainOrderActivity extends BaseAppActivity implements MainOrderView {
    public static final String TAG = "MainOrderActivity";
    @InjectPresenter
    MainOrderPresenter mMainOrderPresenter;

    @BindView(R.id.toolbar)
    AppCustomToolbar mToolbarView;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mMainDrawerLayout;
    private MaterialMenuDrawable materialMenu;
    private boolean menuIsOpen;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainOrderActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_of_service);
        ButterKnife.bind(this);
        setToolbar();
        setDrawerView();
        setRangeOfServiceFragment();
    }

    private void setRangeOfServiceFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.range_of_service_container, RangeOfServiceFragment.newInstance())
                .commit();
    }

    private void setDrawerView() {
        ViewGroup.LayoutParams layoutParams = mMainDrawerLayout.getLayoutParams();
        layoutParams.width = DisplayUtil.getDisplayWidth(this);
        mMainDrawerLayout.requestLayout();

        mMainDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                materialMenu.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        menuIsOpen ? 2 - slideOffset : slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                menuIsOpen = true;

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                menuIsOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void setToolbar() {
        mToolbarView.setToolbarTitle("Driver in Time");
        Toolbar toolbar = mToolbarView.getToolbar();
        setSupportActionBar(toolbar);

        materialMenu = new MaterialMenuDrawable(this, Color.WHITE,
                MaterialMenuDrawable.Stroke.THIN);
        toolbar.setNavigationIcon(materialMenu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen) {
                    materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                    mMainDrawerLayout.closeDrawers();
                } else {
                    materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                    mMainDrawerLayout.openDrawer(Gravity.LEFT);
                }
                // random state

            }
        });
    }
}
