package com.lib.utils.datepicker.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by keding.zhou on 2018/1/22.
 */

public class PickerViewUtils<T> {

    /**
     * 获取时间选择器布局
     *
     * @param context
     * @param listener 选择回调
     * @return
     */
    public static TimePickerView getDatePickerView(Context context, TimePickerView.OnTimeSelectListener listener ) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);

        TimePickerView pvTime = new TimePickerView.Builder(context, listener)
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示 {年，月，日，时，分，秒}
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                //.setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                // .isDialog(true)//是否显示为对话框样式 //不设置为屏幕底部
                .build();
        return pvTime;
    }

    /**
     * 地区选择器布局
     * @param context
     * @param listener
     * @param options1Items
     * @param options2Items
     * @param options3Items
     * @return
     */
    public OptionsPickerView getOptionsPickerView(Context context, OptionsPickerView.OnOptionsSelectListener listener, List<T> options1Items,
                                                  List<List<T>> options2Items,
                                                  List<List<List<T>>> options3Items ) {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, listener)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                .setLabels("省", "市", "区")//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
        return pvOptions;
    }
}
