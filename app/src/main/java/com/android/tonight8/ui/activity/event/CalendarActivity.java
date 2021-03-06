package com.android.tonight8.ui.activity.event;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.utils.DateTimeUtils;
import com.android.tonight8.ui.view.MyCalendarView;
import com.android.tonight8.ui.view.MyCalendarView.OnMyItemClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description:选择日期的页面
 * @author:LiuZhao
 * @Date:2015年2月13日
 */
public class CalendarActivity extends BaseActivity {

	/** */
	@ViewInject(R.id.cv_select_calendar)
	private MyCalendarView cv_select_calendar;
	private String[] selectData = { "2015-02-25", "2015-02-23", "2015-02-18", "2014-12-25", "2015-03-25", "2015-04-25",
			"2015-04-15", "2015-04-20" };
	private String strSelectDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_select_calendar);
		super.onCreate(savedInstanceState);
		selectData = getIntent().getStringArrayExtra("selectdata");
		cv_select_calendar.setSelectMore(false);
		cv_select_calendar.setSelectedOtherDay(selectData);
		cv_select_calendar.setOnItemClickListener(new OnMyItemClickListener() {

			@Override
			public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate) {
				if (!isInculdeDate(downDate)) {
					strSelectDate = DateTimeUtils.dateToStr(downDate);
					Intent intent = new Intent();
					intent.putExtra("downdate", strSelectDate);
					setResult(RESULT_OK, intent);
					CalendarActivity.this.finish();
				}
			}
		});
	}

	private boolean isInculdeDate(Date downDate) {
		for (int i = 0; i < selectData.length; i++) {
			if (selectData[i].equals(DateTimeUtils.dateToStr(downDate))) {
				return true;
			}
		}
		return false;
	}

}
