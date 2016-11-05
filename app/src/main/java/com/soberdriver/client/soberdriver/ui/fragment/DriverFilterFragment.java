package com.soberdriver.client.soberdriver.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DriverFilterFragment extends BaseAppFragment implements DriverFilterView {
    public static final String TAG = "DriverFilterFragment";
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

    public static DriverFilterFragment newInstance() {
        DriverFilterFragment fragment = new DriverFilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_filter, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachKeyboardListeners();
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    try {
                        mScrollView.smoothScrollBy(0,
                                DisplayUtil.getDisplayHeight(getContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };


    void attachKeyboardListeners() {
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void closeFragment() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
