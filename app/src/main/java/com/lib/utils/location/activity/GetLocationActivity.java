package com.lib.utils.location.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ScreenUtils;
import com.lib.ui.layout.dialog.material.DialogAction;
import com.lib.ui.layout.dialog.material.MaterialDialog;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.location.bean.AMapMarkBean;
import com.lib.utils.location.utils.GDLocationUtils;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe: 定位
 * creator: keding.zhou
 * date: 2018/1/30 15:10
 */
public class GetLocationActivity extends BaseActivity implements LocationSource, AMapLocationListener, AMap.OnMyLocationChangeListener, OnCheckDoubleClick {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.map_location)
    MapView mMapView;
    @BindView(R.id.cv_get_location_work_sign)
    CardView cvSign;
    @BindView(R.id.tv_get_location_work_time)
    TextView tvTime;
    @BindView(R.id.ll_get_location_work_click_start)
    LinearLayout llClickStart;
    @BindView(R.id.tv_get_location_work_sign_time)
    TextView tvSignTime;
    @BindView(R.id.tv_get_location_work_sign_location)
    TextView tvSignLocation;
    @BindView(R.id.ll_get_location_work_click_end)
    LinearLayout llClickEnd;
    @BindView(R.id.tv_get_location_work_off_time)
    TextView tvOffTime;
    @BindView(R.id.cv_get_location_work_off_sign)
    CardView cvOffSign;
    @BindView(R.id.ll_get_location_work_off_click_start)
    LinearLayout llOffClickStart;
    @BindView(R.id.tv_get_location_work_off_sign_time)
    TextView tvOffSignTime;
    @BindView(R.id.tv_get_location_work_off_sign_location)
    TextView tvOffSignLocation;
    @BindView(R.id.ll_get_location_work_off_click_end)
    LinearLayout llOffClickEnd;

    private AMap aMap;//地图
    private MyLocationStyle myLocationStyle;
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    //标记点list
    private List<AMapMarkBean> mMarkBeanList = new ArrayList<>();

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_get_location);
        ButterKnife.bind(this);
        //标题
        setTopBarTitle(tvTopbarTitle, "高德地图", true, vsTopbarBack);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        //添加标记点
        AMapMarkBean bean1 = new AMapMarkBean(31.254204, 120.621306, "总部", "科沃斯机器人股份 有限公司", true);
        AMapMarkBean bean2 = new AMapMarkBean(31.2545780000, 120.6141690000, "门店", "TEK白科技", false);
        mMarkBeanList.add(bean1);
        mMarkBeanList.add(bean2);
        //显示上班打卡时间
        // new TimeThread().start();
        initTime(1);
    }

    //显示时间
    private void initTime(final int code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                do {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = code;
                        mHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            long time = System.currentTimeMillis();
            Date date = new Date(time);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            switch (msg.what) {
                case 1:
                    tvTime.setText(format.format(date));
                    break;
                case 2:
                    tvOffTime.setText(format.format(date));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        com.blankj.utilcode.util.PermissionUtils.permission(PermissionConstants.LOCATION)
                .callback(new com.blankj.utilcode.util.PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //同意
                        initMap();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        //拒绝
                        showToast(getResources().getString(R.string.permission_rationale_message));
                    }
                })
                .theme(new com.blankj.utilcode.util.PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);
                    }
                })
                .request();

     /*  if (PermissionUtils.hasLocationPermission(GetLocationActivity.this  , 1)
                ) {
            // 获取权限 requestCode = 1

        }*/
        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        cvSign.setOnClickListener(doubleClickListener);
        cvOffSign.setOnClickListener(doubleClickListener);

    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    initMap();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showToast("无法获取定位权限！");
                }
                return;
        }
    }

    @Override
    public void onCheckDoubleClick(View view) {
        long time = System.currentTimeMillis();
        final Date date = new Date(time);
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        switch (view.getId()) {
            case R.id.cv_get_location_work_sign:
                //上班打卡
                if (null != aMap) {
                    //隐藏打卡按钮，显示打卡结果
                    llClickStart.setVisibility(View.GONE);
                    llClickEnd.setVisibility(View.VISIBLE);
                    tvSignTime.setText("上班打卡时间" + format.format(date));
                    AMapLocation mLocation = (AMapLocation) aMap.getMyLocation();
                    tvSignLocation.setText(mLocation.getAddress());
                    //显示下班打卡布局
                    llOffClickStart.setVisibility(View.VISIBLE);
                    //初始化下班打卡显示的时间
                    initTime(2);
                } else {
                    showToast("无法获取定位信息，请确定相关权限设置！");
                }
                break;
            case R.id.cv_get_location_work_off_sign:
                //下班打卡
                if (null != aMap) {
                    new MaterialDialog.Builder(this)
                            .title("打卡提示")
                            .content("确定打卡下班？")
                            .positiveText("OK")
                            .negativeText("CANCLE")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {

                                    //隐藏打卡按钮，显示打卡结果
                                    llOffClickStart.setVisibility(View.GONE);
                                    llOffClickEnd.setVisibility(View.VISIBLE);
                                    tvOffSignTime.setText("下班打卡时间" + format.format(date));
                                    AMapLocation mOffLocation = (AMapLocation) aMap.getMyLocation();
                                    tvOffSignLocation.setText(mOffLocation.getAddress());

                                }
                            })
                            .show();
                } else {
                    showToast("无法获取定位信息，请确定相关权限设置！");
                }
                break;
        }
    }

    //定位
    private void initMap() {

        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//定位模式，且将视角移动到地图中心点。
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//定位按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);//缩放按钮
        aMap.getUiSettings().setZoomGesturesEnabled(false);//缩放手势
        aMap.getUiSettings().setScrollGesturesEnabled(false);//滑动手势
        aMap.getUiSettings().setRotateGesturesEnabled(false);//旋转手势
        aMap.getUiSettings().setTiltGesturesEnabled(false);//倾斜手势
        //添加标记点
        GDLocationUtils.addMark(GetLocationActivity.this, aMap, mMarkBeanList);
        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(15);
        aMap.moveCamera(mCameraUpdate);//缩放界别
        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);

        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if (location != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                 /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }


}
