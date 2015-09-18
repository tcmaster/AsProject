package com.android.tonight8.ui.adapter.spots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.AppConstants;
import com.android.tonight8.easemob.EaseMobVoiceHelper;
import com.android.tonight8.ui.activity.live.SpotRecordActivity;
import com.android.tonight8.ui.view.recordbutton.RecordEntity;

import java.util.List;


/**
 * 类描述:
 * 创建人：asus
 * 创建时间：2015/9/7 17:43
 * 修改时间：2015/9/7 17:43
 * 修改备注：
 */

public class ReacordListAdapter extends BaseAdapter {
    private static final String TAG = ReacordListAdapter.class.getSimpleName();
    private List<RecordEntity> coll;
    private Context ctx;
    private LayoutInflater mInflater;

    public ReacordListAdapter(Context context, List<RecordEntity> coll) {
        ctx = context;
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        if (coll == null)
            return 0;
        return coll.size();
    }

    public Object getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final RecordEntity entity = coll.get(position);

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.adapter_record_list, null);
            viewHolder = new ViewHolder();
            viewHolder.cbSelectedRecord = (CheckBox) convertView
                    .findViewById(R.id.cb_selected_record);
            viewHolder.tv_chatContent = (TextView) convertView
                    .findViewById(R.id.tv_chatContent);
            viewHolder.ll_time = (LinearLayout) convertView
                    .findViewById(R.id.ll_time);
            viewHolder.ll_click = (LinearLayout) convertView
                    .findViewById(R.id.ll_click);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cbSelectedRecord.setTag(position);
        viewHolder.cbSelectedRecord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int pos = (int) compoundButton.getTag();
                coll.get(pos).setIsChecked(b);
                notifyDataSetChanged();
            }
        });
        if (entity != null && entity.getName() != null) {
            viewHolder.cbSelectedRecord.setChecked(entity.isChecked());
            viewHolder.ll_click.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (EaseMobVoiceHelper.isPlaying()) {
                        EaseMobVoiceHelper.stopVoice();
                    } else {
                        EaseMobVoiceHelper.doPlay(entity.getFilepath(), (SpotRecordActivity) ctx);
                    }
                }
            });
            if (entity.getTime() == 0) {
                viewHolder.tv_chatContent.setText(entity.getName());
            } else {
                viewHolder.tv_chatContent.setText(getSecondToMinu(entity.getTime()));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AppConstants.widthPx * entity.getTime() / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                viewHolder.ll_time.setLayoutParams(params);
            }


        }

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_chatContent;
        public CheckBox cbSelectedRecord;
        public LinearLayout ll_time;
        public LinearLayout ll_click;

    }

    private String getSecondToMinu(int second) {
        String min = null;
        if (second >= 60) {
            return second / 60 + "\'" + second % 60 + "\'";
        }
        min = second + "\'";

        return min;
    }

}
