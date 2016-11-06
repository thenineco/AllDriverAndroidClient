package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.DriverProfilePresenter;
import com.soberdriver.client.soberdriver.presentation.view.DriverProfileView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DriverProfileActivity extends BaseAppActivity implements DriverProfileView {
    public static final String TAG = "DriverProfileActivity";
    @InjectPresenter
    DriverProfilePresenter mDriverProfilePresenter;
    @BindView(R.id.driver_profile_close_btn)
    AppCompatImageView mCloseBtn;
    @BindView(R.id.driver_profile_avatar_image_view)
    CircleImageView mProfileAvatarImageView;
    @BindView(R.id.driver_profile_driver_name_Text_view)
    AppCompatTextView mDriverNameTextView;
    @BindView(R.id.driver_profile_driver_info_text_view)
    AppCompatTextView mDriverInfoTextView;
    @BindView(R.id.driver_profile_driver_driver_experience_info_text_view)
    AppCompatTextView mDriverExperienceInfoTextView;
    @BindView(R.id.driver_profile_star_btn)
    AppCompatImageView mProfileStarBtn;
    @BindView(R.id.driver_profile_driver_characteristics_text_view)
    AppCompatTextView mDriverCharacteristicsTextView;
    @BindView(R.id.driver_profile_docs_category_text_view)
    AppCompatTextView mDocsCategoryTextView;
    @BindView(R.id.driver_profile_international_docs_text_view)
    AppCompatTextView mInternationalDocsTextView;
    @BindView(R.id.driver_profile_driver_cars)
    LinearLayout mDriverCarsContainer;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DriverProfileActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.driver_profile_close_btn, R.id.driver_profile_star_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_profile_close_btn:
                onBackPressed();
                break;
            case R.id.driver_profile_star_btn:
                break;
        }
    }
}
