package com.android.tonight8.io.live.entity;

import java.util.List;

import com.android.tonight8.dao.model.live.EventAward;
import com.android.tonight8.io.net.NetEntityBase;

public class EventLiveWinnerListNetEntity extends NetEntityBase {
	private List<EventAward> eventAwards;

	public List<EventAward> getEventAwards() {
		return eventAwards;
	}

	public void setEventAwards(List<EventAward> eventAwards) {
		this.eventAwards = eventAwards;
	}

	@Override
	public String toString() {
		return "EventLiveWinnerListEntity [eventAwards=" + eventAwards + "]";
	}

}
