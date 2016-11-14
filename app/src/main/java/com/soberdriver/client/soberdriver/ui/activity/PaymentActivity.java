package com.soberdriver.client.soberdriver.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.presentation.presenter.PaymentPresenter;
import com.soberdriver.client.soberdriver.presentation.view.PaymentView;
import com.soberdriver.client.soberdriver.ui.fragment.UserCardsFragment;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseAppActivity implements PaymentView {
    public static final String TAG = "PaymentActivity";
    @InjectPresenter
    PaymentPresenter mPaymentPresenter;
    @BindView(R.id.payment_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.payment_back_btn)
    AppCompatImageView mBackBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        initToolBar();
        startUserCardsFragment();
    }

    private void startUserCardsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.payment_fragments_container, UserCardsFragment.newInstance())
                .commit();
    }

    private void initToolBar() {
        mToolbar.setToolbarTitle("Оплата");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.payment_back_btn)
    public void onClick() {
        finish();
    }
}
