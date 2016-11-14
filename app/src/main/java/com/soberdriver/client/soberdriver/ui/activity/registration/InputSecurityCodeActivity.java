package com.soberdriver.client.soberdriver.ui.activity.registration;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.InputSecurityCodePresenter;
import com.soberdriver.client.soberdriver.presentation.view.InputSecurityCodeView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.MainOrderActivity;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;
import com.soberdriver.client.soberdriver.ui.view.SelectablePhoneEditText;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zest .
 */

public class InputSecurityCodeActivity extends BaseAppActivity implements InputSecurityCodeView {

    public static final String USER_NAME = "user_name";

    public static final String USER_PHONE_NUMBER = "user_phone_number";

    @InjectPresenter
    InputSecurityCodePresenter mInputSecurityCodePresenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.input_security_code_user_name_edit_text)
    SelectableEdithText mUserNameEditText;
    @BindView(R.id.input_security_code_user_phone_number_edit_text)
    SelectablePhoneEditText mUserPhoneNumberEditText;
    @BindView(R.id.input_security_code_code_edit_text)
    SelectableEdithText mCodeEditText;
    @BindView(R.id.input_security_code_send_code_btn)
    AppCompatButton mCodeSendCodeBtn;
    @BindView(R.id.input_security_code_info_text_view)
    AppCompatTextView mInputSecurityCodeInfoTextView;
    @BindView(R.id.input_security_code_scroll_view)
    ScrollView mScrollView;
    private boolean keyboardListenersAttached;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_security_code_activity_layout);
        ButterKnife.bind(this);
        setViewArguments();
        attachKeyboardListeners();
    }

    private void setViewArguments() {
        String userName = getIntent().getStringExtra(USER_NAME);
        String userPhoneNumber = getIntent().getStringExtra(USER_PHONE_NUMBER);
        mUserNameEditText.setText(userName);
        mUserPhoneNumberEditText.setText(userPhoneNumber);
        mCodeEditText.requestFocus();
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    mScrollView.smoothScrollBy(0,
                            DisplayUtil.getDisplayHeight(InputSecurityCodeActivity.this));
                }
            };


    void attachKeyboardListeners() {
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

    @Override
    public void sendSecurityCode(String securityCode) {

    }

    @OnClick(R.id.input_security_code_send_code_btn)
    public void onClick() {

        startActivity(AddUserCarActivity.getIntent(this));
    }
}
