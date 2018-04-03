package com.lib.utils.totalutil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * describe: 权限相关工具类
 * creator: keding.zhou
 * date: 2018/3/14 9:17
 */
public class PermissionUtils {

    public static boolean hasPermission(Activity activity,String permission,int requestCode){
        /**
         * ContextCompat.checkSelfPermission，主要用于检测某个权限是否已经被授予，
         * 方法返回值为 PackageManager.PERMISSION_DENIED 或者 PackageManager.PERMISSION_GRANTED。
         * 当返回 DENIED 就需要进行申请授权了。
         */
        if (ContextCompat.checkSelfPermission(activity,
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission},requestCode);
            return false;
        }else{
            return true;
        }
    }


    public static boolean hasLocationPermission(Activity activity ,int requestCode){
        /**
         * ContextCompat.checkSelfPermission，主要用于检测某个权限是否已经被授予，
         * 方法返回值为 PackageManager.PERMISSION_DENIED 或者 PackageManager.PERMISSION_GRANTED。
         * 当返回 DENIED 就需要进行申请授权了。
         */
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission_group.LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},requestCode);
            return false;
        }else{
            return true;
        }
    }
}
