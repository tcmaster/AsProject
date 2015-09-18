package com.android.tonight8.ui.fragment.livemanage;

import java.lang.ref.WeakReference;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.tonight8.R;
import com.android.tonight8.function.ScrollTopOrBottomListener;
import com.android.tonight8.ui.activity.live.EventLivePlayActivity;
import com.android.tonight8.ui.adapter.live.EventLiveWinnerListAdapter;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.dao.model.live.EventAward;
import com.android.tonight8.io.HandlerConstants;
import com.android.tonight8.io.live.LiveIOController;
import com.android.tonight8.ui.view.ListViewForScrollView;
import com.android.tonight8.ui.view.xlistview.XListView;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Descripton 用于现场直播的fragment（获奖名单）
 * @author LiXiaoSong
 * @2015-6-10
 * @Tonight8
 */
public class WinnerListFragment extends BaseFragment {
	/** 父View */
	private View view;
	@ViewInject(R.id.lv_winner_list)
	private XListView lv_winner_list;
	private EventLiveWinnerListAdapter adapter;
	private MyHandler handler;
	private View myHeader;
	public static WinnerListFragment newInstance() {
		return new WinnerListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_live_winner_list, null);
			ViewUtils.inject(this, view);
			lv_winner_list.setPullLoadEnable(false);
			lv_winner_list.setPullRefreshEnable(false);
		}
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initDatas();
	}

	private void initDatas() {
		handler = new MyHandler(this);
		LiveIOController.readLiveWinnerList(handler);
		myHeader = ((EventLivePlayActivity) getActivity()).getHeader();
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.header_main_live, null);
		v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(), 350)));
		lv_winner_list.addExtraHeaderView(v);
		//滑动的处理
		lv_winner_list.setOnScrollListener(new ScrollTopOrBottomListener(lv_winner_list, myHeader));
	}

	private static class MyHandler extends Handler {
		WeakReference<WinnerListFragment> ref;

		public MyHandler(WinnerListFragment wf) {
			ref = new WeakReference<WinnerListFragment>(wf);
		}

		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case HandlerConstants.Live.LIVE_WINNER_LIST:
					switch (msg.arg1) {
						case HandlerConstants.RESULT_OK:
							if (ref.get().adapter == null) {
								ref.get().adapter = new EventLiveWinnerListAdapter(ref.get().getActivity(),
										(List<EventAward>) msg.obj);
								ref.get().lv_winner_list.setAdapter(ref.get().adapter);
							} else {
								ref.get().adapter.notifyDataSetChanged();
							}
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
		}
	}
}
