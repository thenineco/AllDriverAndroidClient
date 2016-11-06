package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.StartOrderPresenter;
import com.soberdriver.client.soberdriver.presentation.view.StartOrderView;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StartOrderActivity extends BaseAppActivity implements StartOrderView {
    public static final String TAG = "StartOrderActivity";
    @InjectPresenter
    StartOrderPresenter mStartOrderPresenter;
    @BindView(R.id.driver_profile_item_avatar_image_view)
    CircleImageView mAvatarImageView;
    @BindView(R.id.driver_profile_item_driver_name_text_view)
    AppCompatTextView mDriverNameTextView;
    @BindView(R.id.driver_profile_item_driver_info_text_view)
    AppCompatTextView mDriverInfoTextView;
    @BindView(R.id.driver_profile_item_next_btn)
    AppCompatImageView mProfileItemNextBtn;
    @BindView(R.id.start_order_timer_text_view)
    AppCompatTextView mTimerTextView;
    @BindView(R.id.start_order_trip_price_text_view)
    AppCompatTextView mTripPriceTextView;
    @BindView(R.id.start_order_payment_method_text_view)
    AppCompatTextView mPaymentMethodTextView;
    @BindView(R.id.start_order_payment_method_btn)
    LinearLayout mPaymentMethodBtn;
    @BindView(R.id.start_order_call_to_driver_btn)
    AppCompatButton mCallToDriverBtn;
    @BindView(R.id.start_order_cancel_btn)
    AppCompatButton mCancelBtn;
    @BindView(R.id.start_order_more_info_edit_text)
    AppCompatEditText mMoreInfoEditText;
    @BindView(R.id.start_order_scroll_view)
    ScrollView mScrollView;
    private boolean keyboardListenersAttached;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, StartOrderActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);
        ButterKnife.bind(this);
        attachTextInputListeners();
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mScrollView.smoothScrollBy(0,
                            DisplayUtil.getDisplayHeight(StartOrderActivity.this));
                }
            };


    void attachTextInputListeners() {
        mMoreInfoEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mMoreInfoEditText.setFocusable(true);
                mMoreInfoEditText.setFocusableInTouchMode(true);
                return false;
            }
        });
        if (keyboardListenersAttached) {
            return;
        }
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (keyboardListenersAttached) {
            mScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardLayoutListener);
        }
    }


    @OnClick({R.id.driver_profile_item_next_btn, R.id.start_order_payment_method_btn,
            R.id.start_order_call_to_driver_btn, R.id.start_order_cancel_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_profile_item_next_btn:
                openDriverProfile();
                break;
            case R.id.start_order_payment_method_btn:
                break;
            case R.id.start_order_call_to_driver_btn:
                break;
            case R.id.start_order_cancel_btn:
                onBackPressed();
                break;
        }
    }

    private void openDriverProfile() {
        startActivity(DriverProfileActivity.getIntent(this));
    }
}
