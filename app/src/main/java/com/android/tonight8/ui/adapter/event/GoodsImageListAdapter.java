package com.android.tonight8.ui.adapter.event;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.ui.adapter.BaseListAdapter;
import com.android.tonight8.base.Tonight8App;
import com.android.tonight8.dao.entity.Goods;

/**
 * @Description:发活动第一步用到的奖品详情列表数据适配器
 * @author:LiuZhao
 * @Date:2015年1月29日
 */
public class GoodsImageListAdapter extends BaseListAdapter<Goods> {

	public GoodsImageListAdapter(Context context, List<Goods> values) {
		super(context, values);

	}

	@Override
	protected View getItemView(View convertView, int position) {
		ViewHoler holer = null;
		if (convertView == null) {
			holer = new ViewHoler();
			convertView = mInflater.inflate(R.layout.adapter_goodsimage_list,
					null);
			holer.iv_adapter_goods = (ImageView) convertView
					.findViewById(R.id.iv_adapter_goods_temp);
			holer.tv_adapter_goods_price = (TextView) convertView
					.findViewById(R.id.tv_adapter_goodsName);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		Goods goods = mValues.get(position);
		// holer.iv_adapter_goods.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// DialogUtils.showSelectPicDialog((BaseActivity) mContext);
		//
		// }
		// });
		// holer.tv_adapter_goods_price.setOnClickListener(new OnClickListener()
		// {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		return convertView;
	}

	class ViewHoler {

		ImageView iv_adapter_goods;
		TextView tv_adapter_goods_price;
	}

}
