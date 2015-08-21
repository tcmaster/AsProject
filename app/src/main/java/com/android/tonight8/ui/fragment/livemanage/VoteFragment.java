package com.android.tonight8.ui.fragment.livemanage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;

/**
 * @Descripton 用于现场直播的fragment（投票）
 * @author LiXiaoSong
 * @2015-6-10
 * @Tonight8
 */
public class VoteFragment extends BaseFragment {

	/** 父View */
	private View view;

	public static VoteFragment newInstance() {
		return new VoteFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fg_vote, null);
			ViewUtils.inject(this, view);
		}
		return view;
	}
}
