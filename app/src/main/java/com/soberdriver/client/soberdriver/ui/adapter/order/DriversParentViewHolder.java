package com.soberdriver.client.soberdriver.ui.adapter.order;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.soberdriver.client.soberdriver.R;

/**
 * Created by zest .
 */

public class DriversParentViewHolder extends ParentViewHolder {

    private AppCompatTextView parentItemLabel;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DriversParentViewHolder(View itemView) {
        super(itemView);
        parentItemLabel = (AppCompatTextView) itemView.findViewById(R.id.parent_item_label);
    }

    public void bindViewGolder(String label) {
        parentItemLabel.setText(label);
    }
}
