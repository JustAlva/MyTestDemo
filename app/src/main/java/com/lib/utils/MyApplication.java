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

public class MyApplication extends Application {
    private boolean boolShowLogger = false;


    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
        initBlankjUtils();
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
