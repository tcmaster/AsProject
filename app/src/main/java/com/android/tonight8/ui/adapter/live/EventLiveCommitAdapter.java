package com.android.tonight8.ui.adapter.live;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.dao.model.live.EventLiveList;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.ui.adapter.ViewHolder;
import com.android.tonight8.ui.view.CircleImageView;

import java.util.List;

/**
 * @author LiXiaoSong
 * @Descripton 活动现场用户评论
 * @2015-6-12
 * @Tonight8
 */
public class EventLiveCommitAdapter extends BaseListAdapter<EventLiveList> {
    public EventLiveCommitAdapter(Context context, List<EventLiveList> values) {
        super(context, values);
    }

    @Override
    protected View getItemView(View convertView, int position) {
        EventLiveList model = mValues.get(position);
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
        RelativeLayout rl_more_info = ViewHolder.get(convertView, R.id.rl_more_info);//红包或者开奖说明
        ImageView iv_red_more_info = ViewHolder.get(convertView, R.id.iv_red_more_info);//附加内容图片
        TextView tv_more_info = ViewHolder.get(convertView, R.id.tv_more_info);//附加内容文字说明
        switch (model.getLive().getType()) {
            case 0://普通话题
                tv_live_talk_content.setVisibility(View.VISIBLE);
                iv_img_contant.setVisibility(View.GONE);
                rl_more_info.setVisibility(View.GONE);
                tv_live_talk_content.setText(model.getLive().getSubject().getContent());
                break;
            case 1://图片
                tv_live_talk_content.setVisibility(View.GONE);
                iv_img_contant.setVisibility(View.VISIBLE);
                rl_more_info.setVisibility(View.GONE);
                bmUtils.display(iv_img_contant, model.getPhotos().get(0).getUrl());
                break;
            case 2://红包
                tv_live_talk_content.setVisibility(View.GONE);
                iv_img_contant.setVisibility(View.GONE);
                rl_more_info.setVisibility(View.VISIBLE);
                iv_red_more_info.setImageResource(R.mipmap.c5s2p1e001);
                tv_more_info.setText("恭喜发财，大吉大利，领取红包");
                rl_more_info.setBackgroundResource(R.mipmap.c9s2p2e001);
                break;
            case 3://开奖
                tv_live_talk_content.setVisibility(View.GONE);
                iv_img_contant.setVisibility(View.GONE);
                rl_more_info.setVisibility(View.VISIBLE);
                bmUtils.display(iv_red_more_info, model.getPopPic().getUrl());
                tv_more_info.setText("开奖啦!\n本次共有：" + model.getAwardHit().getCount() + "人中奖\n看看谁有这么好运");
                rl_more_info.setBackgroundResource(R.mipmap.c9s2p2e002);
                break;
            default:
                break;
        }

        tv_live_talk_time.setText(model.getLive().getSubject().getDate()
                + model.getLive().getSubject().getTime());
        tv_live_talk_content.setText(model.getLive().getSubject().getContent());
        if (model.getUser() != null) {//角色内容字段的处理
            tv_live_talk_role.setVisibility(View.INVISIBLE);
            tv_live_talk_name.setText(model.getUser().getName());
            bmUtils.display(iv_live_comment_headpic, model.getUser().getPic());
        } else if (model.getSeller() != null) {
            tv_live_talk_role.setVisibility(View.VISIBLE);
            tv_live_talk_role.setText("卖手");
            tv_live_talk_name.setText(model.getSeller().getName());
            bmUtils.display(iv_live_comment_headpic, model.getSeller().getPic());
        } else if (model.getOrg() != null) {
            tv_live_talk_role.setVisibility(View.VISIBLE);
            tv_live_talk_role.setText("商家");
            tv_live_talk_name.setText(model.getOrg().getName());
            bmUtils.display(iv_live_comment_headpic, model.getOrg().getPic());
        }
        return convertView;
    }
}
