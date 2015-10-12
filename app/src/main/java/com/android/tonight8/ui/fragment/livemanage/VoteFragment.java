package com.android.tonight8.ui.fragment.livemanage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.dao.entity.Vote;
import com.android.tonight8.dao.entity.VoteItem;
import com.android.tonight8.dao.entity.VoteItemOption;
import com.android.tonight8.dao.model.live.VoteShow;
import com.android.tonight8.function.ScrollTopOrBottomListener;
import com.android.tonight8.io.HandlerConstants;
import com.android.tonight8.io.live.LiveIOController;
import com.android.tonight8.ui.activity.live.EventLivePlayActivity;
import com.android.tonight8.ui.adapter.live.EventLiveVoteAdapter;
import com.android.tonight8.ui.view.ListViewForScrollView;
import com.android.tonight8.ui.view.xlistview.XListView;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXiaoSong
 * @Descripton 用于现场直播的fragment（投票）
 * @2015-6-10
 * @Tonight8
 */
public class VoteFragment extends BaseFragment {

    /**
     * 附加的头部
     */
    View myHeader;
    /**
     * 父View
     */
    private View view;
    private MyHandler handler;
    private EventLiveVoteAdapter adapter;
    /**
     * 投票标题
     */
    private TextView tv_vote_title;
    /**
     * 投票描述
     */
    private TextView tv_vote_describe;
    /**
     * 投票列表
     */
    @ViewInject(R.id.lv_only_list)
    private XListView lv_vote;
    /**
     * 投票提交
     */
    private Button btn_submit;

    public VoteFragment() {
        handler = new MyHandler(this);
    }

    public static VoteFragment newInstance() {
        return new VoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_only_list, null);
            ViewUtils.inject(this, view);
        }
        lv_vote.setPullLoadEnable(false);
        lv_vote.setPullRefreshEnable(false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }

    @Override
    public void scrollToTop() {
        if(lv_vote != null) {
            lv_vote.setSelection(0);
            if (myHeader != null) myHeader.setTranslationY(0);
        }
    }

    private void initDatas() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View header = inflater.inflate(R.layout.header_vote, null);
        View footer = inflater.inflate(R.layout.footer_vote, null);
        tv_vote_title = (TextView) header.findViewById(R.id.tv_vote_title);
        tv_vote_describe = (TextView) header.findViewById(R.id.tv_vote_describe);
        btn_submit = (Button) footer.findViewById(R.id.btn_submit);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.header_main_live, null);
        v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(), 350)));
        lv_vote.addExtraHeaderView(v, header);
        lv_vote.addExtraFooterView(footer);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交投票内容
                if (adapter != null) {
                    if (!adapter.doCommit())//所有的内容都被选择了
                        Utils.toast("您还有内容未提交");
                }
            }
        });
        LiveIOController.readVoteShow(handler);
        myHeader = ((EventLivePlayActivity) getActivity()).getHeader();
        //滑动的处理
        lv_vote.setOnScrollListener(new ScrollTopOrBottomListener(lv_vote, myHeader));
    }

    private static class MyHandler extends Handler {
        private WeakReference<VoteFragment> ref;

        public MyHandler(VoteFragment vf) {
            ref = new WeakReference<VoteFragment>(vf);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerConstants.Live.LIVE_VOTE:
                    switch (msg.arg1) {
                        case HandlerConstants.RESULT_OK:
                            VoteShow show = (VoteShow) msg.obj;
                            show = processVirualData();
                            if (ref.get().adapter == null) {
                                ref.get().adapter = new EventLiveVoteAdapter(ref.get().getActivity(), show.getVoteItems(), show.getVoteItemOptions());
                                ref.get().lv_vote.setAdapter(ref.get().adapter);
                            } else
                                ref.get().adapter.update(show.getVoteItems(), show.getVoteItemOptions());
                            ref.get().tv_vote_title.setText(show.getVote().getName());
                            ref.get().tv_vote_describe.setText(show.getVote().getDescribe());
                            break;
                    }
                    break;
            }
        }

        private VoteShow processVirualData() {
            VoteShow vs = new VoteShow();
            Vote vote = new Vote();
            vote.setId(1000);
            vote.setDescribe("这是一次华丽得，绚烂的投票，所有内容都是华丽的，绚烂的等待");
            vote.setName("华丽投票");
            vote.setTotalCount(1200);
            List<VoteItem> vitems = new ArrayList<VoteItem>();
            List<VoteItemOption> vitemo = new ArrayList<VoteItemOption>();
            for (int i = 0; i < 10; i++) {
                VoteItem voteItem = new VoteItem();
                voteItem.setId(i);
                voteItem.setName("我需要的投票" + i);
                voteItem.setVoteCount(i * (int) (Math.random() * 2000));
                for (int j = 0; j < 4; j++) {
                    VoteItemOption voteItemOption = new VoteItemOption();
                    voteItemOption.setId(j);
                    voteItemOption.setName("选项" + j);
                    voteItemOption.setVoteItemId((long) i);
                    voteItemOption.setOptionCount(i * (int) (Math.random() * 2000));
                    vitemo.add(voteItemOption);
                }
                vitems.add(voteItem);
            }
            vs.setVote(vote);
            vs.setVoteItems(vitems);
            vs.setVoteItemOptions(vitemo);
            return vs;
        }
    }


}
