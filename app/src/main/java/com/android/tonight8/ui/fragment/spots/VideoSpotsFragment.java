package com.android.tonight8.ui.fragment.spots;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;
import com.lecloud.common.base.util.Logger;
import com.lecloud.skin.PlayerStateCallback;
import com.lecloud.skin.live.LivePlayCenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoSpotsFragment extends BaseFragment {
    private static String Defualt_LIVEID = "201501193000003";//"201412083000001";
    private RelativeLayout mPlayerLayoutView;
    private LivePlayCenter mPlayerView;
    private EditText testEditText;
    private CheckBox cb_play;
    private TextView tv_startplay;
    private boolean isHLS;
    private boolean isBackgroud = false;

    public VideoSpotsFragment() {
        // Required empty public constructor
    }

    public static final VideoSpotsFragment newInstance() {
        VideoSpotsFragment fragment = new VideoSpotsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_spots, container, false);
        mPlayerLayoutView = (RelativeLayout) view
                .findViewById(R.id.rl_liveplayer);
        mPlayerView = new LivePlayCenter(activity, true, true);
        mPlayerView.setRelease(false);
        mPlayerLayoutView.addView(mPlayerView.getPlayerView());
        cb_play = (CheckBox) view
                .findViewById(R.id.cb_play);
        tv_startplay = (TextView) view.findViewById(R.id.tv_startplay);
        cb_play.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tv_startplay.setText("停止播放");
                    cb_play.setBackgroundResource(R.mipmap.c5s4p2e002);
                    onPause();
                } else {
                    tv_startplay.setText("开始播放");
                    mPlayerView.playVideo(Defualt_LIVEID + "", "测试频道");
                    cb_play.setBackgroundResource(R.mipmap.c5s4p2e001);
                }

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPlayerView != null) {
            if (isBackgroud) {
                if (mPlayerView.getCurrentPlayState() == PlayerStateCallback.PLAYER_VIDEO_PAUSE) {
                    this.mPlayerView.resumeVideo();
                } else {
                    Logger.e("LiveActivity", "已回收，重新请求播放");
                    mPlayerView.playVideo(testEditText.getText() + "", "测试频道");
                }
            }
//
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (this.mPlayerView != null) {
            this.mPlayerView.pauseVideo();
            isBackgroud = true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onDestroy() {
        mPlayerView.destroyVideo();
        mPlayerLayoutView.removeAllViews();
        isBackgroud = false;
    }

}
