package com.android.tonight8.ui.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义Dialog 可以自定义Dialog的布�?，以及定义布�?上的监听事件，不影响界面上键盘的弹出
 * 
 * @author lixiaosong
 */
public class CustomerDialog {

	private Activity context;
	private int res;
	private CustomerViewInterface listener;
	private AlertDialog dlg;
	/**
	 * dialog的位置
	 */
	public int gravity = Gravity.CENTER;

	/**
	 * 
	 * @param context
	 *            与dialog关联的上下文
	 * @param res
	 *            自定义dialog的资源id
	 */
	public CustomerDialog(Activity context, int res) {
		this.context = context;
		this.res = res;
	}

	/**
	 * 调用这个构造方法之后必须调用init方法
	 */
	public CustomerDialog() {

	}

	public void init(Activity context, int res) {
		this.context = context;
		this.res = res;
	}

	/**
	 * 在调用这个方法之前最好先调用setOnCustomerViewCreated来控制dialog自定义界面上的内�?
	 */
	public void showDlg() {
		dlg = new Builder(context).create();
		dlg.setCanceledOnTouchOutside(true);
		dlg.setCancelable(true);
		dlg.show();
		Window window = dlg.getWindow();
		// 下面的清除flag主要是为了在dialog中有editText时弹出软件盘�?用�??
		window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		window.setContentView(res);
		if (Gravity.CENTER != gravity)
			window.setGravity(gravity);
		if (listener != null) {
			listener.getCustomerView(window, dlg);
		}
	}

	/**
	 * @Description:在showDlg之前调用，用于设置dialog的位置
	 * @author: LiXiaoSong
	 * @date:2015-2-9
	 */
	public void setLayoutGravity(int gravity) {
		this.gravity = gravity;
	}

	public void setDismissIfClick(boolean ifClick) {
		if (dlg != null) {
			dlg.setCancelable(ifClick);
			dlg.setCanceledOnTouchOutside(ifClick);
		}
	}

	public void dismissDlg() {
		if (dlg != null) {
			dlg.dismiss();
		}
	}

	public AlertDialog getDlg() {
		return dlg;
	}

	public interface CustomerViewInterface {

		public void getCustomerView(final Window window, final AlertDialog dlg);
	}

	/**
	 * 确认，取消按钮的回调接口
	 * 
	 * @author LiXiaoSong
	 * 
	 */
	public interface ClickCallBack {

		public void onOk(CustomerDialog dlg);

		public void onCancel(CustomerDialog dlg);
	}

	public void setOnCustomerViewCreated(CustomerViewInterface listener) {
		this.listener = listener;
	}
}
