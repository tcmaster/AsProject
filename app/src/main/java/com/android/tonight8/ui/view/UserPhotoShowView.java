package com.android.tonight8.ui.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tonight8.R;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.util.LogUtils;

import java.util.List;

/**
 * 用户中奖名单头像显示控件
 */
public class UserPhotoShowView extends LinearLayout {
    private BitmapUtils bmUtils;

    public UserPhotoShowView(Context context) {
        super(context);
        init(context);
    }

    public UserPhotoShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserPhotoShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        bmUtils = new BitmapUtils(getContext());
        int size = Utils.dip2px(context, 30);
        bmUtils.configDefaultBitmapMaxSize(new BitmapSize(size, size));
    }

    public void addModel(List<String> models) {
        //有可能出现布局重用的情况，在增加之前，首先移除所有子item
        this.removeAllViews();
        for (int i = 0; i < models.size(); i++) {
            CircleImageView iv_user = createIv();
            if (i >= 3) {
                break;
            } else {
                this.addView(iv_user);
                bmUtils.display(iv_user, models.get(i));

            }
        }
        if (models.size() > 0) {
            CircleImageView iv_user = createIv();
            iv_user.setImageDrawable(getResources().getDrawable(R.drawable.shape_circle_purple));
            iv_user.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开启activity，进入用户信息展示页面
                    LogUtils.v("点击了多的内容，进入展示页面");
                }
            });
            this.addView(iv_user);
        }
    }

    private CircleImageView createIv() {
        CircleImageView iv_user = new CircleImageView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(Utils.dip2px(getContext(), 30), Utils.dip2px(getContext(), 30));
        lp.gravity = Gravity.CENTER_VERTICAL;
        lp.leftMargin = Utils.dip2px(getContext(), 10);
        iv_user.setLayoutParams(lp);
        return iv_user;
    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }
}
