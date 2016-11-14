package com.soberdriver.client.soberdriver.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zest .
 */

public class SelectableEdithText extends AppCompatEditText {
    private CharSequence hint;

    public SelectableEdithText(Context context) {
        super(context);
        hint = getHint();
    }

    public SelectableEdithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        hint = getHint();
    }

    public SelectableEdithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        hint = getHint();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (focused || !getText().toString().isEmpty()) {
            this.setHint(" ");
        } else {
            this.setHint(hint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
