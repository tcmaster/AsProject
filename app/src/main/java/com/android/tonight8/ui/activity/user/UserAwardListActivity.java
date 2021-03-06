package com.android.tonight8.ui.activity.user;

import android.os.Bundle;

import com.android.tonight8.R;
import com.android.tonight8.ui.adapter.user.UserAwardListAdapter;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.ui.view.xlistview.XListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description:用户中奖码
 * @author LiXiaoSong
 * @date 2015-1-17
 */
public class UserAwardListActivity extends BaseActivity {

	/** 用户中奖码列表 */
	@ViewInject(R.id.lv_only_list)
	private XListView lv_award_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_only_list);
		super.onCreate(savedInstanceState);
		initDatas();
	}

	private void initDatas() {
		getActionBarBase("中奖码");
		lv_award_list.setAdapter(new UserAwardListAdapter(this, null));
	}
}
