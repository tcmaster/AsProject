package com.android.tonight8.ui.adapter.live;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.ui.adapter.ViewHolder;
import com.android.tonight8.dao.model.live.SubjectList;
import com.android.tonight8.dao.entity.ActionItem;
import com.android.tonight8.utils.Utils;
import com.android.tonight8.ui.view.CircleImageView;
import com.android.tonight8.ui.view.CommitPopup;
import com.android.tonight8.ui.view.CommitPopup.onPostClick;
import com.android.tonight8.ui.view.TitlePopup;
import com.android.tonight8.ui.view.TitlePopup.OnItemOnClickListener;

/**
 * 
 * @Descripton 活动现场用户评论
 * @author LiXiaoSong
 * @2015-6-12
 * @Tonight8
 */
public class EventLiveCommitAdapter extends BaseListAdapter<SubjectList> {
	public EventLiveCommitAdapter(Context context, List<SubjectList> values) {
		super(context, values);
	}

	@Override
	protected View getItemView(View convertView, int position) {
		SubjectList subjectList = mValues.get(position);
		if (convertView == null)
			convertView = mInflater.inflate(R.layout.item_live_commit, null);
		CircleImageView iv_live_comment_headpic = ViewHolder.get(convertView,
				R.id.iv_live_talk_headpic);// 用户头像
		TextView tv_live_talk_name = ViewHolder.get(convertView,
				R.id.tv_live_talk_name);//姓名
		TextView tv_live_talk_time = ViewHolder.get(convertView,
				R.id.tv_live_talk_time);//时间
		TextView tv_live_talk_content = ViewHolder.get(convertView,
				R.id.tv_live_talk_content);//文字内容
		FrameLayout fl_container = ViewHolder.get(convertView, R.id.fl_container);//红包或额外内容
		TextView tv_live_talk_role = ViewHolder.get(convertView, R.id.tv_live_talk_role);//角色
		ImageView iv_img_contant = ViewHolder.get(convertView, R.id.iv_img_contant);//图片内容
		LinearLayout ll_more_info = ViewHolder.get(convertView, R.id.ll_more_info);//红包或者开奖说明
		ImageView iv_red_more_info = ViewHolder.get(convertView, R.id.iv_red_more_info);//附加内容图片
		TextView tv_more_info = ViewHolder.get(convertView, R.id.tv_more_info);//附加内容文字说明
		tv_live_talk_name.setText(subjectList.getUser().getName());
		tv_live_talk_time.setText(subjectList.getSubject().getDate()
				+ subjectList.getSubject().getTime());
		tv_live_talk_content.setText(subjectList.getSubject().getContent());
		bmUtils.display(iv_live_comment_headpic, subjectList.getUser().getPic());
		return convertView;
	}
}
