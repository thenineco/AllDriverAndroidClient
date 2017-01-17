package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.module.network.networkmodule.models.driver.Driver;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.DriverInRoadPresenter;
import com.soberdriver.client.soberdriver.presentation.view.DriverInRoadView;
import com.soberdriver.client.soberdriver.ui.activity.order.StartOrderActivity;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DriverInRoadActivity extends BaseAppActivity implements DriverInRoadView {
    public static final String TAG = "DriverInRoadActivity";
    public static final String DRIVER = "driver";
    @InjectPresenter
    DriverInRoadPresenter mDriverInRoadPresenter;
    @BindView(R.id.order_start_info_text_view)
    AppCompatTextView mOrderStartInfoTextView;
    @BindView(R.id.driver_profile_item_avatar_image_view)
    CircleImageView mDriverProfileItemAvatarImageView;
    @BindView(R.id.driver_profile_item_driver_name_text_view)
    AppCompatTextView mDriverProfileItemDriverNameTextView;
    @BindView(R.id.driver_profile_item_driver_info_text_view)
    AppCompatTextView mDriverProfileItemDriverInfoTextView;
    @BindView(R.id.driver_profile_item_next_btn)
    AppCompatImageView mDriverProfileItemNextBtn;
    @BindView(R.id.start_order_call_to_driver_btn)
    AppCompatButton mStartOrderCallToDriverBtn;
    @BindView(R.id.start_order_cancel_btn)
    AppCompatButton mStartOrderCancelBtn;
    @BindView(R.id.start_order_scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.start_order_more_info_edit_text)
    AppCompatEditText mMoreInfoEditText;
    private CompositeSubscription mCompositeSubscription;
    private boolean keyboardListenersAttached;

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mScrollView.smoothScrollBy(0,
                            DisplayUtil.getDisplayHeight(DriverInRoadActivity.this));
                }
            };

    public static Intent getIntent(final Context context, Driver driver) {
        Intent intent = new Intent(context, DriverInRoadActivity.class);
        intent.putExtra(DRIVER, driver);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_in_road);
        ButterKnife.bind(this);
        attachKeyboardListeners();
        Driver driver = (Driver) getIntent().getExtras().getSerializable(DRIVER);
        mDriverProfileItemDriverNameTextView.setText(driver.getName());
        mDriverProfileItemDriverInfoTextView.setText(
                "стаж " + String.valueOf(driver.getMark()) + " лет");
    }

    private void startOrder() {
        Driver driver = (Driver) getIntent().getExtras().getSerializable(DRIVER);
        startActivity(StartOrderActivity.getIntent(this, driver));
        finish();
    }

    void attachKeyboardListeners() {
        mMoreInfoEditText.setOnTouchListener((view, motionEvent) -> {
            mMoreInfoEditText.setFocusable(true);
            mMoreInfoEditText.setFocusableInTouchMode(true);
            return false;
        });
        if (keyboardListenersAttached) {
            return;
        }
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            if (aLong == 10) {
                                startOrder();
                                mCompositeSubscription.unsubscribe();
                            }
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
    }

    @OnClick({R.id.driver_profile_item_next_btn, R.id.start_order_call_to_driver_btn,
            R.id.start_order_cancel_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_profile_item_next_btn:
                break;
            case R.id.start_order_call_to_driver_btn:
                break;
            case R.id.start_order_cancel_btn:
                onBackPressed();
                break;
        }
    }
}
