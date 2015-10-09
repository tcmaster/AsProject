package com.android.tonight8.ui.view.somonthemotionkeyboard.inputboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by LiXiaoSong
 * Date: 2015/9/25 0025
 */
public class ChatLayout extends BaseSoftInputLayout{

    public ChatLayout(Context context) {
        super(context);
    }

    public ChatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChatLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void inflateView() {

    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected View getContainer() {
        return null;
    }

    @Override
    protected View getFrame() {
        return null;
    }

    @Override
    public EditText getEditText() {
        return null;
    }

    @Override
    protected View getBtnKeyBoard() {
        return null;
    }
}
