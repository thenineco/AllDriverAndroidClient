package com.soberdriver.client.soberdriver.ui.adapter.order;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.soberdriver.client.soberdriver.R;
import com.soberdriver.client.soberdriver.ui.view.SelectablePhoneEditText;

/**
 * Created by zest .
 */

public class DriverChildViewHolder extends ChildViewHolder implements View.OnClickListener {

    private LinearLayout mSelectStartLocationTextView;
    private OrderDriversAdapter.OnItemClickListener mOnItemClickListener;
    private AppCompatTextView mDriverDetailsTextView;
    private AppCompatCheckBox forAllOrdersCheckBox;
    private SelectablePhoneEditText clientPhoneNumberEditText;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DriverChildViewHolder(View itemView,
            OrderDriversAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        mSelectStartLocationTextView = (LinearLayout) itemView.findViewById(
                R.id.order_select_start_location_btn);
        mDriverDetailsTextView = (AppCompatTextView) itemView.findViewById(
                R.id.order_driver_details_btn);
        forAllOrdersCheckBox = (AppCompatCheckBox) itemView.findViewById(
                R.id.order_for_all_orders_check_box);
        clientPhoneNumberEditText = (SelectablePhoneEditText) itemView.findViewById(
                R.id.order_client_phone_edit_text);
        mOnItemClickListener = onItemClickListener;

        setViewParams();
    }

    private void setViewParams() {
        mSelectStartLocationTextView.setOnClickListener(this);
        mDriverDetailsTextView.setOnClickListener(this);
        forAllOrdersCheckBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mOnItemClickListener.onItemClick(view);
    }

}
