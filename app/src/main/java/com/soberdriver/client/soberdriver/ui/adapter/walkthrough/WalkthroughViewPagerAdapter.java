package com.soberdriver.client.soberdriver.ui.adapter.walkthrough;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soberdriver.client.soberdriver.R;

/**
 * Created by zest .
 */

public class WalkthroughViewPagerAdapter extends PagerAdapter {

    private Context mContext;

    public WalkthroughViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.walkthrough_item_layout, container,
                false);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
