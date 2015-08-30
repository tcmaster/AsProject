package com.android.tonight8.ui.activity.live;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.ui.fragment.spots.LiveSpotsFragment;
import com.android.tonight8.ui.fragment.spots.VideoSpotsFragment;
import com.lidroid.xutils.view.annotation.ViewInject;


public class LiveManageActivity extends BaseActivity {
    @ViewInject(R.id.rg_zhibochabo)
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private BaseFragment[] baseFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreateNomal(savedInstanceState, R.layout.activity_live_manage);
        initData();
        initListener();
    }

    private void initData() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        baseFragments = new BaseFragment[2];
        baseFragments[0] = LiveSpotsFragment.newInstance();
        baseFragments[1] = VideoSpotsFragment.newInstance();
        ft.add(R.id.fl_chabo, baseFragments[0]);
        ft.add(R.id.fl_chabo, baseFragments[1]);
        ft.commit();

    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_liveplay:
                        doFragmentShow(0);
                        break;
                    case R.id.rb_videoplay:
                        doFragmentShow(1);
                        break;
                }
            }
        });
    }

    /**
     * @param which 第几个对象
     * @Description:显示当前的fragment对象
     * @author: LiXiaoSong
     * @date:2015-2-6
     */
    private void doFragmentShow(int which) {
        ft = fm.beginTransaction();
        for (int i = 0; i < baseFragments.length; i++) {
            if (which == i) {
                ft.show(baseFragments[i]);
            } else
                ft.hide(baseFragments[i]);
        }
        ft.commit();
    }
}
