package com.lib.utils.datepicker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.TimePickerView;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.datepicker.utils.PickerViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerActivity extends BaseActivity {

    @BindView(R.id.btn_datepicker_ios_date)
    Button btnDate;
    @BindView(R.id.btn_datepicker_ios_location)
    Button btnLocation;

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_date_picker);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.btn_datepicker_ios_date, R.id.btn_datepicker_ios_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_datepicker_ios_date:
                TimePickerView pvTime = PickerViewUtils.getDatePickerView(DatePickerActivity.this, selectDateListener);
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.btn_datepicker_ios_location:
                break;
        }
    }
    TimePickerView.OnTimeSelectListener selectDateListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            btnDate.setText(getTime(date));
        }
    };

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }


}
