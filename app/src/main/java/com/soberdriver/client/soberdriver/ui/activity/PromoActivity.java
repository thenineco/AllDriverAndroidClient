package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.PromoPresenter;
import com.soberdriver.client.soberdriver.presentation.view.PromoView;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;
import com.soberdriver.client.soberdriver.ui.view.SelectableEdithText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromoActivity extends BaseAppActivity implements PromoView {
    public static final String TAG = "PromoActivity";
    @InjectPresenter
    PromoPresenter mPromoPresenter;
    @BindView(R.id.promo_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.promo_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.promo_cod_edit_text)
    SelectableEdithText mPromoCodEditText;
    @BindView(R.id.promo_get_discount_btn)
    AppCompatButton mGetDiscountBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, PromoActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setToolbarTitle("Промо-код");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.promo_back_btn, R.id.promo_get_discount_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.promo_back_btn:
                finish();
                break;
            case R.id.promo_get_discount_btn:
                break;
        }
    }
}
