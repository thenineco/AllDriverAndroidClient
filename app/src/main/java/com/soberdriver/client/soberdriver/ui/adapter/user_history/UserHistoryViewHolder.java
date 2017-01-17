package com.soberdriver.client.soberdriver.ui.adapter.user_history;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.module.network.networkmodule.models.driver.Driver;

/**
 * Created by zest .
 */
public class UserHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private UserHistoryItemClickListener mUserHistoryItemClickListener;
    private Driver mDriver;

    UserHistoryViewHolder(View itemView,
            UserHistoryItemClickListener userHistoryItemClickListener) {
        super(itemView);
        mUserHistoryItemClickListener = userHistoryItemClickListener;
        itemView.setOnClickListener(this);
    }

    void bindView(Driver driver) {
        mDriver = driver;
    }

    @Override
    public void onClick(View view) {
        mUserHistoryItemClickListener.onClick(mDriver);
    }

    public interface UserHistoryItemClickListener {
        void onClick(Driver driver);
    }
}
