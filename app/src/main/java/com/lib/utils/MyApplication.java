package com.lib.utils;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.smtt.sdk.QbSdk;

import static com.amap.api.mapcore.util.dj.b;

public class MyApplication extends Application {
    private boolean boolShowLogger = false;


    @Override
    public void onCreate() {
        super.onCreate();

        initTBS();
        initLogger();
        initBlankjUtils();
    }

    /**
     * 腾讯x5内核
     */
    private void initTBS() {

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.i("zkd", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                Log.i("zkd","加载内核是否成功:"+b);
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }
    /**
     * 初识化 BlankjUtils
     */
    private void initBlankjUtils() {
        Utils.init(this);
    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
       // Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                /**
                 * true:显示log内容
                 * false:不显示
                 */
                 return BuildConfig.DEBUG;
            }
        });

    }
}
