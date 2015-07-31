package com.android.tonight8.ui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.base.AppConstants;
import com.android.tonight8.utils.Utils;


import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiXiaoSong on 2015/7/20 0020.
 * 字幕布局
 */
public class BarrageView extends RelativeLayout {
    private static final boolean DEBUG = true;
    private static final String TAG = "BarrageView";
    //一些常用数值，计算一次后，以后将经常使用
    private static int layout_Height = 60;//该布局的高度，假定一个值
    private static float smallTextSize;//type1时，小字体的大小(像素)
    private static float middleTextSize;//type1时，中字体的大小（像素）
    private static float bigTextSize;//type1时，大字体的大小（像素）
    private static float iTypeSize;//type2时，字体的大小（像素）
    private boolean lastFinish = false;//是否停止弹幕
    private ShowBarrageHandler handler;
    private boolean whichQueue;//决定当前放入哪个队列
    private boolean animLock = false;//在发出消息到播放动画之前的动画锁;
    private int startThreadCount;
    private int finishThreadCount;
    private Lock lock;

    public BarrageView(Context context) {
        super(context);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 传入弹幕数据并显示
     */
    public void initData() {
        layout_Height = Utils.dip2px(getContext(), 60);
        handler = new ShowBarrageHandler(this);
        whichQueue = false;
        //初始化各项需要的宽高值
        smallTextSize = layout_Height / 10.0f * 2.0f; //小字占十分之二的宽高
        middleTextSize = layout_Height / 10.0f * 3.0f;//中字占十分之三的宽高
        bigTextSize = layout_Height / 10.0f * 4.0f;//大字占十分之四的宽高
        iTypeSize = layout_Height / 10.0f * 6.0f;//通知字体大小，十分之六的宽高
        startThreadCount = 0;
        finishThreadCount = 0;
        lock = new ReentrantLock(true);//公平锁
    }

    /**
     * 在不需要字幕播放时调用
     */
    public void stopBarrage() {
        lastFinish = true;
    }

    public void getData(List<BarrageModel> models) {
        for (BarrageModel e : models) fillData(e);
        playData(models);
    }

    /**
     * 增加一条字幕内容,并填充弹幕的内容
     */
    private void fillData(BarrageModel newContent) {

        if (newContent.getType() == BarrageModel.IMPORTANT) {
            newContent.setTextSize(iTypeSize);
            newContent.createTextView(this);
        } else if (newContent.getType() == BarrageModel.NORMAL) {
            if (whichQueue) {
                newContent.setTextSize(createTextSize(newContent.getText().length()));
                newContent.setWhichLineType(BarrageModel.TOP);
                newContent.createTextView(this);
            } else {
                newContent.setTextSize(createTextSize(newContent.getText().length()));
                newContent.setWhichLineType(BarrageModel.BOTTOM);
                newContent.createTextView(this);
            }
            whichQueue = !whichQueue;
        }
    }

    /**
     * 根据字体的个数返回一个字体的大小（小字体，中字体，大字体）
     *
     * @return
     */
    private float createTextSize(int textCount) {
        float baseWidth = textCount * (middleTextSize + 5);
        if (baseWidth >= AppConstants.widthPx) {
            return smallTextSize;
        } else if (baseWidth >= AppConstants.widthPx / 2 && baseWidth < AppConstants.widthPx) {
            return middleTextSize;
        } else if (baseWidth < AppConstants.widthPx / 2) {
            return bigTextSize;
        }
        return middleTextSize;
    }

    private void playData(final List<BarrageModel> models) {
        new ProcessThread(models).start();
    }

    private static class ShowBarrageHandler extends Handler {
        private WeakReference<BarrageView> view;

        public ShowBarrageHandler(BarrageView v) {
            view = new WeakReference<BarrageView>(v);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                final BarrageModel model = (BarrageModel) msg.obj;
                view.get().addView(model.getTv_barrage());
                BarrageAnimation animation = new BarrageAnimation(model.getTv_barrage(), view.get().getRight(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        view.get().animLock = false;//动画开启，解锁
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.get().removeView(model.getTv_barrage());
                        model.setbA(null);//取消动画
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                model.setbA(animation);
                animation.startA();

            } finally {
            }

        }
    }

    /**
     * 弹幕数据模型类
     */
    public static class BarrageModel implements Cloneable {
        public static final int IMPORTANT = 1;
        public static final int NORMAL = 2;
        public static final int TOP = 1;//上边的行
        public static final int BOTTOM = 2;//下面的行
        private String text;
        private int color;
        private int type;//字幕的类型（特殊为IMPORTANT，普通为NORMAL）
        private int whichLineType;//该字幕处于第一行还是第二行
        private float textSize;//文字大小（像素）
        private TextView tv_barrage;//弹幕控件播放的
        private BarrageAnimation bA;//当前播放的动画

        public BarrageModel(String text, int color, int type) {
            this.text = text;
            this.color = color;
            this.type = type;
        }

        public int getWhichLineType() {
            return whichLineType;
        }

        public void setWhichLineType(int whichLineType) {
            this.whichLineType = whichLineType;
        }

        public BarrageAnimation getbA() {
            return bA;
        }

        public void setbA(BarrageAnimation bA) {
            this.bA = bA;
        }

        public TextView getTv_barrage() {
            return tv_barrage;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


        public Object cloneNew() {
            Object o = null;
            try {
                o = super.clone();
            } catch (CloneNotSupportedException e) {
            }
            return o;
        }

        /**
         * 设置好所有内容后，创建textView
         *
         * @param parent
         */
        private void createTextView(View parent) {
            tv_barrage = new TextView(parent.getContext());
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            if (type == NORMAL) {
                if (whichLineType == TOP) {
                    if (textSize == BarrageView.smallTextSize)
                        lp.topMargin = BarrageView.layout_Height / 10 * 2;
                    else if (textSize == BarrageView.middleTextSize)
                        lp.topMargin = BarrageView.layout_Height / 10 * 1;
                    else if (textSize == BarrageView.bigTextSize)
                        lp.topMargin = BarrageView.layout_Height / 20 * 1;
                } else if (whichLineType == BOTTOM) {
                    if (textSize == BarrageView.smallTextSize)
                        lp.topMargin = BarrageView.layout_Height / 10 * 2 * 2 + (int) smallTextSize;
                    else if (textSize == BarrageView.middleTextSize)
                        lp.topMargin = BarrageView.layout_Height / 10 * 3 + (int) middleTextSize;
                    else if (textSize == BarrageView.bigTextSize)
                        lp.topMargin = BarrageView.layout_Height / 20 * 3 + (int) bigTextSize;
                }
            } else if (type == IMPORTANT) {
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
            }
            tv_barrage.setLayoutParams(lp);
            tv_barrage.setSingleLine();
            tv_barrage.setLeft(parent.getLeft());
            tv_barrage.setText(text);
            tv_barrage.setTextColor(color);
            tv_barrage.setTextSize(Utils.px2sp(parent.getContext(), textSize));
        }
    }

    /**
     * 字幕的平移动画
     */
    private static class BarrageAnimation extends Animation {
        private TextView v;//播放动画的视图
        private int distance;//该动画要移动的距离
        private int start;//该动画运行初始运行位置
        private AnimationListener listener;//该动画的监听
        private int nowPos;//该控件右侧的位置
        private int tvWidth;//该控件的宽度
        private boolean moreWidth;//该textView里包含字符的宽度是否大于屏幕的宽度
        private float changeValue = -10.0f;//在大于屏幕宽度后，需要修改的值
        private float moreTime = 0.0f;//该控件里包含字符的宽度大于屏幕宽度后，需要额外走的时间
        private float duration;//动画总播放时间
        private int inScreenDistance;//到达一屏的距离
        private boolean isScrollOver = false;//如果有滑动事件，则该位置置为true之后，平移事件将改为滑动事件
        private boolean isScrollInScreen = false;//如果滑动事件已经到达边界，则可进行下一个item的显示

        public BarrageAnimation(TextView v, int start, AnimationListener listener) {
            this.v = v;
            setFillAfter(true);
            this.start = start;
            tvWidth = (int) (v.getText().length() * v.getTextSize() + 20);
            moreWidth = false;
            distance = start + tvWidth;
            duration = distance / 300.0f * 1000.0f;
            setDuration((int) duration);//所有字幕以300像素每秒的速度移动
            if (tvWidth > AppConstants.widthPx) {
                moreWidth = true;
                moreTime = (distance - (2 * AppConstants.widthPx)) / 300.0f * 1000.0f;
                inScreenDistance = AppConstants.widthPx;
            } else inScreenDistance = tvWidth;
            setInterpolator(new LinearInterpolator());
            this.listener = listener;
            nowPos = 0;
        }

        public void startA() {
            v.clearAnimation();
            BarrageAnimation.this.setAnimationListener(listener);
            v.startAnimation(BarrageAnimation.this);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float current = start - (interpolatedTime * distance);
            if (current <= 0 && moreWidth) {//说明需要进行滑动
                if (!isScrollOver) isScrollOver = true;
                if (changeValue == -10.0f) {
                    changeValue = interpolatedTime;
                }
                float currentProgress = interpolatedTime - changeValue;
                v.setScrollX((int) ((tvWidth - AppConstants.widthPx) * currentProgress / (moreTime / duration)));
                if (interpolatedTime >= changeValue + (moreTime / duration)) {//滑动结束
//                    moreWidth = false;
                    isScrollInScreen = true;
                }
            }
            if (!isScrollOver) {//在未滑动时进行平移动画
                v.setTranslationX(current);
                nowPos = (int) (interpolatedTime * distance);
            }
        }

        /**
         * 返回当前视图是否已经完全进入屏幕中
         *
         * @return
         */
        public boolean isInScreen() {
            return isScrollInScreen || (v == null || nowPos >= inScreenDistance);
        }

        /**
         * 获得控件的宽度(未进行绘制的控件）
         *
         * @param v 要获得宽度的控件
         * @return
         */
        private int getTargetWidth(View v) {
            try {
                Method m = v.getClass().getDeclaredMethod("onMeasure", int.class,
                        int.class);
                m.setAccessible(true);
                m.invoke(v, MeasureSpec.makeMeasureSpec(
                        ((View) v.getParent()).getMeasuredWidth(),
                        MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(0,
                        MeasureSpec.UNSPECIFIED));
            } catch (Exception e) {
            }
            return v.getMeasuredWidth();
        }
    }

    private class ProcessThread extends Thread {
        private List<BarrageModel> models;

        public ProcessThread(List<BarrageModel> models) {
            this.models = models;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                while (!lastFinish && (startThreadCount > finishThreadCount)) ;
                startThreadCount++;
                for (int i = 0; i < models.size(); i++) {
                    if (lastFinish) return;//停止循环
                    animLock = true;//锁住
                    BarrageModel iModel = models.get(i);
                    if (i >= 0) {
                        label1:
                        for (int j = i - 1; j >= 0; j--) {//检测是否之前队列的内容已经播放到正确位置
                            BarrageModel jModel = models.get(j);
                            if (jModel.getbA() != null) {//该动画未播放完毕
                                switch (iModel.getType()) {
                                    case BarrageModel.IMPORTANT:
                                        if (jModel.getType() == BarrageModel.NORMAL) {
                                            while (!lastFinish && (jModel.getbA() != null && !jModel.getbA().isInScreen()))
                                                ;//如果之前的动画未播放完毕，循环等待
                                            if (j > 0) {
                                                BarrageModel jModel2 = models.get(j - 1);
                                                while (!lastFinish && (jModel2.getbA() != null && !jModel2.getbA().isInScreen()))
                                                    ;//如果之前的动画未播放完毕，循环等待
                                                break label1;
                                            } else break label1;
                                        }
                                        while (!lastFinish && (jModel.getbA() != null && !jModel.getbA().isInScreen()))
                                            ;//如果之前的动画未播放完毕，循环等待
                                        break label1;
                                    case BarrageModel.NORMAL:
                                        if (jModel.getType() == BarrageModel.NORMAL) {//前面也是普通型model
                                            if (iModel.getWhichLineType() != jModel.getWhichLineType()) {//不是同一行的，不进行判断考虑
                                                continue label1;
                                            } else if (iModel.getWhichLineType() == jModel.getWhichLineType()) {//为同一行的内容，需要考虑
                                                while (!lastFinish && (jModel.getbA() != null && !jModel.getbA().isInScreen()))
                                                    ;
                                                break label1;
                                            }
                                        } else if (jModel.getType() == BarrageModel.IMPORTANT) {//前面是通知型model
                                            while (!lastFinish && (jModel.getbA() != null && !jModel.getbA().isInScreen()))
                                                ;//等待该通知型model结束
                                            break label1;
                                        }
                                        break;
                                }
                            }
                        }
                    }
                    /**
                     * 四个值的属性分别为，当前model编号，model总编号，备用数据，model的内容
                     */
                    handler.sendMessage(handler.obtainMessage(i, models.size(), -1, models.get(i)));
                    while (!lastFinish && animLock) ;//等待开始播放后解锁
                    if (i == models.size() - 1) {
                        while (!lastFinish && (models.get(models.size() - 1).getbA() != null && !models.get(models.size() - 1).getbA().isInScreen()))
                            ;
                        finishThreadCount++;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
