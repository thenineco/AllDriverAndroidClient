package com.soberdriver.client.soberdriver.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.soberdriver.client.soberdriver.R;

/**
 * Created by zest .
 */

public class AppCustomToolbar extends FrameLayout {
    private AppCompatTextView mTitleTextView;
    private Toolbar mToolbar;

    public AppCustomToolbar(Context context) {
        super(context);
        initView();
    }

    public AppCustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public AppCustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AppCustomToolbar(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();

    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.app_toolbar, this, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTitleTextView = (AppCompatTextView) view.findViewById(R.id.toolbar_title_text_view);
        mToolbar.setTitle("");
        addView(view);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    public void setToolbarTitle(String title) {
        mTitleTextView.setText(title);
    }
}
