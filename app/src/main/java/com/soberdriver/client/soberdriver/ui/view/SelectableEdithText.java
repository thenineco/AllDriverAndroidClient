package com.soberdriver.client.soberdriver.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zest .
 */

public class SelectableEdithText extends AppCompatEditText {
    private CharSequence hint;
    private SelectablePhoneEditText.TextViewStateChangesCallback mTextViewStateChangesCallback;

    public SelectableEdithText(Context context) {
        super(context);
        initView();
    }

    public SelectableEdithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectableEdithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mTextViewStateChangesCallback != null) {
                    mTextViewStateChangesCallback.textChanged(getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
        if (!isFocusable()) {
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
            SelectablePhoneEditText.TextViewStateChangesCallback textViewStateChangesCallback) {
        mTextViewStateChangesCallback = textViewStateChangesCallback;
    }

    public interface TextViewStateChangesCallback {
        void viewFocused(boolean focus);

        void textChanged(String text);
    }
}
