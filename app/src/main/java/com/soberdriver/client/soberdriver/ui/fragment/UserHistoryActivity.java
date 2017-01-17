package com.soberdriver.client.soberdriver.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.module.network.networkmodule.models.driver.Driver;
import com.soberdriver.client.soberdriver.presentation.presenter.UserHistoryPresenter;
import com.soberdriver.client.soberdriver.presentation.view.UserHistoryView;
import com.soberdriver.client.soberdriver.ui.activity.BaseAppActivity;
import com.soberdriver.client.soberdriver.ui.activity.DriverProfileActivity;
import com.soberdriver.client.soberdriver.ui.adapter.user_history.UserHistoryAdapter;
import com.soberdriver.client.soberdriver.ui.adapter.user_history.UserHistoryViewHolder;
import com.soberdriver.client.soberdriver.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserHistoryActivity extends BaseAppActivity implements UserHistoryView {
    public static final String TAG = "UserHistoryActivity";
    @InjectPresenter
    UserHistoryPresenter mUserHistoryPresenter;
    @BindView(R.id.user_history_recycler_view)
    RecyclerView mUserHistoryRecyclerView;
    @BindView(R.id.user_history_toolbar)
    AppCustomToolbar mToolbar;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserHistoryActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_history);
        ButterKnife.bind(this);

        UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(this,
                new UserHistoryViewHolder.UserHistoryItemClickListener() {
                    @Override
                    public void onClick(Driver driver) {
                        openDriverProfile();
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mUserHistoryRecyclerView.setLayoutManager(layoutManager);
        mUserHistoryRecyclerView.setAdapter(userHistoryAdapter);
    }

    private void openDriverProfile() {
        startActivity(DriverProfileActivity.getIntent(this));
    }

    @OnClick(R.id.user_history_back_btn)
    public void onClick() {
        finish();
    }
}
