package com.soberdriver.client.soberdriver.ui.adapter.user_history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soberdriver.client.soberdriver.R;
import com.module.network.networkmodule.models.driver.Driver;

import java.util.List;

/**
 * Created by zest .
 */

public class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryViewHolder> {

    private List<Driver> mDrivers;
    private Context mContext;
    private UserHistoryViewHolder.UserHistoryItemClickListener mUserHistoryItemClickListener;

    public UserHistoryAdapter(Context context,
            UserHistoryViewHolder.UserHistoryItemClickListener userHistoryItemClickListener) {
        mContext = context;
        mUserHistoryItemClickListener = userHistoryItemClickListener;
    }

    @Override
    public UserHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.user_history_driver_item, parent, false);
        return new UserHistoryViewHolder(view, mUserHistoryItemClickListener);
    }

    @Override
    public void onBindViewHolder(UserHistoryViewHolder holder, int position) {
        holder.bindView(null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setDrivers(List<Driver> drivers) {
        mDrivers = drivers;
    }
}
