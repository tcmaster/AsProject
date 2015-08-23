package com.android.tonight8.ui.activity.live;

import android.os.Bundle;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;

public class LiveManageActivity extends BaseActivity {

//    private WheelView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_manage);
        initWheelView();
    }

    private void initWheelView() {
//        wv = (WheelView) findViewById(R.id.wheel_view_wv);
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i < 31; i++) {
//            list.add(i + "");
//        }
//        ;
//        wv.setOffset(0);
//        wv.setItems(list);
//        wv.setSeletion(1);
//        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(int selectedIndex, String item) {
//                LogUtils.d("[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
//            }
//        });
    }

}
