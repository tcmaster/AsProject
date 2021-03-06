package com.android.tonight8.ui.activity.org;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.Tonight8App;
import com.android.tonight8.io.net.NetEntityBase;
import com.android.tonight8.io.net.NetRequest;
import com.android.tonight8.io.net.NetRequest.RequestResult;
import com.android.tonight8.utils.AlbumAndCamera;
import com.android.tonight8.utils.DialogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @Description:认证照片上传页面
 * @author:LiuZhao
 * @Date:2015年2月27日
 */
public class AuthPicActivity extends BaseActivity {

	/** 营业执照 */
	@ViewInject(R.id.rl_org_license)
	private RelativeLayout rl_org_license;
	/** 营业执照 */
	@ViewInject(R.id.iv_org_license)
	private ImageView iv_org_license;
	/** 身份证前面 */
	@ViewInject(R.id.rl_identity_front)
	private RelativeLayout ll_identity_front;
	/** 身份证前面 */
	@ViewInject(R.id.iv_identity_front)
	private ImageView iv_identity_front;
	/** 身份证后面 */
	@ViewInject(R.id.rl_identity_reverse)
	private RelativeLayout ll_identity_reverse;
	/** 身份证后面 */
	@ViewInject(R.id.iv_identity_reverse)
	private ImageView iv_identity_reverse;
	/** 0营业执照1身份证前面2身份证后面 */
	private int imageType = 0;
	/** 营业执照 身份证前面 身份证后面 */
	private String licensePicPath, idFrontPicPath, idReversePicPath;

	// private ButtonOnClick buttonOnClick = new ButtonOnClick() {
	//
	// @Override
	// public void getButton(Button leftButton, Button rightButton, final
	// AlertDialog dlg) {
	//
	// leftButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View arg0) {
	//
	// getPhotoFromGallery();
	// dlg.dismiss();
	// }
	// });
	//
	// rightButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View arg0) {
	// getPhotoByTakePicture();
	// dlg.dismiss();
	// }
	// });
	// }
	// };
	public static Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

		}

	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		getWindow().getDecorView().invalidate();
		if (resultCode != RESULT_OK) {
			return;
		}
		File tempFile = new File(Environment.getExternalStorageDirectory()
				+ "/Camera/", tempName);

		switch (imageType) {

		case 0: // 0营业执照
			if (requestCode == PICKPICTURE) {
				cropPicture(data.getData(), PICKPICTURE, 256, 256);
			} else if (requestCode == TAKEPHOTO) {
				cropPicture(Uri.fromFile(tempFile), PICKPICTURE, 256, 256);
			} else if (requestCode == CROP) {

				Uri cropImageUri = data.getData();
				// 图片解析成Bitmap对象
				Bitmap bitmap = null;
				try {
					bitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(cropImageUri));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					bitmap.recycle();
				}
				licensePicPath = AlbumAndCamera.getImagePath(
						AlbumAndCamera.getTempPath(), bitmap);
			}

			break;

		case 1: // 1身份证前面
			if (requestCode == PICKPICTURE) {
				cropPicture(data.getData(), PICKPICTURE, 256, 256);
			} else if (requestCode == TAKEPHOTO) {
				cropPicture(Uri.fromFile(tempFile), PICKPICTURE, 256, 256);
			} else if (requestCode == CROP) {
				Uri cropImageUri = data.getData();
				// 图片解析成Bitmap对象
				Bitmap bitmap = null;
				try {
					bitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(cropImageUri));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					bitmap.recycle();
				}
				idFrontPicPath = AlbumAndCamera.getImagePath(
						AlbumAndCamera.getTempPath(), bitmap);
			}
			break;
		case 2: // 2身份证后面
			if (requestCode == PICKPICTURE) {
				cropPicture(data.getData(), PICKPICTURE, 256, 256);
			} else if (requestCode == TAKEPHOTO) {
				cropPicture(Uri.fromFile(tempFile), PICKPICTURE, 256, 256);
			} else if (requestCode == CROP) {
				Uri cropImageUri = data.getData();
				// 图片解析成Bitmap对象
				Bitmap bitmap = null;
				try {
					bitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(cropImageUri));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					bitmap.recycle();
				}
				idReversePicPath = AlbumAndCamera.getImagePath(
						AlbumAndCamera.getTempPath(), bitmap);
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_auth_pic);
		super.onCreate(savedInstanceState);
		getActionBarBase("认证");
		initData();
	}

	@OnClick({ R.id.rl_org_license, R.id.rl_identity_front,
			R.id.rl_identity_reverse })
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.rl_org_license:
			imageType = 0;
			break;
		case R.id.rl_identity_front:
			imageType = 1;
			break;
		case R.id.rl_identity_reverse:
			imageType = 2;
			break;
		case R.id.btn_auth_ok:
			upAuthPic();
			break;

		default:
			break;
		}
		DialogUtils.showSelectPicDialog(AuthPicActivity.this, PICKPICTURE,
				TAKEPHOTO);
	}

	private void initData() {
		if (getIntent().getBooleanExtra("isPerson", true)) {
			rl_org_license.setVisibility(View.GONE);
		}

	}

	/** 上传认证图片 */
	private void upAuthPic() {
		Map<String, String> params = new HashMap<String, String>();
		params.put(NetRequest.REQUEST_URL, NetRequest.BASE_URL);
		RequestResult<String> callback = new RequestResult<String>(null,
				mHandler) {

			@Override
			public void onFailure(HttpException error, String msg) {

			}

			@Override
			public void getData(NetEntityBase netEntityBase, String t,
					Handler handler) {

			}
		};

		NetRequest.postImageToServer(params, callback, "PIC", new File(
				licensePicPath));

	}
}
