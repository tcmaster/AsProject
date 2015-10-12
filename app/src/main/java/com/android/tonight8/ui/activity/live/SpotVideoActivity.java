package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.utils.IntentUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 类描述:
 * 创建人：asus
 * 创建时间：2015/9/15 17:59
 * 修改时间：2015/9/15 17:59
 * 修改备注：
 */

public class SpotVideoActivity extends BaseActivity {
    @ViewInject(R.id.btn_playvideo)
    private Button btn_playvideo;
    @ViewInject(R.id.tv_record_count)
    private TextView tv_record_count;
    @ViewInject(R.id.tv_total_count)
    private TextView tv_total_count;
    @ViewInject(R.id.lv_video)
    private ListView lv_video;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotvideo);
        getActionBarRight("视频插入", "保存", onClickListener);

        initView();
    }


    private void initView() {
        btn_playvideo = (Button) findViewById(R.id.btn_playvideo);
        btn_playvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startVideo(mContext, 31);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 31) {

        }
    }
}
