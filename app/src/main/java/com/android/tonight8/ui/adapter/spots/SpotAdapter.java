package com.android.tonight8.ui.adapter.spots;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.AppConstants;

/**
 * @Description:分享的数据适配器
 * @author:LiuZhao
 * @Date:2015年2月9日
 */
public class SpotAdapter extends BaseAdapter {

    private String[] strMenu = {"图片", "字幕", "音频", "视频", "外链"};
    private int[] intMenu = {R.mipmap.c7s5p2e001, R.mipmap.c7s5p2e002, R.mipmap.c7s5p2e003, R.mipmap.c7s5p2e004, R.mipmap.c7s5p2e005};
    private Context context;
    private LayoutInflater mLiInflater;

    public SpotAdapter(Context context) {
        super();
        this.context = context;
        mLiInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return strMenu.length;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLiInflater.inflate(R.layout.adapter_postevents_grid, null, false);
            holder.tv_grid_title = (TextView) convertView.findViewById(R.id.tv_grid_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_grid_title.setText(strMenu[position]);
        Drawable drawable = context.getResources().getDrawable(intMenu[position]);
        drawable.setBounds(0, 0, AppConstants.widthPx / 6, AppConstants.widthPx / 6);
        holder.tv_grid_title.setCompoundDrawables(null, drawable, null, null);
        return convertView;
    }

    class ViewHolder {

        TextView tv_grid_title;
    }
}