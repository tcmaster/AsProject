package com.android.tonight8.ui.activity.live;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.easemob.util.VoiceRecorder;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 类描述:
 * 创建人：asus
 * 创建时间：2015/9/15 17:59
 * 修改时间：2015/9/15 17:59
 * 修改备注：
 */

public class SpotVideoActivity extends BaseActivity {
    private CheckBox cb_playrecord;
    @ViewInject(R.id.tv_record_count)
    private TextView tv_record_count;
    @ViewInject(R.id.tv_total_count)
    private TextView tv_total_count;
    private ListView lv_video;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    /**
     * 本界面的录音器
     */
    private VoiceRecorder voiceRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotvideo);
        getActionBarRight("视频插入", "保存", onClickListener);
        initView();


    }


    private void initView() {
        lv_video = (ListView) findViewById(R.id.lv_video);
        cb_playrecord = (CheckBox) findViewById(R.id.cb_playrecord);
        cb_playrecord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }


}
