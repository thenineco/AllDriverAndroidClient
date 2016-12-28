package com.soberdriver.client.soberdriver.ui.adapter.order;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zest .
 */

public class DriverItem implements ParentListItem {


    @Override
    public List<?> getChildItemList() {
        return Arrays.asList(1);
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
