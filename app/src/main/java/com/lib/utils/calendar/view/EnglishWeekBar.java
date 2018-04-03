package com.lib.utils.calendar.view;

import android.content.Context;
import android.view.LayoutInflater;
import com.haibin.calendarview.WeekBar;
import com.lib.utils.R;

/**
 * describe: 自定义英文栏 
 * creator: keding.zhou
 * date: 2018/1/30 8:38
 */
public class EnglishWeekBar extends WeekBar {
    public EnglishWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_english_week_bar, this, true);
    }
}
