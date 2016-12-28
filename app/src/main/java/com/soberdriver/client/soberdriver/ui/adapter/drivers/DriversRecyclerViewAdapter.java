package com.soberdriver.client.soberdriver.ui.adapter.drivers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.module.network.networkmodule.models.orders.Driver;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.ui.activity.SelectDriverActivity;

import java.util.List;

/**
 * Created by zest
 */

public class DriversRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Driver> mDrivers;
    private SelectDriverActivity.DriverSelectListener mDriverSelectListener;

    public DriversRecyclerViewAdapter(Context context,
            List<Driver> drivers, SelectDriverActivity.DriverSelectListener mDriverSelectListener) {
        mContext = context;
        mDrivers = drivers;
        this.mDriverSelectListener = mDriverSelectListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.driver_profile_item, parent, false);
        return new DriversViewHolder(view, mDriverSelectListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Driver driver = mDrivers.get(position);
        ((DriversViewHolder) holder).bindViewHolder(driver);
    }

    @Override
    public int getItemCount() {
        return mDrivers.size();
    }

}
