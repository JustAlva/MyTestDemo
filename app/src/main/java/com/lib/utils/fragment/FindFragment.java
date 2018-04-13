package com.lib.utils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.SelectPicture.activity.SelectPictureActivity;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.calendar.activity.CalendarActivity;
import com.lib.utils.coordinatorlayout.activity.CoordinatorLayoutActivity;
import com.lib.utils.datepicker.activity.DatePickerActivity;
import com.lib.utils.filedownload.activity.FileDownloadActivity;
import com.lib.utils.location.activity.GetLocationActivity;
import com.lib.utils.qrcode.activity.QrCodeActivity;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;
import com.lib.utils.views.MenuLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * describe: 发现
 * creator: keding.zhou
 * date: 2018/1/24 14:21
 */
public class FindFragment extends BaseFragment implements OnCheckDoubleClick{


    Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.ml_find_scan)
    MenuLinearLayout mlScan;
    @BindView(R.id.ml_find_datepicker)
    MenuLinearLayout mlDatePicker;
    @BindView(R.id.ml_find_coordinatorlayout)
    MenuLinearLayout mlCoordinatorLayut;
    @BindView(R.id.ml_find_calendar)
    MenuLinearLayout mlCalendar;
    @BindView(R.id.ml_find_location)
    MenuLinearLayout mlLocation;
    @BindView(R.id.ml_find_select_picture)
    MenuLinearLayout mlSelectPicture;
    @BindView(R.id.ml_find_file_download)
    MenuLinearLayout mlFileDownload;
    @BindView(R.id.ml_find_location2)
    MenuLinearLayout mlLocation2;

    @Override
    protected View setContentView(LayoutInflater inflater) {
        View convertView = inflater.inflate(R.layout.fragment_main_find, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        //标题
        tvTopbarTitle.setText("发现");

        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        //扫描
        mlScan.setOnClickListener(doubleClickListener);
        //日历
        mlCalendar.setOnClickListener(doubleClickListener);
        //CoordinatorLayout
        mlCoordinatorLayut.setOnClickListener(doubleClickListener);
        //时间选择器
        mlDatePicker.setOnClickListener(doubleClickListener);
        //定位
        mlLocation.setOnClickListener(doubleClickListener);
        //定位指向
        mlLocation2.setOnClickListener(doubleClickListener);
        //图片选择
        mlSelectPicture.setOnClickListener(doubleClickListener);
        //文件下载
        mlFileDownload.setOnClickListener(doubleClickListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ml_find_scan:
                //扫描
                startActivity(QrCodeActivity.class);
                break;
            case R.id.ml_find_datepicker:
                //时间选择器
                startActivity(DatePickerActivity.class);
                break;
            case R.id.ml_find_coordinatorlayout:
                //CoordinatorLayout
                startActivity(CoordinatorLayoutActivity.class);
                break;
            case R.id.ml_find_calendar:
                //日历
                startActivity(CalendarActivity.class);
                break;
            case R.id.ml_find_location:
                //定位
                startActivity(GetLocationActivity.class);
                break;
            case R.id.ml_find_location2:
                //定位指向

                break;
            case R.id.ml_find_select_picture:
                //图片选择
                startActivity(SelectPictureActivity.class);
                break;
            case R.id.ml_find_file_download:
                //文件下载
                startActivity(FileDownloadActivity.class);
                break;
        }
    }
}
