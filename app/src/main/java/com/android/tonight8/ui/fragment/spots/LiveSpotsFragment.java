package com.android.tonight8.ui.fragment.spots;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.ui.activity.live.SpotRecordActivity;
import com.android.tonight8.ui.adapter.spots.SpotAdapter;
import com.android.tonight8.ui.view.CustomerDialog;
import com.android.tonight8.ui.view.StationaryGridview;
import com.android.tonight8.ui.view.Wheel.NumericWheelAdapter;
import com.android.tonight8.ui.view.Wheel.OnWheelChangedListener;
import com.android.tonight8.ui.view.Wheel.WheelView;
import com.android.tonight8.utils.DialogUtils;
import com.lecloud.common.base.util.Logger;
import com.lecloud.download.control.DownloadCenter;
import com.lecloud.skin.PlayerStateCallback;
import com.lecloud.skin.vod.VODPlayCenter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 现场插播类
 */
public class LiveSpotsFragment extends BaseFragment {
    @ViewInject(R.id.linear1)
    private LinearLayout linear1;
    @ViewInject(R.id.tv_time1)
    private TextView tv_hour;
    @ViewInject(R.id.tv_time2)
    private TextView tv_minute;
    @ViewInject(R.id.tv_time3)
    private TextView tv_second;
    @ViewInject(R.id.btn_preview)
    private Button btn_preview;
    @ViewInject(R.id.btn_insert)
    private Button btn_insert;
    private String strHour, strMinute, strSecond;
    @ViewInject(R.id.gv_insertitem)
    private StationaryGridview stationaryGridview;
    @ViewInject(R.id.rl_leshi_player)
    private RelativeLayout mPlayerLayoutView;
    private VODPlayCenter mPlayerView;
    private boolean isBackgroud = false;
    private String uu = "487c884e76";
    private String vu = "e5a4fb751e";
    @ViewInject(R.id.tv_subtitle)
    private TextView tv_subtitle;
    @ViewInject(R.id.iv_insert)
    private ImageView iv_insert;
    private List<String> spotlist;

    public LiveSpotsFragment() {
        // Required empty public constructor
    }

    public static final LiveSpotsFragment newInstance() {
        LiveSpotsFragment fragment = new LiveSpotsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_live_spots, container, false);
        ViewUtils.inject(this, rootView);
        SpotAdapter adapter = new SpotAdapter(activity);
        stationaryGridview.setAdapter(adapter);
        stationaryGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        DialogUtils.showSelectPicDialog(activity, BaseActivity.PICKPICTURE, BaseActivity.TAKEPHOTO);
                        break;
                    case 1:
                        DialogUtils.showCommitZiMuDialog(activity, "请输入字幕(限40字)", tv_subtitle);
                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), SpotRecordActivity.class);
                        getActivity().startActivityForResult(intent, 21);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;

                }
            }
        });

        mPlayerView = new VODPlayCenter(activity, true);
        mPlayerLayoutView.addView(mPlayerView.getPlayerView());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPlayerView != null) {
            if (isBackgroud) {
                if (mPlayerView.getCurrentPlayState() == PlayerStateCallback.PLAYER_VIDEO_PAUSE) {
                    this.mPlayerView.resumeVideo();
                } else {
                    Logger.e("VODActivity", "已回收，重新请求播放");
                    mPlayerView.playVideo(uu, vu, "", "", "测试节目");
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            mPlayerView.pauseVideo();
            isBackgroud = true;
        }
    }

    @Override
    public void onDestroy() {
        this.mPlayerView.destroyVideo();
        this.mPlayerLayoutView.removeAllViews();
        this.mPlayerView = null;
        Logger.e("VODActivity", "onDestroy");
        super.onDestroy();
        isBackgroud = false;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Log.i("VODActivity", "半屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Log.i("VODActivity", "全屏");
        }
    }

    @OnClick({R.id.linear1, R.id.btn_preview, R.id.btn_insert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear1:
                selectTimeDialog();
                break;
            case R.id.btn_preview:
                mPlayerView.bindDownload(DownloadCenter.getInstances(activity));
                DownloadCenter.getInstances(activity).allowShowMsg(false);
                this.mPlayerView.playVideo(uu, vu, "151398", "", "测试节目", true);// c34f821becb64978216a8765ccfff24e
                break;
            case R.id.btn_insert:

                break;

        }

    }

    private void selectTimeDialog() {
        final CustomerDialog cdlg = new CustomerDialog(activity,
                R.layout.dialog_time_select);
        cdlg.setOnCustomerViewCreated(new CustomerDialog.CustomerViewInterface() {

            @Override
            public void getCustomerView(Window window, final AlertDialog dlg) {
                window = dlg.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.gravity = Gravity.BOTTOM;
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);

                final WheelView wv_hour = (WheelView) window
                        .findViewById(R.id.wv_hour);
                WheelView wv_minute = (WheelView) window
                        .findViewById(R.id.wv_minute);
                WheelView wv_second = (WheelView) window.findViewById(R.id.wv_second);
                Button btn_cancel = (Button) window.findViewById(R.id.btn_cancel);
                Button btn_ok = (Button) window.findViewById(R.id.btn_ok);
                NumericWheelAdapter adapter1 = new NumericWheelAdapter(0, 60);
                wv_hour.setAdapter(adapter1);

                NumericWheelAdapter adapter2 = new NumericWheelAdapter(0, 60);
                wv_minute.setAdapter(adapter2);

                NumericWheelAdapter adapter3 = new NumericWheelAdapter(0, 60);
                wv_second.setAdapter(adapter3);

                wv_hour.setCurrentItem(Integer.valueOf(tv_hour.getText().toString()));
                wv_minute.setCurrentItem(Integer.valueOf(tv_minute.getText().toString()));
                wv_second.setCurrentItem(Integer.valueOf(tv_second.getText().toString()));
                wv_hour.setCyclic(true);
                wv_minute.setCyclic(true);
                wv_second.setCyclic(true);
                wv_hour.addChangingListener(new OnWheelChangedListener() {
                    @Override
                    public void onChanged(WheelView wheel, int oldValue, int newValue) {
                        strHour = newValue + "";
                    }
                });
                wv_minute.addChangingListener(new OnWheelChangedListener() {
                    @Override
                    public void onChanged(WheelView wheel, int oldValue, int newValue) {
                        strMinute = newValue + "";
                    }
                });
                wv_second.addChangingListener(new OnWheelChangedListener() {
                    @Override
                    public void onChanged(WheelView wheel, int oldValue, int newValue) {
                        strSecond = newValue + "";
                    }
                });

                btn_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        tv_hour.setText(strHour);
                        tv_minute.setText(strMinute);
                        tv_second.setText(strSecond);
                        dlg.dismiss();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg.dismiss();
                    }
                });

            }
        });
        cdlg.setDismissIfClick(true);
        cdlg.setLayoutGravity(Gravity.BOTTOM);
        cdlg.showDlg();
    }

    public void updateImageSource(String tempPicPath) {
        bmUtils.display(iv_insert, tempPicPath);
    }

    public void updateRecordData(List<String> list) {
        LogUtils.d("返回的数据" + list.get(0).toString());
    }
}
