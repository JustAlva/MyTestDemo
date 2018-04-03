package com.lib.utils.blankj.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.blankj.utils.DialogHelper;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlankjPermissionActivity extends BaseActivity implements OnCheckDoubleClick {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tv_blankj_permission_content)
    TextView tvContent;
    @BindView(R.id.cv_blankj_permission_setting)
    CardView cvSetting;
    @BindView(R.id.cv_blankj_permission_location)
    CardView cvLocation;
    @BindView(R.id.cv_blankj_permission_phone_detail)
    CardView cvPhoneState;

    private String permissions;
    private List<String> listPermission = new ArrayList<>();
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_blankj_permission);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setTopBarTitle(tvTopbarTitle, "Blankj权限相关", true, vsTopbarBack);
        StringBuilder sb = new StringBuilder();
        for (String s : PermissionUtils.getPermissions()) {
            listPermission.add(s);
            sb.append(s.substring(s.lastIndexOf('.') + 1)).append("\n");
        }
        permissions = sb.toString();
    }

    @Override
    protected void initView() {
        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        //
        cvSetting.setOnClickListener(doubleClickListener);
        cvLocation.setOnClickListener(doubleClickListener);
        cvPhoneState.setOnClickListener(doubleClickListener);
    }


    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.cv_blankj_permission_setting:
                //
                PermissionUtils.launchAppDetailsSettings();
                break;

            case R.id.cv_blankj_permission_location:
                PermissionUtils.permission(PermissionConstants.LOCATION)
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                            }
                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                showToast(getResources().getString(R.string.permission_rationale_message));
                            }
                        })
                        .theme(new PermissionUtils.ThemeCallback() {
                            @Override
                            public void onActivityCreate(Activity activity) {
                                ScreenUtils.setFullScreen(activity);
                            }
                        })
                        .request();
                break;

            case R.id.cv_blankj_permission_phone_detail:
                //读取手机相关信息
                PermissionUtils.permission(PermissionConstants.PHONE)
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                                showToast(getResources().getString(R.string.permission_rationale_message));
                            }
                        })
                        .request();
                break;
        }
    }

    private void updateAboutPermission() {
        SpanUtils show = new SpanUtils();
        for (int i = 0; i < listPermission.size(); i++) {
            show.appendLine(listPermission.get(i) + ":" + PermissionUtils.isGranted(listPermission.get(i)));
        }
       // Manifest.permission.READ_CALENDAR
        tvContent.setText(show.create());
    }

}
