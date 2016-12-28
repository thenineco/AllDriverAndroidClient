package com.soberdriver.client.soberdriver.ui.adapter.drivers;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.module.network.networkmodule.models.orders.Driver;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.ui.activity.SelectDriverActivity;

/**
 * Created by zest
 */

public class DriversViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final AppCompatTextView driverNameTextView;
    private final AppCompatTextView driverInfoTextView;
    private SelectDriverActivity.DriverSelectListener mDriverSelectListener;
    private Driver mDriver;

    public DriversViewHolder(View itemView,
            SelectDriverActivity.DriverSelectListener driverSelectListener) {
        super(itemView);
        mDriverSelectListener = driverSelectListener;
        driverNameTextView = (AppCompatTextView) itemView.findViewById(
                R.id.driver_profile_item_driver_name_text_view);
        driverInfoTextView = (AppCompatTextView) itemView.findViewById(
                R.id.driver_profile_item_driver_info_text_view);
        itemView.setOnClickListener(this);
    }

    public void bindViewHolder(Driver driver) {
        mDriver = driver;
        driverNameTextView.setText(driver.getName());
        driverInfoTextView.setText(
                "стаж " + String.valueOf(driver.getMark()) + " лет");

    }

    @Override
    public void onClick(View view) {
        mDriverSelectListener.DriverSelected(mDriver);
    }
}
