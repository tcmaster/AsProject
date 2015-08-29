package com.android.tonight8.io.live.entity;

import com.android.tonight8.dao.model.live.EventGoods;
import com.android.tonight8.io.net.NetEntityBase;

public class GoodsDetailNetEntity extends NetEntityBase {
	private EventGoods eventGoods;

	public EventGoods getEventGoods() {
		return eventGoods;
	}

	public void setEventGoods(EventGoods eventGoods) {
		this.eventGoods = eventGoods;
	}

	@Override
	public String toString() {
		return "GoodsDetailNetEntity [eventGoods=" + eventGoods + "]";
	}

}
