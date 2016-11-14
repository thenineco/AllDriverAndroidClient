package com.soberdriver.client.soberdriver.ui.adapter.order;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.soberdriver.client.soberdriver.R;

/**
 * Created by zest .
 */

public class DriverChildViewHolder extends ChildViewHolder implements View.OnClickListener {

    private LinearLayout mSelectStartLocationBtn;
    private OrderDriversAdapter.OnItemClickListener mOnItemClickListener;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DriverChildViewHolder(View itemView,
            OrderDriversAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        mSelectStartLocationBtn = (LinearLayout) itemView.findViewById(
                R.id.order_select_start_location_btn);
        mOnItemClickListener = onItemClickListener;
        mSelectStartLocationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mOnItemClickListener.onItemClick(view);

    }
}
