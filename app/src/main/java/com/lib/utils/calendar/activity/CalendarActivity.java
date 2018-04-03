package com.lib.utils.calendar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.calendar.activity.colorful.ColorfulActivity;
import com.lib.utils.calendar.activity.index.IndexActivity;
import com.lib.utils.calendar.activity.simple.SimpleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * describe: 日历展示
 * creator: keding.zhou
 * date: 2018/1/26 11:16
 */
public class CalendarActivity extends BaseActivity implements
        CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener ,CalendarView.OnMonthChangeListener {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tv_month_day)
    TextView tvMonthDay;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.tv_current_day)
    TextView tvCurrentDay;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.ll_simple)
    LinearLayout llSimple;
    @BindView(R.id.ll_index)
    LinearLayout llIndex;
    @BindView(R.id.ll_colorful)
    LinearLayout llColorful;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;

    private int mYear;

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        setTopBarTitle(tvTopbarTitle, "日历", true, vsTopbarBack);
    }

    @Override
    protected void initData() {
        final List<Calendar> schemes = new ArrayList<>();
        final int year = mCalendarView.getCurYear();
        final int month = mCalendarView.getCurMonth();
        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        mCalendarView.setSchemeDate(schemes);
    }

    @Override
    protected void initView() {
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        tvYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        tvMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        tvLunar.setText("今日");
        tvCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    /**
     * 选择年监听
     * @param year
     */
    @Override
    public void onYearChange(int year) {
        tvMonthDay.setText(String.valueOf(year));
    }

    /**
     * 日期选择监听
     * @param calendar
     * @param isClick
     */
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        tvLunar.setVisibility(View.VISIBLE);
        tvYear.setVisibility(View.VISIBLE);
        tvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        tvYear.setText(String.valueOf(calendar.getYear()));
        tvLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
        if (isClick) {
            Toast.makeText(this, getCalendarText(calendar), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 月份改变监听,监听日历左右滑动切换月份，可在此刷新标记等
     * @param year
     * @param month
     */
    @Override
    public void onMonthChange(int year, int month) {

    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    private static String getCalendarText(Calendar calendar) {
        return String.format("新历%s \n 农历%s \n 公历节日：%s \n 农历节日：%s \n 节气：%s \n 是否闰月：%s",
                calendar.getMonth() + "月" + calendar.getDay() + "日",
                calendar.getLunarCakendar().getMonth() + "月" + calendar.getLunarCakendar().getDay() + "日",
                TextUtils.isEmpty(calendar.getGregorianFestival()) ? "无" : calendar.getGregorianFestival(),
                TextUtils.isEmpty(calendar.getTraditionFestival()) ? "无" : calendar.getTraditionFestival(),
                TextUtils.isEmpty(calendar.getSolarTerm()) ? "无" : calendar.getSolarTerm(),
                calendar.getLeapMonth() == 0 ? "否" : String.format("润%s月", calendar.getLeapMonth()));
    }

    //点击事件
    @OnClick({R.id.ll_simple, R.id.ll_index, R.id.ll_colorful})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_simple:
                //简单类型的
                startActivity(SimpleActivity.class);
                break;
            case R.id.ll_index:
                //下标型
                startActivity(IndexActivity.class);
                break;
            case R.id.ll_colorful:
                //多彩的
                startActivity(ColorfulActivity.class);
                break;
        }
    }


}
