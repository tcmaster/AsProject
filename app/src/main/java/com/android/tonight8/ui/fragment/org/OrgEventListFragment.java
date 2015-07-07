package com.android.tonight8.ui.fragment.org;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.tonight8.R;
import com.android.tonight8.ui.activity.event.UserAgreementActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.dao.model.event.EventList;
import com.android.tonight8.ui.view.xlistview.XListView;

/**
 * @author lz商家活动列表
 * 
 */
public class OrgEventListFragment extends BaseFragment {

	private XListView lv_only_list;
	private List<EventList> list;
	private View rootView;

	public static final OrgEventListFragment newInstance() {
		OrgEventListFragment orgEventListFragment = new OrgEventListFragment();
		return orgEventListFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		rootView = inflater.inflate(R.layout.activity_only_list, null);
		lv_only_list = (XListView) rootView.findViewById(R.id.lv_only_list);
		list = new ArrayList<EventList>();
		lv_only_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivityForAnima(new Intent(activity,
						UserAgreementActivity.class), null);

			}
		});
		return rootView;
	}

}
