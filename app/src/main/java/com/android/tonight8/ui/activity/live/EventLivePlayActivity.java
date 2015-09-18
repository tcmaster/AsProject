package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.AppConstants;
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
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tencent.open.utils.Util;

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
     * 开关附件布局
     */
    @ViewInject(R.id.iv_attachment)
    private ImageView iv_attachment;
    /**
     * 输入文字
     */
    @ViewInject(R.id.et_content)
    private EditText et_content;
    /**
     * 发送消息
     */
    @ViewInject(R.id.tv_send_message)
    private TextView tv_send_message;
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
    /**
     * 功能菜单开关
     */
    @ViewInject(R.id.v_function_button)
    private View v_function_button;
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
        v_function_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_function_container.clearAnimation();
                MenuAnimation animation = new MenuAnimation();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        rl_function_container.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                rl_function_container.startAnimation(animation);
            }
        });
    }

    private void initInterface() {
        //LiveIOController.readLiveTitle(handler);//暂时注释，防止混乱
        tv_send_message.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventLivePlayActivity.this,
                        GoodsInfoActivity.class);
                startActivity(intent);
            }
        });
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
            ft.add(R.id.ll_lv_container, bf);
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
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < 4; i++) {
            if (i == pos)
                ft.show(bfs[i]);
            else
                ft.hide(bfs[i]);
        }
        ft.commit();
    }

    private AnimationSet createFunctionMenuAnimOpen() {
        rl_function_container.setVisibility(View.VISIBLE);
        float endX = v_function_button.getX();
        float endY = v_function_button.getY();
        float startX = rl_function_container.getX();
        float startY = rl_function_container.getY();
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(1000);
        animationSet.setFillAfter(false);
        TranslateAnimation ta = new TranslateAnimation(endX - startX - rl_function_container.getMeasuredWidth() / 2, 0, endY - startY - rl_function_container.getMeasuredHeight() / 2, 0);
        ScaleAnimation sa = new ScaleAnimation(0.16f, 1.0f, 0.16f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(ta);
        animationSet.addAnimation(sa);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //rl_function_container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animationSet;
    }

    private AnimationSet createFunctionMenuAnimClose() {
        float endX = v_function_button.getX();
        float endY = v_function_button.getY();
        float startX = rl_function_container.getX();
        float startY = rl_function_container.getY();
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(500);
        animationSet.setFillAfter(false);
        TranslateAnimation ta = new TranslateAnimation(endX - startX, 0, endY - startY, 0);
        ScaleAnimation sa = new ScaleAnimation(1.0f, 0.16f, 1.0f, 0.16f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(ta);
        animationSet.addAnimation(sa);
        return animationSet;
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
    private class MenuAnimation extends Animation {
        private float startX, startY, endX, endY;
        private float initVX, initVY;
        private float menuX, menuY;

        public MenuAnimation() {
            initVX = v_function_button.getMeasuredWidth();
            initVY = v_function_button.getMeasuredHeight();
            menuX = rl_function_container.getMeasuredWidth();
            menuY = rl_function_container.getMeasuredHeight();
            startX = (AppConstants.widthPx - menuX) / 2;
            startY = (AppConstants.heightPx - menuY) / 2;
            endX = AppConstants.widthPx - Utils.dip2px(EventLivePlayActivity.this, 20) - initVX;
            endY = AppConstants.heightPx - Utils.dip2px(EventLivePlayActivity.this, 20) - initVY;
            setInterpolator(new AccelerateDecelerateInterpolator());
            setDuration(1000);
            setFillAfter(true);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float x = (endX + initVX) - (AppConstants.widthPx - menuX) / 2 * interpolatedTime - startX;
            float y = (endY + initVY) - (AppConstants.heightPx - menuY) / 2 * interpolatedTime - startY;
            LogUtils.v("the value is " + x + " the y value is " + y);
            rl_function_container.setTranslationX(x);
            rl_function_container.setTranslationY(y);
//            rl_function_container.setScaleX(0.16f + 0.84f * interpolatedTime);
//            rl_function_container.setScaleY(0.16f + 0.84f * interpolatedTime);

        }

    }


}
