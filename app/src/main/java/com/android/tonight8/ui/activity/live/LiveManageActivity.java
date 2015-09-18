package com.android.tonight8.ui.activity.live;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.ui.fragment.spots.LiveSpotsFragment;
import com.android.tonight8.ui.fragment.spots.VideoSpotsFragment;
import com.android.tonight8.utils.AlbumAndCamera;
import com.lecloud.common.cde.LeCloud;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileNotFoundException;


public class LiveManageActivity extends BaseActivity {
    @ViewInject(R.id.rg_zhibochabo)
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private BaseFragment[] baseFragments;
    @ViewInject(R.id.iv_arrow)
    private ImageView iv_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeCloud.init(getApplicationContext());
        initCreateNomal(savedInstanceState, R.layout.activity_live_manage);
        initData();
        initListener();
    }

    private void initData() {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        ft = fm.beginTransaction();
        baseFragments = new BaseFragment[2];
        baseFragments[0] = LiveSpotsFragment.newInstance();
        baseFragments[1] = VideoSpotsFragment.newInstance();
        ft.add(R.id.fl_chabo, baseFragments[1]);
        ft.add(R.id.fl_chabo, baseFragments[0]);
        ft.commit();
        radioGroup.check(R.id.rb_liveplay);

    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_liveplay:
                        doFragmentShow(0);
                        break;
                    case R.id.rb_videoplay:
                        doFragmentShow(1);
                        break;
                }
            }
        });
    }


    /**
     * @param which 第几个对象
     * @Description:显示当前的fragment对象
     * @author: LiXiaoSong
     * @date:2015-2-6
     */
    private void doFragmentShow(int which) {
        ft = fm.beginTransaction();
        for (int i = 0; i < baseFragments.length; i++) {
            if (which == i) {
                ft.show(baseFragments[i]);
            } else
                ft.hide(baseFragments[i]);
        }
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getWindow().getDecorView().invalidate();
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case PICKPICTURE:
                cropPicture(data.getData(), CROP, 256, 256);
                break;
            case TAKEPHOTO:
                File tempFile = new File(Environment.getExternalStorageDirectory()
                        + "/Camera/", tempName);
                cropPicture(Uri.fromFile(tempFile), CROP, 256, 256);
                break;
            case CROP:
                Uri cropImageUri = data.getData();
                // 图片解析成Bitmap对象
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver()
                            .openInputStream(cropImageUri));
                    String tempPicPath = AlbumAndCamera.getImagePath(
                            AlbumAndCamera.getTempPath(), bitmap);
                    ((LiveSpotsFragment) baseFragments[0]).updateImageSource(tempPicPath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }

                }

                break;
            case 21:
                if (data != null)
                    ((LiveSpotsFragment) baseFragments[0]).updateRecordData(data.getStringArrayListExtra("RECORDFILESTRING"));
                break;
            default:
                break;
        }
    }
}
