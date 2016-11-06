package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.DriverFilterPresenter;
import com.soberdriver.client.soberdriver.presentation.view.DriverFilterView;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DriverFilterActivity extends BaseAppActivity implements DriverFilterView {
    public static final String TAG = "DriverFilterActivity";
    @InjectPresenter
    DriverFilterPresenter mPresenter;
    @BindView(R.id.driver_filter_close_btn)
    AppCompatImageView mCloseBtn;
    @BindView(R.id.driver_filter_a_category_btn)
    AppCompatButton mACategoryBtn;
    @BindView(R.id.driver_filter_b_category_btn)
    AppCompatButton mBCategoryBtn;
    @BindView(R.id.driver_filter_c_category_btn)
    AppCompatButton mCCategoryBtn;
    @BindView(R.id.driver_filter_d_category_btn)
    AppCompatButton mDCategoryBtn;
    @BindView(R.id.driver_filter_e_category_btn)
    AppCompatButton mECategoryBtn;
    @BindView(R.id.driver_filter_international_docs_swich)
    SwitchCompat mInternationalDocsSwich;
    @BindView(R.id.driver_filter_male_btn)
    AppCompatButton mMaleBtn;
    @BindView(R.id.driver_filter_female_btn)
    AppCompatButton mFemaleBtn;
    @BindView(R.id.driver_filter_driver_skin_swich)
    SwitchCompat mSkinSwich;
    @BindView(R.id.driver_filter_comment_for_driver_edit_text)
    AppCompatEditText mCommentForDriverEditText;
    @BindView(R.id.driver_filter_smoker_swich)
    SwitchCompat mSmokerSwich;
    @BindView(R.id.driver_filter_done_btn)
    AppCompatButton mrDoneBtn;
    @BindView(R.id.driver_filter_scroll_view)
    ScrollView mScrollView;

    private Unbinder unbinder;
    private boolean keyboardListenersAttached;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DriverFilterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_driver_filter);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        attachKeyboardListeners();
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    try {
                        mScrollView.smoothScrollBy(0,
                                DisplayUtil.getDisplayHeight(DriverFilterActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };


    void attachKeyboardListeners() {
        mCommentForDriverEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mCommentForDriverEditText.setFocusable(true);
                mCommentForDriverEditText.setFocusableInTouchMode(true);
                return false;
            }
        });
        if (keyboardListenersAttached) {
            return;
        }
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
        keyboardListenersAttached = true;
    }


    @OnClick({R.id.driver_filter_a_category_btn, R.id.driver_filter_b_category_btn,
            R.id.driver_filter_c_category_btn, R.id.driver_filter_d_category_btn,
            R.id.driver_filter_e_category_btn, R.id.driver_filter_male_btn,
            R.id.driver_filter_female_btn, R.id.driver_filter_done_btn,
            R.id.driver_filter_close_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_filter_a_category_btn:
                break;
            case R.id.driver_filter_b_category_btn:
                break;
            case R.id.driver_filter_c_category_btn:
                break;
            case R.id.driver_filter_d_category_btn:
                break;
            case R.id.driver_filter_e_category_btn:
                break;
            case R.id.driver_filter_male_btn:
                break;
            case R.id.driver_filter_female_btn:
                break;
            case R.id.driver_filter_done_btn:
                closeFragment();
                break;
            case R.id.driver_filter_close_btn:
                closeFragment();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (keyboardListenersAttached) {
            mScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardLayoutListener);
        }
    }

    @Override
    public void closeFragment() {
        onBackPressed();
    }
}
