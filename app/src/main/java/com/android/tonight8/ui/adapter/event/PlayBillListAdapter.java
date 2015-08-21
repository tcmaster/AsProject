package com.android.tonight8.ui.adapter.event;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.ui.adapter.ViewHolder;
import com.android.tonight8.dao.model.event.PlayBillList;
import com.android.tonight8.ui.view.UserPhotoShowView;
import com.android.tonight8.utils.JsonUtils;
import com.lidroid.xutils.util.LogUtils;

public class PlayBillListAdapter extends BaseListAdapter<PlayBillList> {

	public PlayBillListAdapter(Context context, List<PlayBillList> values) {
		super(context, values);
	}

	@Override
	protected View getItemView(View convertView, int position) {
		PlayBillList model = mValues.get(position);
		if (convertView == null)
			convertView = mInflater.inflate(R.layout.item_play_bill_list, null);
		TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);
		TextView tv_content = ViewHolder.get(convertView, R.id.tv_content);
		TextView tv_achieve_count = ViewHolder.get(convertView, R.id.tv_achieve_count);
		UserPhotoShowView ll_show_user = ViewHolder.get(convertView, R.id.ll_show_user);
		tv_time.setText(model.getPlaybill().getTime());
		tv_content.setText(model.getPlaybill().getContent());
		tv_achieve_count.setText(model.getPlaybill().getAwardNumber() + "");
		List<String> strs = new ArrayList<>();
		for (int i = 0; i < Math.random() * 4; i++) {
			strs.add(JsonUtils.imgs[i]);
		}
		ll_show_user.addModel(strs);
		return convertView;
	}

	public void updateData(List<PlayBillList> data) {
		mValues.clear();
		mValues.addAll(data);
		notifyDataSetChanged();
	}

}
