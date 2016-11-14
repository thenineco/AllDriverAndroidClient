package com.soberdriver.client.soberdriver.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.AddUserCarPresenter;
import com.soberdriver.client.soberdriver.presentation.view.AddUserCarView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.MainOrderActivity;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserCarActivity extends BaseAppActivity implements AddUserCarView {
    public static final String TAG = "AddUserCarActivity";
    @InjectPresenter
    AddUserCarPresenter mAddUserCarPresenter;
    @BindView(R.id.add_user_car_model_edit_text)
    SelectableEdithText mCarModelEditText;
    @BindView(R.id.add_user_car_color_edit_text)
    SelectableEdithText mCarColorEditText;
    @BindView(R.id.add_user_car_number_edit_text)
    SelectableEdithText mCarNumberEditText;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, AddUserCarActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_car);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_user_car_skip_text_view)
    public void onClick() {
        startActivity(MainOrderActivity.getIntent(this, true));
    }
}
