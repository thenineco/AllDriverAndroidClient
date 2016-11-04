package com.soberdriver.client.soberdriver.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.AttributeSet;

/**
 * Created by zest .
 */

public class SelectablePhoneEditText extends AppCompatEditText {

    private CharSequence hint;

    public SelectablePhoneEditText(Context context) {
        super(context);

        initView();
    }


    public SelectablePhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectablePhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        addTextChangedListener(new PhoneNumberFormattingTextWatcher());
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
    public boolean hasOverlappingRendering() {
        return false;
    }
}
