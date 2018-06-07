package com.lib.utils;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.lib.utils.base.BaseActivity;
import com.lib.utils.fragment.FindFragment;
import com.lib.utils.fragment.HomeFragment;
import com.lib.utils.fragment.NewsFragment;
import com.lib.utils.fragment.PersonalFragment;
import com.lib.utils.views.NoScrollViewPager;
import com.lib.utils.xinge.beans.MessageBean;
import com.orhanobut.logger.Logger;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  {

    @BindView(R.id.tl_main_tab)
    TabLayout tlMainTab;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;

    private List<Fragment> mListPages = new ArrayList<>();
    private MyPagerAdapter mPagerAdapter;
    private String[] tabTitleStr = new String[]{"首页", "发现", "消息", "我的"};
    private int[] tabIconRes = new int[]{R.drawable.tab_icon_follow, R.drawable.tab_icon_find, R.drawable.tab_icon_news, R.drawable.tab_icon_personal};

    private HomeFragment mHomeFragment = new HomeFragment();
    private FindFragment mFindFragment = new FindFragment();
    private NewsFragment mNewsFragment = new NewsFragment();
    private PersonalFragment mPersonalFragment = new PersonalFragment();

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        getJuris(1);
    }


    public void getJuris(int key) {
        /**
         * ContextCompat.checkSelfPermission，主要用于检测某个权限是否已经被授予，
         * 方法返回值为 PackageManager.PERMISSION_DENIED 或者 PackageManager.PERMISSION_GRANTED。
         * 当返回 DENIED 就需要进行申请授权了。
         */
        switch (key) {
            case 1:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            key);
                } else {
                    getJuris(2);
                }
                break;
            case 2:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            key);
                } else {
                    getJuris(3);
                }
                break;
            case 3:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            key);
                } else {
                    initXinge();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // GetPermissionsUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getJuris(2);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showToast("无法获取相应权限！");
                }
                return;
            case 2:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getJuris(3);
                } else {
                    showToast("无法获取相应权限！");
                }
                return;
            case 3:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initXinge();
                } else {
                    showToast("无法获取相应权限！");
                }
                return;
        }
    }

    private void initXinge() {
        XGPushConfig.enableDebug(this, true);
        //注册数据更新监听器
        MsgReceiver  updateListViewReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
        registerReceiver(updateListViewReceiver, intentFilter);
        XGPushManager.registerPush(MainActivity.this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                showToast( "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                showToast("注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        // 获取token
        String token = XGPushConfig.getToken(this);
        showToast(token);
        Log.i("zkd","[MainActivity][initXinge]==> token : "+ token );
       // XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.setTag(this, "XINGE");
    }

    @Override
    protected void initView() {
        Log.i("zkd", "this is log debug");
        Logger.d("this is Logger debug");
        mListPages.add(mHomeFragment);
        mListPages.add(mFindFragment);
        mListPages.add(mNewsFragment);
        mListPages.add(mPersonalFragment);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mPagerAdapter);
        //不可滑动
        vpMain.setScanScroll(false);

        /**
         * 要先关联，再设置tabLayout的标题和icon，
         * 不然 setupWithViewPager 关联里面的 populateFromPagerAdapter() 方法会 removeAllTabs() 清空tab
         */
        tlMainTab.setupWithViewPager(vpMain);
        for (int i = 0; i < tlMainTab.getTabCount(); i++) {
            tlMainTab.getTabAt(i).setText(tabTitleStr[i]);
            tlMainTab.getTabAt(i).setIcon(tabIconRes[i]);
        }
        //绑定
        vpMain.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    public void updatePushData(MessageBean msg) {
        mHomeFragment.hasNewMsg(msg);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mListPages.get(position);
        }

        @Override
        public int getCount() {
            return mListPages.size();
        }
    }

    //用于接收推送接收器收到的信息内容
    public class MsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null!=intent) {
                Bundle bundle = intent.getExtras();
                if (null!=bundle) {
                    MessageBean msgBean = (MessageBean) bundle.get("msg");
                    updatePushData(msgBean);
                }
            }
        }
    }
}
