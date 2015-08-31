package com.android.tonight8.ui.fragment.spots;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.ui.adapter.event.ShareAdapter;
import com.android.tonight8.ui.view.CustomerDialog;
import com.android.tonight8.ui.view.StationaryGridview;
import com.android.tonight8.ui.view.Wheel.NumericWheelAdapter;
import com.android.tonight8.ui.view.Wheel.OnWheelChangedListener;
import com.android.tonight8.ui.view.Wheel.WheelView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * A simple {@link Fragment} subclass.
 * 现场插播类
 */
public class LiveSpotsFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.linear1)
    private LinearLayout ll_linea1;
    @ViewInject(R.id.tv_hour)
    private TextView tv_hour;
    @ViewInject(R.id.tv_minute)
    private TextView tv_minute;
    @ViewInject(R.id.tv_second)
    private TextView tv_second;
    @ViewInject(R.id.btn_preview)
    private Button btn_preview;
    @ViewInject(R.id.btn_insert)
    private Button btn_insert;
    private String strHour, strMinute, strSecond;
    @ViewInject(R.id.gv_insertitem)
    private StationaryGridview stationaryGridview;

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
        ShareAdapter shareAdapter = new ShareAdapter(activity);
        stationaryGridview.setAdapter(shareAdapter);
        stationaryGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear1:
                selectTimeDialog();
                break;
            case R.id.btn_preview:

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
}
