package com.android.tonight8.ui.fragment.livemanage;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.tonight8.R;
import com.android.tonight8.function.ScrollTopOrBottomListener;
import com.android.tonight8.ui.activity.live.EventLivePlayActivity;
import com.android.tonight8.ui.adapter.event.PlayBillListAdapter;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.io.event.EventIOController;
import com.android.tonight8.io.event.entity.PlayListNetEntity;
import com.android.tonight8.io.net.NetEntityBase;
import com.android.tonight8.io.net.NetRequest.RequestResult;
import com.android.tonight8.ui.view.ListViewForScrollView;
import com.android.tonight8.ui.view.xlistview.XListView;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Descripton 用于现场直播的fragment（节目单）
 * @author LiXiaoSong
 * @2015-6-10
 * @Tonight8
 */
public class ProgramListFragment extends BaseFragment {
	/** 父View */
	private View view;
	/** 节目单列表 */
	@ViewInject(R.id.lv_only_list)
	private XListView lv_only_list;
	/** 节目单列表 */
	private PlayBillListAdapter adapter;
	/**
	 * 头部
	 */
	private View myHeader;

	public static ProgramListFragment newInstance() {
		return new ProgramListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.activity_only_list,
					null);
			ViewUtils.inject(this, view);
			lv_only_list.setPullLoadEnable(false);
			lv_only_list.setPullRefreshEnable(false);
		}
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initDatas();
	}

	private void initDatas() {
		EventIOController.eventPlayBillRead(22l,
				new RequestResult<PlayListNetEntity>(PlayListNetEntity.class,
						null) {

					@Override
					public void onFailure(HttpException error, String msg) {

					}

					@Override
					public void getData(NetEntityBase netEntityBase,
							final PlayListNetEntity t, Handler handler) {
						getActivity().runOnUiThread(new Runnable() {

							@Override
							public void run() {
								if (adapter == null) {
									adapter = new PlayBillListAdapter(
											getActivity(), t.getPlaybillList());
									lv_only_list.setAdapter(adapter);
								} else
									adapter.notifyDataSetChanged();
							}
						});
					}
				});
		myHeader = ((EventLivePlayActivity) getActivity()).getHeader();
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.header_main_live, null);
		v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(), 350)));
		lv_only_list.addExtraHeaderView(v);
		//滑动的处理
		lv_only_list.setOnScrollListener(new ScrollTopOrBottomListener(lv_only_list, myHeader));
	}
}
