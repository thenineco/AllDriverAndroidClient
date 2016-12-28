package com.soberdriver.client.soberdriver.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.module.network.networkmodule.TokenUtil;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.WalkthroughPresenter;
import com.soberdriver.client.soberdriver.presentation.view.WalkthroughView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.MainOrderActivity;
import com.soberdriver.client.soberdriver.ui.adapter.walkthrough.WalkthroughViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class WalcthroughActivity extends BaseAppActivity implements WalkthroughView {
    public static final String TAG = "WalkthroughActivity";
    private int currentPage = 0;

    @InjectPresenter
    WalkthroughPresenter mWalkthroughPresenter;
    @BindView(R.id.walkthrough_view_pager)
    ViewPager mWalkthroughViewPager;
    @BindView(R.id.walkthrough_circle_indicator)
    CircleIndicator mWalkthroughCircleIndicator;
    @BindView(R.id.walkthrough_next_btn)
    AppCompatButton mWalkthroughNextBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, WalcthroughActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        ButterKnife.bind(this);
        if (!TokenUtil.getToken(this).isEmpty()) {
            startActivity(MainOrderActivity.getIntent(this, false));
        }
        WalkthroughViewPagerAdapter adapter = new WalkthroughViewPagerAdapter(this);
        mWalkthroughViewPager.setAdapter(adapter);
        mWalkthroughCircleIndicator.setViewPager(mWalkthroughViewPager);
        adapter.registerDataSetObserver(mWalkthroughCircleIndicator.getDataSetObserver());
    }

    @OnClick({R.id.walkthrough_next_btn, R.id.walkthrough_skip_text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.walkthrough_next_btn:
                if (currentPage < 3) {
                    currentPage += 1;
                    mWalkthroughViewPager.setCurrentItem(currentPage);
                } else if (currentPage == 3) {
                    startActivity(InputPhoneNumberActivity.getIntent(this));
                    finish();
                }
                break;
            case R.id.walkthrough_skip_text_view:
                startActivity(InputPhoneNumberActivity.getIntent(this));
                finish();
                break;
        }
    }
}
