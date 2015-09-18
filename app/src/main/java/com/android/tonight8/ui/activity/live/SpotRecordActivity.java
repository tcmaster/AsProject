package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.Tonight8App;
import com.android.tonight8.easemob.EaseMobVoiceHelper;
import com.android.tonight8.ui.adapter.spots.ReacordListAdapter;
import com.android.tonight8.ui.view.recordbutton.RecordEntity;
import com.android.tonight8.ui.view.recordbutton.SoundMeter;
import com.android.tonight8.utils.FileUtils;
import com.android.tonight8.utils.Utils;
import com.easemob.util.PathUtil;
import com.easemob.util.VoiceRecorder;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpotRecordActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox cb_playrecord;
    @ViewInject(R.id.tv_record_count)
    private TextView tv_record_count;
    @ViewInject(R.id.tv_total_count)
    private TextView tv_total_count;
    private ListView lv_record;
    private ReacordListAdapter adapter;
    private List<RecordEntity> list;
    private ImageView img1, sc_img1;
    private SoundMeter mSensor;
    private LinearLayout del_re;
    private LinearLayout rcChat_popup;
    private RecordEntity entity;
    private TextView tv_startrecord;
    private TextView tv_delete, tv_save;
    /**
     * 本界面的录音器
     */
    private VoiceRecorder voiceRecorder;
    /**
     * 录音器的handler
     */
    private Handler voiceHandler;
    /**
     * 麦克风图片资源
     */
    private Drawable[] micImages;
    /**
     * 麦克风图像
     */
    private ImageView iv_mic_image;
    /**
     * 录音缓存的目录名称
     */
    private static String RECODE = "RECORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotrecord);
        getActionBarBase("开始录音");
        initView();
        list = new ArrayList<RecordEntity>();
        upateFileData();
        adapter = new ReacordListAdapter(SpotRecordActivity.this, list);
        lv_record.setAdapter(adapter);

    }

    //更新目录下的文件数据
    private void upateFileData() {
        List<File> listFile = FileUtils.listFile(PathUtil.getInstance().getVoicePath(), "amr");
        if (listFile != null)
            for (int i = 0; i < listFile.size(); i++) {
                RecordEntity entity = new RecordEntity();
                entity.setFilepath(listFile.get(i) + "");
                entity.setName(listFile.get(i).getName());
                entity.setTime(0);
                list.add(entity);
            }
    }

    @Override
    protected void onPause() {
        if (EaseMobVoiceHelper.isPlaying()) {
            EaseMobVoiceHelper.stopVoice();
        }
        super.onPause();
    }

    private void initView() {
        lv_record = (ListView) findViewById(R.id.lv_record);
        cb_playrecord = (CheckBox) findViewById(R.id.cb_playrecord);
        rcChat_popup = (LinearLayout) findViewById(R.id.rcChat_popup);
        cb_playrecord.setOnCheckedChangeListener(this);
        tv_startrecord = (TextView) findViewById(R.id.tv_startrecord);
        iv_mic_image = (ImageView) findViewById(R.id.iv_mic_image);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        micImages = new Drawable[]{
                getResources().getDrawable(R.mipmap.record_animate_01),
                getResources().getDrawable(R.mipmap.record_animate_02),
                getResources().getDrawable(R.mipmap.record_animate_03),
                getResources().getDrawable(R.mipmap.record_animate_04),
                getResources().getDrawable(R.mipmap.record_animate_05),
                getResources().getDrawable(R.mipmap.record_animate_06),
                getResources().getDrawable(R.mipmap.record_animate_07),
                getResources().getDrawable(R.mipmap.record_animate_08),
                getResources().getDrawable(R.mipmap.record_animate_09),
                getResources().getDrawable(R.mipmap.record_animate_10),
                getResources().getDrawable(R.mipmap.record_animate_11),
                getResources().getDrawable(R.mipmap.record_animate_12),
                getResources().getDrawable(R.mipmap.record_animate_13),
                getResources().getDrawable(R.mipmap.record_animate_14),};
        voiceHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                iv_mic_image.setImageDrawable(micImages[msg.what]);
            }
        };
        Tonight8App.getSelf().onCreate();
        PathUtil.getInstance().initDirs(null, RECODE, SpotRecordActivity.this);
        initListener();
    }

    private void initListener() {
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> templist = null;
                templist = new ArrayList<String>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        templist.add(list.get(i).getFilepath());
                    }
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra("RECORDFILESTRING", templist);
                LogUtils.d("选中的文件地址" + templist.get(0).toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        FileUtils.deleteDirectory(list.get(i).getFilepath());
                    }
                }
                upateFileData();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            String userName = System.currentTimeMillis() + "";
            // 按下之后，停止播放其他声音，开启录音模式录音
            if (EaseMobVoiceHelper.isPlaying()) {
                EaseMobVoiceHelper.stopVoice();
            }
            if (voiceRecorder == null) {
                voiceRecorder = new VoiceRecorder(voiceHandler);
            }
            rcChat_popup.setVisibility(View.VISIBLE);// 显示话筒控件
            voiceRecorder.startRecording(null, userName,
                    SpotRecordActivity.this);
            tv_startrecord.setText("停止录音");
        } else {
            rcChat_popup.setVisibility(View.GONE);// 隐藏话筒控件
            tv_startrecord.setText("开始录音");
            int second = 0;
            if (voiceRecorder.isRecording())
                second = voiceRecorder.stopRecoding();
            if (second < 1) {
                Utils.toast("录音时间太短");
                return;
            }
            File recordFile = new File(voiceRecorder.getVoiceFilePath());
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setFilepath(voiceRecorder.getVoiceFilePath());
            recordEntity.setTime(second);
            recordEntity.setName(voiceRecorder.getVoiceFileName(RECODE));
            list.add(recordEntity);
            adapter.notifyDataSetChanged();
            lv_record.setSelection(lv_record.getCount() - 1);

            LogUtils.d("录音路径" + voiceRecorder.getVoiceFilePath());
        }
    }
}
