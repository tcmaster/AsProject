package com.android.tonight8.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.lidroid.xutils.util.LogUtils;


/**
 * Created by LiXiaoSong
 * Date: 2015/9/23 0023
 */
public class LiveContainer extends LinearLayout{
    private View judgeView;
    private InterruptTouch mInterrupt;
    public LiveContainer(Context context) {
        super(context);
    }

    public LiveContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiveContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LiveContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setInterruptInterface(InterruptTouch callBack,View v){
        this.mInterrupt = callBack;
        this.judgeView = v;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            if(judgeView != null) {
                if (judgeView.getVisibility() == View.VISIBLE) {
                    float x = ev.getX();
                    float y = ev.getY();
                    if ((x > judgeView.getRight() || x < judgeView.getLeft()) || (y > judgeView.getBottom() || y < judgeView.getTop())) {
                        if (mInterrupt != null) mInterrupt.onInterrupt();
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public interface InterruptTouch{
        public void onInterrupt();
    }
}
