package com.lib.utils.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lib.utils.R;
import com.lib.utils.totalutil.LoadingDialog;
import com.lib.utils.totalutil.MeasureUtil;

import java.io.File;


/**
 * describe: Activity 基类
 * creator: keding.zhou
 * date: 2017/11/29 8:47
 */
public abstract class BaseActivity extends AppCompatActivity {

    private final static String TAG_IF_ELSE = "IF_ELSE";
    //加载动画
    public LoadingDialog loadingDialog;

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setConfigs();
            setContentView(savedInstanceState);
            mContext = this;
            initData();
            initView();
        } catch (Exception e) {
            Log.e("zkd", this.getLocalClassName() + ":" + e.toString());
        }
    }

    /**
     * 设置属性
     */
    protected void setConfigs() {
        //隐藏标题栏
        getSupportActionBar().hide();
    }

    /**
     * 标题栏相关设置
     *
     * @param tvTopbarTitle 标题TextView
     * @param title         标题
     * @param isCanBack     是否有返回键
     * @param vsTopbarBack  返回键 ViewStub
     */
    protected void setTopBarTitle(TextView tvTopbarTitle, String title, boolean isCanBack, ViewStub vsTopbarBack) {
        //设置标题
        tvTopbarTitle.setText(title);
        if (isCanBack) {
            //可以返回
            //返回 ViewStub
            vsTopbarBack.inflate();
            //所有的返回键LinearLayout id 都是一样的
            LinearLayout ll_back = (LinearLayout) findViewById(R.id.ll_topbar_back);
            ll_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

    }

    /**
     * 设置主布局文件
     */
    protected abstract void setContentView(@Nullable Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 初始化布局
     */
    protected abstract void initView();




    /**
     * Okhttp 失败回调
     *
     * @param call
     * @param e
     * @param id   方法id
     */
   // protected abstract void failStringBack(Call call, Exception e, int id);

    /**
     * Okhttp 成功回调
     *
     * @param response
     * @param id       方法id
     */
  //  protected abstract void successStringBack(String response, int id);

    /**
     * @param text
     * @param <T>
     */
    protected <T> void showToast(T text) {
        if (null != text) {
            Toast.makeText(this, text + "", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG_IF_ELSE, "输入值不能为空！");
        }
    }
    /**
     * @param text
     * @param <T>
     */
    protected <T> void showToastLong(T text) {
        if (null != text) {
            Toast.makeText(this, text + "", Toast.LENGTH_LONG).show();
        } else {
            Log.i( TAG_IF_ELSE, "输入值不能为空！");
        }
    }

    /**
     * Okhttp String 类型 返回回调接口
     */
    /*public class MyBaseStringCallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            failStringBack(call, e, id);
        }

        @Override
        public void onResponse(String response, int id) {
            successStringBack(response, id);
        }
    }*/

    /**
     * 获取屏幕宽度
     *
     * @return dp
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = MeasureUtil.px2dip(this, dm.widthPixels);
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return dp
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = MeasureUtil.px2dip(this, dm.heightPixels);
        return height;
    }

    /**
     * 跳转
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 带值跳转
     * @param cls
     * @param bundle
     */
    protected void startActivityWithData(Class<?> cls,Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras( bundle);
        startActivity(intent);
    }

    /**
     * 跳转，有返回值
     *
     * @param cls
     * @param requestCode
     */
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转，有返回值,并传值
     * @param cls
     * @param requestCode
     * @param bundle
     */
    protected void startActivityForResultWithData(Class<?> cls, int requestCode,Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras( bundle);
        startActivityForResult(intent, requestCode);
    }




}
