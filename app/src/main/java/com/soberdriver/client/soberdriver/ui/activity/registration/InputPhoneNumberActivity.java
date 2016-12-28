package com.soberdriver.client.soberdriver.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.User;
import com.soberdriver.client.soberdriver.presentation.presenter.InputPhoneNumberPresenter;
import com.soberdriver.client.soberdriver.presentation.view.InputPhoneNumberView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.MainOrderActivity;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;
import com.soberdriver.client.soberdriver.ui.view.SelectablePhoneEditText;
import com.soberdriver.client.soberdriver.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zest
 */


public class InputPhoneNumberActivity extends BaseAppActivity implements InputPhoneNumberView {

    @InjectPresenter
    InputPhoneNumberPresenter mInputPhoneNumberPresenter;

    @BindView(R.id.input_phone_number_info_text_view)
    AppCompatTextView mInputPhoneNumberInfoTextView;
    @BindView(R.id.input_phone_number_user_name_edit_text)
    SelectableEdithText mUserNameEditText;
    @BindView(R.id.input_phone_number_user_phone_number_edit_text)
    SelectablePhoneEditText mUserPhoneNumberEditText;
    @BindView(R.id.input_phone_number_send_user_data_btn)
    AppCompatButton mSendUserDataBtn;
    @BindView(R.id.input_phone_number_scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.toolbar_title_text_view)
    AppCompatTextView mToolbarTitleTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.input_phone_number_skip_text_view)
    AppCompatTextView mSkipTextView;
    @BindView(R.id.input_phone_number_pin_code_edit_text)
    SelectableEdithText mPinCodeEditText;
    @BindView(R.id.input_phone_number_pin_code_container)
    LinearLayout mCodeContainer;
    private boolean keyboardListenersAttached;
    private User mUser;


    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mScrollView.smoothScrollBy(0,
                            DisplayUtil.getDisplayHeight(InputPhoneNumberActivity.this));
                }
            };

    public static Intent getIntent(final Context context) {

        return new Intent(context, InputPhoneNumberActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_phone_number_activity_layout);
        ButterKnife.bind(this);
        attachKeyboardListeners();
        setViewsParam();
    }

    private void setViewsParam() {
        mUserPhoneNumberEditText.setTextViewStateChangesCallback(
                new SelectablePhoneEditText.TextViewStateChangesCallback() {
                    @Override
                    public void viewFocused(boolean focus) {

                    }

                    @Override
                    public void textChanged(String text) {
                        if (text.length() > 0) {
                            showSendButton();
                        } else {
                            hideSendButton();
                        }
                    }
                });

        mPinCodeEditText.setTextViewStateChangesCallback(
                new SelectablePhoneEditText.TextViewStateChangesCallback() {
                    @Override
                    public void viewFocused(boolean focus) {

                    }

                    @Override
                    public void textChanged(String text) {
                        if (text.length() == 4) {
                            mPinCodeEditText.setFocusable(false);
                            mInputPhoneNumberPresenter.sendPinCodeToServer(
                                    mUser.getUserPhoneNumber(), Integer.valueOf(text));
                        }
                    }
                });
    }

    private void hideSendButton() {
        mSendUserDataBtn.setVisibility(View.GONE);
        mSkipTextView.setVisibility(View.VISIBLE);
    }

    private void showSendButton() {
        mSendUserDataBtn.setVisibility(View.VISIBLE);
        mSkipTextView.setVisibility(View.GONE);
    }

    private void showPinContainer() {
        mCodeContainer.setVisibility(View.VISIBLE);
        hideSendButton();
    }

    private void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    @Override
    public void showSendPhoneNumberBtn() {

    }

    @Override
    public void startEnterSecurity(User user) {
        showPinContainer();
    }

    @Override
    public void startAddUserCarActivity() {
        startActivity(AddUserCarActivity.getIntent(this));
    }

    @OnClick({R.id.input_phone_number_send_user_data_btn, R.id.input_phone_number_skip_text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.input_phone_number_send_user_data_btn:
                clearEditTextFocus();
                sendAuthUserData();
                break;
            case R.id.input_phone_number_skip_text_view:
                startActivity(MainOrderActivity.getIntent(this, true));
                finish();
                break;
        }
    }

    private void sendAuthUserData() {
        String userName = mUserNameEditText.getText().toString();
        String userPhoneNumber = mUserPhoneNumberEditText.getText().toString();
        userPhoneNumber = userPhoneNumber.replaceAll(" ", "");
        userPhoneNumber = userPhoneNumber.replaceAll("-", "");
        mUser = new User(userName, userPhoneNumber);
        mInputPhoneNumberPresenter.sendUserData(mUser);
    }

    private void clearEditTextFocus() {
        mUserPhoneNumberEditText.setFocusable(false);
        mUserNameEditText.setFocusable(false);
    }
}
