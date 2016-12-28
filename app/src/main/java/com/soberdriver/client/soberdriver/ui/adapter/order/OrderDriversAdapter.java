package com.soberdriver.client.soberdriver.ui.adapter.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.soberdriver.client.soberdriver.R;
import com.module.network.networkmodule.models.orders.Driver;

import java.util.List;

/**
 * Created by zest .
 */

public class OrderDriversAdapter extends
        ExpandableRecyclerAdapter<DriversParentViewHolder, DriverChildViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private List<Driver> mDriverList;
    private int orderCount = 0;


    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     *
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public OrderDriversAdapter(Context context,
            @NonNull List<? extends ParentListItem> parentItemList,
            OnItemClickListener onItemClickListener) {
        super(parentItemList);
        mOnItemClickListener = onItemClickListener;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public DriversParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mLayoutInflater.inflate(R.layout.expanable_view_header_layout, parentViewGroup,
                false);
        return new DriversParentViewHolder(view);
    }

    @Override
    public DriverChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mLayoutInflater.inflate(R.layout.expanable_view_container_layout,
                childViewGroup,
                false);
        return new DriverChildViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindParentViewHolder(DriversParentViewHolder parentViewHolder, int position,
            ParentListItem parentListItem) {
        System.out.println(orderCount);
        parentViewHolder.bindViewGolder("Водитель " + (orderCount + 1));
        orderCount++;
    }

    @Override
    public void onBindChildViewHolder(DriverChildViewHolder childViewHolder, int position,
            Object childListItem) {
    }


    public interface OnItemClickListener {
        void onItemClick(View v);
    }



}
