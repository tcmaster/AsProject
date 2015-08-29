package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
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
import com.android.tonight8.ui.view.ForLiveScrollView;
import com.android.tonight8.utils.Utils;
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
     * 滑动布局
     */
    @ViewInject(R.id.sv_lv_main)
    private ForLiveScrollView sv_lv_main;
    /**
     * 直播界面的ViewPager
     */
    @ViewInject(R.id.vp_play_screen)
    private ViewPager vp_play_screen;
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
    private String[] texts = {"没错，是短字符", "又有谁能猜出来，我是一个中字符", "长长的字符，大大的爱情，死亡之势不断透明的心情，会有一个的"};

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
        sv_lv_main.setOnScrollListener(new ForLiveScrollView.ForLiveScrollListener() {
            @Override
            public void onDropDown(float nowScrollY) {
                ////////测试下拉的代码begin////////////////////////////////////
                LogUtils.v("已经下拉");
                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        LogUtils.v("下拉完成");
                        sv_lv_main.onFinishDropDown();
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //模拟下拉
                        try {
                            Thread.sleep(2000);
                            handler.sendMessage(handler.obtainMessage(0));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                ////////测试下拉的代码end////////////////////////////////////
            }

            @Override
            public void onPullUp(float nowScrollY) {
                ////////测试上拉的代码begin////////////////////////////////////
                LogUtils.v("已经上拉");
                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        LogUtils.v("上拉完成");
                        sv_lv_main.onFinishPullUp();
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //模拟上拉数据
                        try {
                            Thread.sleep(2000);
                            handler.sendMessage(handler.obtainMessage(0));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                ////////测试上拉的代码end////////////////////////////////////
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

    /**
     * 滑到顶端
     */
    public void scrollTop() {
        sv_lv_main.scrollTo(0, 0);
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
}
