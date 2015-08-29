package com.android.tonight8.ui.adapter.live;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.dao.entity.VoteItem;
import com.android.tonight8.dao.entity.VoteItemOption;
import com.android.tonight8.io.live.LiveIOController;
import com.android.tonight8.io.net.NetEntityBase;
import com.android.tonight8.io.net.NetRequest;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.ui.adapter.ViewHolder;
import com.android.tonight8.ui.view.CustomerRadioGroup;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.util.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/21 0021
 */
public class EventLiveVoteAdapter extends BaseListAdapter<VoteItem> {
    private final int[] rbids = {R.id.rb_one, R.id.rb_two, R.id.rb_three, R.id.rb_four, R.id.rb_five, R.id.rb_six, R.id.rb_seven};
    private List<VoteItemOption> voteItemOptions; //投票的选项
    private Map<Long, Long> checkResult;
    private int topMargin;
    private boolean commitOk;//投票是否成功

    public EventLiveVoteAdapter(Context context, List<VoteItem> values, List<VoteItemOption> voteItemOptions) {
        super(context, values);
        this.voteItemOptions = voteItemOptions;
        checkResult = new HashMap<>();
        topMargin = Utils.dip2px(getContext(), 20);
        commitOk = false;//以后需要专门传入参数来确定当前投票是否成功
    }

    public void update(List<VoteItem> values, List<VoteItemOption> option) {
        super.update(values);
        this.voteItemOptions = option;
        checkResult.clear();
    }

    @Override
    protected View getItemView(View convertView, int position) {
        VoteItem model = mValues.get(position);
        if (convertView == null) convertView = mInflater.inflate(R.layout.item_live_vote, null);
        ImageView iv_circle = ViewHolder.get(convertView, R.id.iv_circle);
        TextView tv_vote_title = ViewHolder.get(convertView, R.id.tv_vote_title);
        final CustomerRadioGroup rg_vote = ViewHolder.get(convertView, R.id.rg_vote);
        ImageView iv_vote_line = ViewHolder.get(convertView, R.id.iv_vote_line);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv_vote_line.getLayoutParams();
        if (position == 0) {
            lp.topMargin = topMargin;
            iv_vote_line.setLayoutParams(lp);
        } else {
            lp.topMargin = 0;
            iv_vote_line.setLayoutParams(lp);
        }
        if (position % 3 == 0) {
            iv_circle.setImageResource(R.drawable.shape_circle_vote1);
            tv_vote_title.setTextColor(getContext().getResources().getColor(R.color.vote_title1));
        } else if (position % 3 == 1) {
            iv_circle.setImageResource(R.drawable.shape_circle_vote2);
            tv_vote_title.setTextColor(getContext().getResources().getColor(R.color.vote_title2));
        } else if (position % 3 == 2) {
            iv_circle.setImageResource(R.drawable.shape_circle_vote3);
            tv_vote_title.setTextColor(getContext().getResources().getColor(R.color.vote_title3));
        }
        tv_vote_title.setText(model.getName());
        processRG(model.getId(), rg_vote);
        return convertView;
    }

    private void processRG(long id, CustomerRadioGroup rg_vote) {
        int count = 0;
        rg_vote.removeAllViews();//防止重复
        for (int i = 0; i < voteItemOptions.size(); i++) {
            if (voteItemOptions.get(i).getVoteItemId() == id) {
                LinearLayout rl_vote = (LinearLayout) mInflater.inflate(R.layout.item_vote_option, null);
                TextView tv_vote_title = (TextView) rl_vote.findViewById(R.id.tv_vote_title);
                TextView tv_vote_result = (TextView) rl_vote.findViewById(R.id.tv_vote_result);
                RadioButton rb = (RadioButton) rl_vote.findViewById(R.id.rb_vote);
                tv_vote_title.setText(voteItemOptions.get(i).getName());
                if (commitOk) {
                    rb.setVisibility(View.GONE);
                    tv_vote_result.setVisibility(View.VISIBLE);
                    tv_vote_result.setText(voteItemOptions.get(i).getOptionCount() + "");
                } else {
                    rb.setVisibility(View.VISIBLE);
                    tv_vote_result.setVisibility(View.GONE);
                    rb.setId(rbids[count]);//该值不能大于7
                    rb.setTag(voteItemOptions.get(i));
                    rg_vote.setOnCheckedChangeListener(new CustomerRadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CustomerRadioGroup group, int checkedId) {
                            RadioButton rb = (RadioButton) group.findViewById(checkedId);
                            VoteItemOption option = (VoteItemOption) rb.getTag();
                            LogUtils.v("有东西已经被选择了");
                            checkResult.put(option.getVoteItemId(), option.getId());//选项的结果
                        }
                    });
                }
                rg_vote.addView(rl_vote);
                count++;
            }
        }
    }

    /**
     * 是否所有内容都进行了投票
     */
    private boolean isAllCheck() {
        return !(checkResult == null || checkResult.size() < mValues.size());
    }

    public boolean doCommit() {
        if (!isAllCheck()) {
            return false;
        } else {
            //处理提交结果
            LiveIOController.writeVoteCommit(checkResult, new NetRequest.RequestResult<NetEntityBase>(NetEntityBase.class, null) {
                @Override
                public void onFailure(HttpException e, String s) {
                    ((BaseActivity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utils.toast("提交失败");
                        }
                    });
                }

                @Override
                public void getData(NetEntityBase netEntityBase, NetEntityBase netEntityBase2, Handler handler) {
                    ((BaseActivity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utils.toast("提交成功");
                            commitOk = true;
                            for (int i = 0; i < mValues.size(); i++) {
                                long voteItemId = mValues.get(i).getId();
                                long voteItemOptionId = checkResult.get(voteItemId);
                                for (int j = 0; j < voteItemOptions.size(); j++) {
                                    VoteItemOption option = voteItemOptions.get(j);
                                    if (option.getVoteItemId() == voteItemId && option.getId() == voteItemOptionId) {
                                        option.setOptionCount(option.getOptionCount() + 1);
                                    }
                                }
                            }
                            notifyDataSetChanged();
                        }
                    });
                }

            });
            return true;
        }
    }
}
