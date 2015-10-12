package com.android.tonight8.function;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/31 0031
 */

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.tonight8.utils.Utils;


/**
 * 判断当前的ListView的滑动方向的监听
 */
public class ScrollTopOrBottomListener implements AbsListView.OnScrollListener {
    private int mListViewFirstItem = 0;
    //listView中第一项的在屏幕中的位置
    private int mScreenY = 0;
    //是否向上滚动
    private boolean mIsScrollToUp = false;
    private ListView mListView;
    private int lastVisiblePos = 0;
    private int lastPos = 0;
    private boolean lastDirection = false;//上次的滑动方向，true为向上，false为向下
    private int isTouchUp;//当前触摸事件是否已经抬起，若抬起，将不接受反弹的滑动事件
    private boolean isInBottomFiling = false;//是否在底部滑动
    private int headerTop;//向上移动时，header移动的最终位置
    private int headerBottom;//向下移动时，header移动的最终位置
    private int headerHeight;//头部的高度
    private int tvHeight;//字幕的高度
    private View trueHeader;

    public ScrollTopOrBottomListener(final ListView mListView, View trueHeader) {
        this.mListView = mListView;
        this.trueHeader = trueHeader;
        headerTop = Utils.dip2px(mListView.getContext(), -310);
        headerBottom = Utils.dip2px(mListView.getContext(), 0);
        headerHeight = Utils.dip2px(mListView.getContext(), 350);
        tvHeight = Utils.dip2px(mListView.getContext(), -40);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        isTouchUp = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if ((firstVisibleItem + totalItemCount == totalItemCount && isTouchUp == SCROLL_STATE_FLING) || firstVisibleItem == 0 && isTouchUp == SCROLL_STATE_FLING) {//滑动已经到底部/顶部，且呈飞滑状态
            isInBottomFiling = true;//滑动到底部，如果当前仍处于filling状态，则不进行滑动
        }
        if (isTouchUp != SCROLL_STATE_FLING) isInBottomFiling = false;//闲置状态，可进行滑动
        if (mListView.getChildCount() > 0) {
            boolean isScrollToUp = false;
            View childAt = mListView.getChildAt(0);//第一个可见元素
            if (childAt == null) return;
            int[] location = new int[2];
            childAt.getLocationOnScreen(location);
            if (firstVisibleItem != mListViewFirstItem) {
                isScrollToUp = firstVisibleItem > mListViewFirstItem;
                mListViewFirstItem = firstVisibleItem;
                mScreenY = location[1];
            } else {
                if (mScreenY > location[1]) {
                    isScrollToUp = true;
                } else if (mScreenY < location[1]) {
                    isScrollToUp = false;
                }
                mScreenY = location[1];
            }
            int translateY = 0;
            if (isScrollToUp && totalItemCount > visibleItemCount) {//当前滑动方向向上
                if (isInBottomFiling && lastDirection || !isInBottomFiling) {//防止反弹时候的滑动
                    if (lastVisiblePos != firstVisibleItem) {
                        translateY = lastPos - 0;//上个item的距离
                        translateY += childAt.getMeasuredHeight() - childAt.getBottom();//本item的距离
                    } else {
                        translateY = lastPos - childAt.getBottom();//本次向上滑动的距离
                    }
                    lastDirection = true;
                    //调整listView的内容
                    if (translateY > 0 && trueHeader.getTranslationY() > headerTop) {//在可滑动范围内
                        float translationResult = trueHeader.getTranslationY() - translateY;
                        if (translationResult < headerTop) translationResult = headerTop;
                        trueHeader.setTranslationY(translationResult);
                    }
                }
            } else if (!isScrollToUp && totalItemCount > visibleItemCount) {//当前滑动方向向下
                if (isInBottomFiling && lastDirection || !isInBottomFiling) {//防止反弹时候的滑动
                    if (lastVisiblePos != firstVisibleItem) {
                        translateY = lastPos - 0;//上个item的距离
                        translateY += 0 - childAt.getBottom();//本item的距离
                    } else {
                        translateY = lastPos - childAt.getBottom();//本次向下滑动的距离
                    }
                    lastDirection = false;
                    //调整listView的内容
                    if (mListView.getFirstVisiblePosition() == 1) {
                        View v = mListView.getChildAt(1);
                        if (v.getTranslationY() > tvHeight) {
                            if (translateY < 0 && trueHeader.getTranslationY() < headerBottom) {//在可滑动范围内
                                float translationResult = trueHeader.getTranslationY() - translateY;
                                if (translationResult > headerBottom)
                                    translationResult = headerBottom;
                                trueHeader.setTranslationY(translationResult);
                            }
                        }
                    }
                }
            }
            lastPos = childAt.getBottom();
            lastVisiblePos = firstVisibleItem;

        }
    }
}
