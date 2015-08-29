package com.android.tonight8.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.android.tonight8.utils.Utils;

/**
 * 用于现场直播的ScrollView
 * Created by LiXiaoSong
 * Date: 2015/8/26 0026
 */
public class ForLiveScrollView extends ScrollView {
    private float lastScrollY;
    private ForLiveScrollListener mListener;
    private int scrollValue;//下滑的值
    private boolean isOpenUpDowm = true;//是否开启上拉下拉功能
    private boolean isInDropDown = false;
    private boolean isInPullUp = false;
    private int marginTop = 0;//初始marginTop值
    private int increment;//增量值

    public ForLiveScrollView(Context context) {
        super(context);
    }

    public ForLiveScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForLiveScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ForLiveScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastScrollY = ev.getY();
            if (increment == 0) {
                increment = Utils.dip2px(getContext(), 10);
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            //滑动完成，可以判断当前滑动距离
            float nowScrollY = ev.getY();
            if (isOpenUpDowm && (nowScrollY - lastScrollY > 15) && !isInDropDown) {//大于50像素，认为是一次有效的下拉
                if (mListener != null) {
                    FrameLayout.LayoutParams flp = ((FrameLayout.LayoutParams) this.getLayoutParams());
                    if (marginTop == 0) marginTop = flp.topMargin;
                    //当ScrollView下拉到一定范围，并且下拉到某个高度，进行下拉刷新
                    if (getScrollY() < getTop() - increment) {
                        flp.topMargin = 0;
                        setLayoutParams(flp);
                        isInDropDown = true;
                        mListener.onDropDown(nowScrollY);
                    }
                }
            } else if (isOpenUpDowm && (nowScrollY - lastScrollY < -15) && !isInPullUp) {//小于-50像素，认为是一次有效的上拉
                View v = getChildAt(0);//ScrollView里的linearLayout布局
                if (v.getMeasuredHeight() <= getHeight() + getScrollY()) {
                    isInPullUp = true;
                    mListener.onPullUp(nowScrollY);
                }
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    public void enableOpenOrDown() {
        isOpenUpDowm = true;
    }

    public void disableOpenOrDown() {
        isOpenUpDowm = false;
    }

    /**
     * 完成下拉时调用
     */
    public void onFinishDropDown() {
        if (isOpenUpDowm && isInDropDown) {
            FrameLayout.LayoutParams flp = ((FrameLayout.LayoutParams) this.getLayoutParams());
            flp.topMargin = marginTop;
            setLayoutParams(flp);
            isInDropDown = false;
        }
    }

    /**
     * 完成上拉加载更多时使用
     */
    public void onFinishPullUp() {
        if (isOpenUpDowm && isInPullUp) {
            isInPullUp = false;
        }
    }

    public void setOnScrollListener(ForLiveScrollListener listener) {
        this.mListener = listener;
    }

    public interface ForLiveScrollListener {
        /**
         * 有效下拉时调用
         */
        void onDropDown(float nowScrollY);

        void onPullUp(float nowScrollY);
    }
}
