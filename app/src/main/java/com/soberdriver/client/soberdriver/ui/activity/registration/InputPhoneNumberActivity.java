package com.soberdriver.client.soberdriver.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.User;
import com.soberdriver.client.soberdriver.presentation.presenter.InputPhoneNumberPresenter;
import com.soberdriver.client.soberdriver.presentation.view.InputPhoneNumberView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.DriverProfileActivity;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;
import com.soberdriver.client.soberdriver.ui.view.SelectablePhoneEditText;

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


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InputPhoneNumberActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_phone_number_activity_layout);
        ButterKnife.bind(this);
//        setSupportActionBar(mToolbar);
    }

    @Override
    public void showSendPhoneNumberBtn() {

    }

    @Override
    public void startEnterSecurity(User user) {
        Intent intent = new Intent(this, InputSecurityCodeActivity.class);
        intent.putExtra(InputSecurityCodeActivity.USER_NAME, user.getUserName());
        intent.putExtra(InputSecurityCodeActivity.USER_PHONE_NUMBER, user.getUserPhoneNumber());
        startActivity(intent);

    }

    @OnClick(R.id.input_phone_number_send_user_data_btn)
    public void onClick() {
        String userName = mUserNameEditText.getText().toString();
        String userPhoneNumber = mUserPhoneNumberEditText.getText().toString();
        User user = new User(userName, userPhoneNumber);
        mInputPhoneNumberPresenter.registerUser(user);
    }
}
