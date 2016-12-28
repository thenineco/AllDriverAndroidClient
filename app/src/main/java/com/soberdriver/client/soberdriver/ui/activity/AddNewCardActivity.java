package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.AddNewCardPresenter;
import com.soberdriver.client.soberdriver.presentation.view.AddNewCardView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCardActivity extends BaseAppActivity implements AddNewCardView {
    public static final String TAG = "AddNewCardActivity";
    @InjectPresenter
    AddNewCardPresenter mAddNewCardPresenter;
    @BindView(R.id.add_new_card_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.add_new_card_close_btn)
    AppCompatImageView mCloseBtn;
    @BindView(R.id.add_new_card_card_number_edit_text)
    SelectableEdithText mCardNumberEditText;
    @BindView(R.id.add_new_card_actual_time_edit_text)
    SelectableEdithText mCardActualTimeEditText;
    @BindView(R.id.add_new_card_security_cod_edit_text)
    SelectableEdithText mSecurityCodEditText;
    @BindView(R.id.add_new_card_take_card_photo_text_view)
    AppCompatTextView mTakeCardPhotoTextView;
    @BindView(R.id.add_new_card_save_btn)
    AppCompatButton mSaveBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, AddNewCardActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Банковская карта");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.add_new_card_close_btn, R.id.add_new_card_take_card_photo_text_view,
            R.id.add_new_card_save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_new_card_close_btn:
                finish();
                break;
            case R.id.add_new_card_take_card_photo_text_view:
                break;
            case R.id.add_new_card_save_btn:
                finish();
                break;
        }
    }
}
