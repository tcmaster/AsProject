package com.android.tonight8.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.tonight8.R;
import com.android.tonight8.ui.activity.live.EventLivePlayActivity;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.easemob.EaseMobManager;
import com.android.tonight8.easemob.EaseMobManager.LoginCallBack;
import com.android.tonight8.ui.fragment.main.MyAccountFragment;
import com.android.tonight8.ui.fragment.main.TonightEightFragment;
import com.android.tonight8.ui.fragment.main.WishMainFragment;
import com.android.tonight8.storage.DBUtil;
import com.android.tonight8.ui.view.RegionalSortPopupWindow;
import com.android.tonight8.ui.view.SlideView;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description:主界面
 * @author:LiXiaoSong
 * @Date:2014年12月17日
 */
public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener {

	/** 下方radioGroup */
	private RadioGroup rg_mian;
	/** 下方中间的按钮 */
	private ImageView iv_main_center;
	/** 左边的radioButton */
	private RadioButton rb_main_left;
	/** 右边的radioButton */
	private RadioButton rb_main_right;
	/** 本界面的popupwindow */
	private RegionalSortPopupWindow window;
	/** "我"的界面 */
	private View leftView;
	/** 主界面 */
	private View rightView;
	/** 头部界面 */
	private View headView;

	/** slideView */
	@ViewInject(R.id.sv_main_page)
	private SlideView sv_main;
	/** Fragment管理类 */
	private FragmentManager fragmentManager;
	/** 判断跳转到哪个页面的下标 */
	private int switchid = 0;
	// 自定义actionBar相关（主页）
	/** 主页左边按钮（我） */
	private ImageView iv_left_btn;
	/** 主页最右边按钮 */
	private ImageView iv_right_btn;
	/** 主页较右边按钮 */
	private ImageView iv_right_btn2;
	private RadioGroup rg_center;
	/** 中间左边的选择 */
	@ViewInject(R.id.rb_left)
	private RadioButton rb_left;
	/** 中间右边的选择 */
	@ViewInject(R.id.rb_right)
	private RadioButton rb_right;
	/** 页面信息标题数组 */
	private String[] titleArr = { "8点", "愿望列表" };
	private FragmentTransaction fragmentTransaction;
	/** 右边界面所用到的fragment */
	private BaseFragment[] fragments;
	/** 左边界面所用到的fragment */
	private MyAccountFragment myAccountFragment;

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		/* 处理被单击的按钮 */
		RadioButton mRadioButton = (RadioButton) arg0.findViewById(arg1);
		switchid = Integer.parseInt(mRadioButton.getTag().toString());
		if (arg0.getId() == R.id.radio_group) {
			doFragmentShow(switchid);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCreateOverLay(savedInstanceState, R.layout.activity_main);
		initMainInterface();
		initActionBar();
		final String userName = "liuzhao";
		// 测试用，登录环信账号
		EaseMobManager.loginAccount(userName, "123", new LoginCallBack() {

			@Override
			public void onSuccess() {
				LogUtils.v("登录环信成功，用户名" + userName);
				// EaseMobManager.sendTxtMessage("liuzhao", "helloboy", null);
			}

			@Override
			public void onError(int code, String msg) {

			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EaseMobManager.logout();
		DBUtil.close();// 关闭数据库
	}

	private void initMainInterface() {
		rightView = LayoutInflater.from(this).inflate(
				R.layout.layout_main_right, null);
		leftView = LayoutInflater.from(this).inflate(
				R.layout.fragment_main_left, null);
		headView = LayoutInflater.from(this).inflate(R.layout.actionbar_main,
				null);
		rg_mian = (RadioGroup) rightView.findViewById(R.id.radio_group);
		iv_main_center = (ImageView) rightView
				.findViewById(R.id.iv_main_center);
		sv_main.initView(leftView, rightView);
		// 界面初始化工作
		sv_main.post(new Runnable() {

			@Override
			public void run() {
				initFragment();
			}
		});
		iv_main_center.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				processCenterClick();
			}
		});
	}

	private void initActionBar() {
		rb_main_right = (RadioButton) rightView
				.findViewById(R.id.rb_main_right);
		iv_left_btn = (ImageView) rightView.findViewById(R.id.iv_left_btn);
		rb_main_left = (RadioButton) rightView.findViewById(R.id.rb_main_left);
		rb_left = (RadioButton) rightView.findViewById(R.id.rb_left);
		rb_right = (RadioButton) rightView.findViewById(R.id.rb_right);
		rg_center = (RadioGroup) rightView.findViewById(R.id.rg_center);
		rg_center.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb_left:
					if (rb_main_right.isChecked()) {
						WishMainFragment wFragment = (WishMainFragment) fragments[1];
						wFragment.doFragmentShow(0);
					} else if (rb_main_left.isChecked()) {
						TonightEightFragment tFragment = (TonightEightFragment) fragments[0];
						tFragment.changeLeft();
					}
					break;
				case R.id.rb_right:
					if (rb_main_right.isChecked()) {
						WishMainFragment wFragment = (WishMainFragment) fragments[1];
						wFragment.doFragmentShow(1);
					} else if (rb_main_left.isChecked()) {
						TonightEightFragment tFragment = (TonightEightFragment) fragments[0];
						tFragment.changeRight();
					}
					break;

				default:
					break;
				}

			}
		});
		iv_left_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (sv_main.isOpen()) {
					sv_main.closeMenu();
				} else
					sv_main.openMenu();

			}
		});
	}

	private void initFragment() {
		fragments = new BaseFragment[2];
		fragments[0] = TonightEightFragment.newInstance();
		myAccountFragment = MyAccountFragment.newInstance();
		fragments[1] = WishMainFragment.newInstance();
		/* 设置跳转那个fragment页面 */
		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.tabcontent, fragments[0], titleArr[0]);
		fragmentTransaction.add(R.id.tabcontent, fragments[1], titleArr[1]);
		fragmentTransaction.add(R.id.left_content, myAccountFragment);
		fragmentTransaction.commit();
		/* fragment管理器初始化 */
		/* 实始化下方单选按钮组 */
		rg_mian.setOnCheckedChangeListener(this);
		((RadioButton) rg_mian.getChildAt(0)).setChecked(true);
	}

	private void processCenterClick() {
		Intent intent = new Intent(this, EventLivePlayActivity.class);
		startActivity(intent);
	}

	/**
	 * @Description:得到当前用户的fragment对象
	 * @return
	 * @author: LiXiaoSong
	 * @date:2015-2-6
	 */
	public MyAccountFragment getMyAccountFragment() {
		return null;
	}

	/**
	 * @Description:显示当前的fragment对象
	 * @param which
	 *            第几个对象
	 * @author: LiXiaoSong
	 * @date:2015-2-6
	 */
	private void doFragmentShow(int which) {
		if (which == 0) {
			rb_left.setText("今日");
			rb_right.setText("预告");
			((RadioButton) rg_center.getChildAt(0)).setChecked(true);
		} else {
			rb_left.setText("未实现");
			rb_right.setText("已实现");
			((RadioButton) rg_center.getChildAt(0)).setChecked(true);
		}

		fragmentTransaction = fragmentManager.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			if (which == i) {

				fragmentTransaction.show(fragments[i]);
			} else {

				fragmentTransaction.hide(fragments[i]);
			}
		}
		fragmentTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		if (window != null && window.isShowing()) {
			rlDown();
			window.dismissPopWindow();
			return;
		} else
			super.onBackPressed();
	}

}
