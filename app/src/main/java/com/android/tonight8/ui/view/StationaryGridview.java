package com.android.tonight8.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 类描述:
 * 创建人：asus
 * 创建时间：2015/8/31 16:03
 * 修改时间：2015/8/31 16:03
 * 修改备注：
 */

public class StationaryGridview extends GridView {
    public StationaryGridview(Context context) {
        super(context);
    }

    public StationaryGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public StationaryGridview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //重写dispatchTouchEvent方法禁止GridView滑动
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
