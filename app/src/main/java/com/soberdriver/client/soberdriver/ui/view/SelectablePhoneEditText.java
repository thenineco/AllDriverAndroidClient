package com.soberdriver.client.soberdriver.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zest .
 */

public class SelectablePhoneEditText extends AppCompatEditText {

    private CharSequence hint;
    private TextViewStateChangesCallback mTextViewStateChangesCallback;

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
        addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if (mTextViewStateChangesCallback != null) {
                    mTextViewStateChangesCallback.textChanged(getText().toString());
                }
            }
        });
        hint = getHint();
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (mTextViewStateChangesCallback != null) {
            mTextViewStateChangesCallback.viewFocused(focused);
        }

        if (focused || !getText().toString().isEmpty()) {
            this.setHint(" ");
        } else {
            this.setHint(hint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isFocusable()){
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void setTextViewStateChangesCallback(
            TextViewStateChangesCallback textViewStateChangesCallback) {
        mTextViewStateChangesCallback = textViewStateChangesCallback;
    }

    public interface TextViewStateChangesCallback {
        void viewFocused(boolean focus);

        void textChanged(String text);
    }
}
