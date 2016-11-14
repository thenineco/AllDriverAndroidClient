package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.UserProfilePresenter;
import com.soberdriver.client.soberdriver.presentation.view.UserProfileView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends BaseAppActivity implements UserProfileView {
    public static final String TAG = "UserProfileActivity";
    @InjectPresenter
    UserProfilePresenter mUserProfilePresenter;
    @BindView(R.id.user_profile_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.user_profile_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.user_profile_avatar_image_view)
    CircleImageView mAvatarImageView;
    @BindView(R.id.user_profile_user_name)
    AppCompatTextView mUserName;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserProfileActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setToolbarTitle("Профиль");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.user_profile_back_btn)
    public void onClick() {
        finish();
    }
}
