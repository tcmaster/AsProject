/**
 * 2015-1-4
 */
package com.android.tonight8.ui.adapter.event;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.ui.adapter.ViewHolder;
import com.android.tonight8.dao.entity.Subject;

/**
 * @Description:
 * @author:LiXiaoSong
 * @see:
 * @since:
 * @copyright @tonight8
 * @Date:2015-1-4
 */
public class GoodRightAdapter extends BaseListAdapter<Subject> {
	public GoodRightAdapter(Context context, List<Subject> values) {
		super(context, values);
	}

	@Override
	protected View getItemView(View convertView, int position) {
		Subject model = mValues.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.item_fg_goods_detail_right, null, false);
		}
		ImageView iv_photo = ViewHolder.get(convertView, R.id.iv_photo);// 用户头像
		TextView tv_commit = ViewHolder.get(convertView, R.id.tv_commit);// 用户咨询
		TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);// 时间
		//bmUtils.display(iv_photo, model.get.pic);
		tv_commit.setText(model.getContent());
		//tv_time.setText(model.consult.date + " " + model.consult.time);
		return convertView;
	}

	/**
	 * 增加数据
	 */
	public void addData(List<Subject> data) {
		mValues.addAll(data);
		notifyDataSetChanged();
	}

	public void updateData(List<Subject> data) {
		mValues.clear();
		addData(data);
	}

}
