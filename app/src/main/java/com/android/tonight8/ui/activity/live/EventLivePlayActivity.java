package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.dao.model.live.EventLive;
import com.android.tonight8.io.HandlerConstants;
import com.android.tonight8.io.event.entity.EventListNetEntity;
import com.android.tonight8.io.live.paramentity.TestParamEntity;
import com.android.tonight8.io.net.NetEntityBase;
import com.android.tonight8.io.net.NetRequest;
import com.android.tonight8.ui.fragment.livemanage.LiveCommitListFragment;
import com.android.tonight8.ui.fragment.livemanage.ProgramListFragment;
import com.android.tonight8.ui.fragment.livemanage.VoteFragment;
import com.android.tonight8.ui.fragment.livemanage.WinnerListFragment;
import com.android.tonight8.ui.view.BarrageView;
import com.android.tonight8.ui.view.LiveContainer;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXiaoSong
 * @Descripton 活动现场界面, 现场界面用到的4个fragment（）
 * @2015-5-11
 */
public class EventLivePlayActivity extends BaseActivity {
    /**
     * 该界面的头部
     */
    @ViewInject(R.id.ll_header)
    private LinearLayout ll_header;
    /**
     * 直播界面的ViewPager
     */
    @ViewInject(R.id.vp_play_screen)
    private ViewPager vp_play_screen;
    /**
     * 左边按钮的布局
     */
    @ViewInject(R.id.ll_left)
    private LinearLayout ll_left;
    /**
     * 右边的按钮的布局
     */
    @ViewInject(R.id.ll_right)
    private LinearLayout ll_right;
    //下面是checkBox的复选框
    /**
     * 分享
     */
    @ViewInject(R.id.cb_share)
    private CheckBox cb_share;
    /**
     * 收藏
     */
    @ViewInject(R.id.cb_save)
    private CheckBox cb_save;
    /**
     * 音量
     */
    @ViewInject(R.id.cb_voice)
    private CheckBox cb_voice;
    /**
     * 字幕开关
     */
    @ViewInject(R.id.cb_barrage_on_off)
    private CheckBox cb_barrage_on_off;
    /**
     * 播放界面左边的选项
     */
    @ViewInject(R.id.cb_extra_left)
    private CheckBox cb_extra_left;
    /**
     * 播放界面右边的选项
     */
    @ViewInject(R.id.cb_extra_right)
    private CheckBox getCb_extra_right;
    /**
     * 页面四个选项的radioGroup
     */
    @ViewInject(R.id.rg_tab)
    private RadioGroup rg_tab;
    /**
     * 商品信息
     */
    @ViewInject(R.id.tv_goods_info)
    private TextView tv_goods_info;
    /**
     * 聊天界面
     */
    @ViewInject(R.id.ll_talk)
    private LinearLayout ll_talk;
    /**
     * 附件界面
     */
    @ViewInject(R.id.ll_send_attachments)
    private LinearLayout ll_send_attachments;
    /**
     * 输入文字
     */
    @ViewInject(R.id.et_content)
    private EditText et_content;
    /**
     * 字幕视图
     */
    @ViewInject(R.id.bv_live)
    private BarrageView bv_live;
    /**
     * 控制布局
     */
    @ViewInject(R.id.rl_controller)
    private RelativeLayout rl_controller;
    /**
     * 字幕
     */
    @ViewInject(R.id.tv_ppt_discribe)
    private TextView tv_ppt_discribe;
    /**
     * 功能菜单
     */
    @ViewInject(R.id.rl_function_container)
    private RelativeLayout rl_function_container;
    //功能菜单的四个按钮
    /**
     * 发表话题
     */
    @ViewInject(R.id.tv_talk)
    private TextView tv_talk;
    /**
     * 赞
     */
    @ViewInject(R.id.ib_good)
    private ImageButton ib_good;
    /**
     * 置顶
     */
    @ViewInject(R.id.ib_top)
    private ImageButton ib_top;
    /**
     * 进入购买页面
     */
    @ViewInject(R.id.tv_buy)
    private TextView tv_buy;
    /**
     * 功能菜单开关
     */
    @ViewInject(R.id.v_function_button)
    private View v_function_button;
    /**
     * 存放四个fragment的布局
     */
    @ViewInject(R.id.ll_container)
    private LiveContainer ll_container;
    /**
     * 话题发送消息按钮
     */
    @ViewInject(R.id.iv_send_message)
    private ImageView iv_send_message;
    /**
     * 4个页面的fragment列表
     */
    private BaseFragment[] bfs;
    /**
     * fg管理器
     */
    private FragmentManager fm;
    /**
     * 本界面的handler
     */
    private MyHandler handler;
    /**
     * 字幕播放开关
     */
    private boolean isNotDestory = true;
    private boolean isInit = true;//初始化
    private int[] types = {BarrageView.BarrageModel.NORMAL, BarrageView.BarrageModel.IMPORTANT};
    private String[] texts = {"短字符测试", "中型字符的长效测试，没催", "长字符的各种测试，测来测去的长小测试，你们都会明白的"};
    private boolean isShowMore = true;//当前界面是否展示额外内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreateNomal(savedInstanceState, R.layout.activity_event_live_play);
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isNotDestory = false;//停止发送字幕
        bv_live.stopBarrage();//停止字幕播放
    }


    private void initDatas() {
        handler = new MyHandler(this);//初始化handler
        initInterface();
        initFragments();
        barrageTest();
        //新版接口使用示例
        TestParamEntity entity = new TestParamEntity();
        entity.getPage().setOffset(0);
        entity.getPage().setRow(20);
        entity.getRegional().setCode("1100");
        entity.setIsToday(true);
        NetRequest.doPostRequestByJson("http://180.150.179.226/activitys/Event/list/show", entity, new NetRequest.RequestResult<EventListNetEntity>(EventListNetEntity.class, new Handler()) {
            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.v("数据返回不成功" + s);
            }

            @Override
            public void getData(NetEntityBase netEntityBase, EventListNetEntity eventListNetEntity, Handler handler) {
                LogUtils.v("数据成功返回");
            }
        });
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.cb_extra_left:
                        showLeft(isChecked);
                        break;
                    case R.id.cb_extra_right:
                        showRight(isChecked);
                        break;
                    case R.id.cb_save:
                        processSave(isChecked);
                        break;
                    case R.id.cb_share:
                        processShare(isChecked);
                        break;
                    case R.id.cb_voice:
                        processVoice(isChecked);
                        break;
                    case R.id.cb_barrage_on_off:
                        processBarrage(isChecked);
                        break;
                }
            }
        };
        ll_left.setVisibility(View.INVISIBLE);
        ll_right.setVisibility(View.INVISIBLE);
        cb_extra_left.setOnCheckedChangeListener(listener);
        getCb_extra_right.setOnCheckedChangeListener(listener);
        cb_save.setOnCheckedChangeListener(listener);
        cb_share.setOnCheckedChangeListener(listener);
        cb_voice.setOnCheckedChangeListener(listener);
        cb_barrage_on_off.setOnCheckedChangeListener(listener);
        OnClickListener clickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.v_function_button:
                        rl_function_container.clearAnimation();
                        MenuAnimation animation = new MenuAnimation(true);
                        rl_function_container.startAnimation(animation);
                        v_function_button.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.tv_talk:
                        processTalk();
                        break;
                    case R.id.ib_good:
                        processGood();
                        break;
                    case R.id.ib_top:
                        processTop();
                        break;
                    case R.id.tv_buy:
                        processBuy();
                        break;
                    case R.id.iv_send_message:
                        processSendTalk();
                        break;
                }

            }
        };
        v_function_button.setOnClickListener(clickListener);
        tv_talk.setOnClickListener(clickListener);
        tv_buy.setOnClickListener(clickListener);
        ib_good.setOnClickListener(clickListener);
        ib_top.setOnClickListener(clickListener);
        iv_send_message.setOnClickListener(clickListener);
        ll_container.setInterruptInterface(new LiveContainer.InterruptTouch() {
            @Override
            public void onInterrupt() {
                rl_function_container.clearAnimation();
                rl_function_container.startAnimation(new MenuAnimation(false));
            }
        }, rl_function_container);
    }

    private void initInterface() {
        //LiveIOController.readLiveTitle(handler);//暂时注释，防止混乱
    }


    private void initFragments() {
        getActionBarSpeical("某活动现场", R.mipmap.ic_launcher, true, false, null);
        bfs = new BaseFragment[4];
        bfs[0] = LiveCommitListFragment.newInstance();
        bfs[1] = WinnerListFragment.newInstance();
        bfs[2] = VoteFragment.newInstance();
        bfs[3] = ProgramListFragment.newInstance();
        if (fm == null)
            fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (BaseFragment bf : bfs) {
            ft.add(R.id.ll_container, bf);
            ft.hide(bf);
        }
        ft.commit();
        rg_tab.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_live:
                        changeFragment(0);
                        break;
                    case R.id.rb_winner_list:
                        changeFragment(1);
                        break;
                    case R.id.rb_vote:
                        changeFragment(2);
                        break;
                    case R.id.rb_program_list:
                        changeFragment(3);
                        break;
                    default:
                        break;
                }

            }
        });
        rg_tab.check(R.id.rb_live);
    }

    private void processSave(boolean isChecked) {

    }

    private void processShare(boolean isShare) {

    }

    private void processVoice(boolean isVoice) {

    }

    private void processBarrage(boolean isChecked) {

    }

    /**
     * 显示发话题栏
     */
    private void processTalk() {
        if (ll_talk.getVisibility() == View.VISIBLE) ll_talk.setVisibility(View.INVISIBLE);
        else ll_talk.setVisibility(View.VISIBLE);
        rl_function_container.clearAnimation();
        MenuAnimation animation = new MenuAnimation(false);
        rl_function_container.startAnimation(animation);
    }

    /**
     * 发送话题
     */
    private void processSendTalk() {
        if (ll_send_attachments.getVisibility() == View.VISIBLE) {

        }
    }

    /**
     * 赞
     */
    private void processGood() {

    }

    /**
     * 置顶
     */
    private void processTop() {
        List<Fragment> bfs = fm.getFragments();
        for (int i = 0; i < bfs.size(); i++) {
            BaseFragment bf = (BaseFragment) bfs.get(i);
            if (bf != null && bf.isVisible()) {
                bf.scrollToTop();
            }
        }
        rl_function_container.clearAnimation();
        MenuAnimation animation = new MenuAnimation(false);
        rl_function_container.startAnimation(animation);
    }

    /**
     * 进入商品详情
     */
    private void processBuy() {

    }

    private void showLeft(boolean isShow) {
        if (isShow) ll_left.setVisibility(View.VISIBLE);
        else ll_left.setVisibility(View.INVISIBLE);
    }

    private void showRight(boolean isShow) {
        if (isShow) ll_right.setVisibility(View.VISIBLE);
        else ll_right.setVisibility(View.INVISIBLE);
    }

    private void barrageTest() {//字幕测试
        bv_live.initData();
        isNotDestory = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(handler.obtainMessage(-333));
                SystemClock.sleep(10000);
                handler.sendMessage(handler.obtainMessage(-344));
            }
        }).start();

    }

    /**
     * 返回虚拟的头部
     *
     * @return
     */
    public View getHeader() {
        return ll_header;
    }

    /**
     * 切换当前显示的fragment
     *
     * @param pos
     */
    private void changeFragment(int pos) {
        int showPos = 0;
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < 4; i++) {
            if (i == pos) {
                ft.show(bfs[i]);
                showPos = i;
            } else
                ft.hide(bfs[i]);
        }
        ft.commit();
        bfs[showPos].scrollToTop();
    }

    private static class MyHandler extends Handler {
        WeakReference<EventLivePlayActivity> ref;
        private int i = 0;


        public MyHandler(EventLivePlayActivity context) {
            ref = new WeakReference<EventLivePlayActivity>(context);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HandlerConstants.Live.LIVE_TITLE:
                    switch (msg.arg1) {
                        case HandlerConstants.NETWORK_BEGIN:
                            break;
                        case HandlerConstants.RESULT_OK:
                            EventLive result = (EventLive) msg.obj;
                            ref.get().getActionBarSpeical(result.getEvent().getName(),
                                    R.mipmap.ic_launcher, true, false,
                                    new OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                        }
                                    });
                            break;
                        default:
                            break;
                    }
                    break;
                case -333:
                    List<BarrageView.BarrageModel> models = new ArrayList<BarrageView.BarrageModel>();
                    for (int i = 0; i < 20; i++) {
                        BarrageView.BarrageModel model = new BarrageView.BarrageModel(ref.get().texts[(int) (Math.random() * 3)] + i, Color.BLACK, ref.get().types[(Math.random() > 0.3d ? 0 : 1)]);
                        models.add(model);
                    }
                    ref.get().bv_live.getData(models);
                    break;
                case -344:
                    List<BarrageView.BarrageModel> models2 = new ArrayList<BarrageView.BarrageModel>();
                    for (int i = 20; i < 150; i++) {
                        BarrageView.BarrageModel model = new BarrageView.BarrageModel(ref.get().texts[(int) (Math.random() * 3)] + i, Color.BLACK, ref.get().types[(Math.random() > 0.3d ? 0 : 1)]);
                        models2.add(model);

                    }
                    ref.get().bv_live.getData(models2);
                default:
                    break;
            }
        }
    }

    /**
     * 针对菜单的动画
     */
    public class MenuAnimation extends Animation {
        private float startX, startY;
        private boolean isOpen;

        public MenuAnimation(final boolean isOpen) {
            startX = v_function_button.getLeft() - rl_function_container.getLeft() - (rl_function_container.getMeasuredWidth() - v_function_button.getMeasuredWidth()) / 2;
            startY = v_function_button.getTop() - rl_function_container.getTop() - (rl_function_container.getMeasuredWidth() - v_function_button.getMeasuredWidth()) / 2;
            this.isOpen = isOpen;
            setInterpolator(new AccelerateDecelerateInterpolator());
            setDuration(500);
            setFillAfter(true);
            setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (isOpen) rl_function_container.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (!isOpen) {
                        rl_function_container.setVisibility(View.INVISIBLE);
                        v_function_button.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float x, y, scaleX, scaleY;
            if (isOpen) {
                x = startX - startX * interpolatedTime;
                y = startY - startY * interpolatedTime;
                scaleX = 0.16f + 0.84f * interpolatedTime;
                scaleY = 0.16f + 0.84f * interpolatedTime;
            } else {
                x = 0 + startX * interpolatedTime;
                y = 0 + startY * interpolatedTime;
                scaleX = 1 - 0.84f * interpolatedTime;
                scaleY = 1 - 0.84f * interpolatedTime;
            }
            rl_function_container.setTranslationX(x);
            rl_function_container.setTranslationY(y);
            rl_function_container.setScaleX(scaleX);
            rl_function_container.setScaleY(scaleY);

        }

    }

    public void insertSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int key = a[i];
            int num = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > key) {
                    num = j;
                    key = a[j];
                }
            }
            a[num] = a[i];
            a[i] = key;
        }
    }

    public void fastSort(int[] a, int head, int end) {
        int low = head;
        int high = end;
        int key = a[low];
        while (low < high) {
            while (a[high] > key) high--;
            if(low < high) {
                int temp = a[high];
                a[high] = a[low];
                a[low] = temp;
            }
            while (a[low] < key) low++;
            if(low < high) {
                int temp2 = a[low];
                a[low] = a[high];
                a[high] = temp2;
            }
        }
        if(low > head) fastSort(a,head,low-1);
        if(high < end)fastSort(a,low+1,end);
    }


}
