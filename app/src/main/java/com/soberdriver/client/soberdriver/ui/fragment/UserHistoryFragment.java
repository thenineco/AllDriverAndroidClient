package com.soberdriver.client.soberdriver.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.mvp.models.Driver;
import com.soberdriver.client.soberdriver.presentation.presenter.UserHistoryPresenter;
import com.soberdriver.client.soberdriver.presentation.view.UserHistoryView;
import com.soberdriver.client.soberdriver.ui.activity.DriverProfileActivity;
import com.soberdriver.client.soberdriver.ui.adapter.user_history.UserHistoryAdapter;
import com.soberdriver.client.soberdriver.ui.adapter.user_history.UserHistoryViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserHistoryFragment extends BaseAppFragment implements UserHistoryView {
    public static final String TAG = "UserHistoryFragment";
    @InjectPresenter
    UserHistoryPresenter mUserHistoryPresenter;
    @BindView(R.id.user_history_recycler_view)
    RecyclerView mUserHistoryRecyclerView;

    public static UserHistoryFragment newInstance() {
        UserHistoryFragment fragment = new UserHistoryFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(getContext(),
                new UserHistoryViewHolder.UserHistoryItemClickListener() {
                    @Override
                    public void onClick(Driver driver) {
                        openDriverProfile();
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mUserHistoryRecyclerView.setLayoutManager(layoutManager);
        mUserHistoryRecyclerView.setAdapter(userHistoryAdapter);
    }

    private void openDriverProfile() {
        startActivity(DriverProfileActivity.getIntent(getContext()));
    }
}
