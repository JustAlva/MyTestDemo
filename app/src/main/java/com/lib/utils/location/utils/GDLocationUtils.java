package com.lib.utils.location.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lib.utils.R;
import com.lib.utils.location.bean.AMapMarkBean;

import java.util.List;

/**
 * describe: 高德地图工具类
 * creator: keding.zhou
 * date: 2018/3/14 10:00
 */
public class GDLocationUtils {


    /**
     * 添加标记点
     * @param aMap
     * @param markList 标记点 list
     */
    public static void addMark(Context context, AMap aMap, List<AMapMarkBean> markList) {
        if (aMap!=null) {
            if (markList!=null) {
                for (int i = 0; i < markList.size(); i++) {
                    addMark(context,aMap,markList.get(i));
                }
            }
        }
    }

    /**
     * 添加标记点
     * @param aMap
     * @param markBean
     */
    public static void addMark(Context context,AMap aMap,AMapMarkBean markBean) {
        LatLng latLng = new LatLng(markBean.getLatitude(), markBean.getLongtitude());
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(markBean.getTitle());
        markerOption.snippet(markBean.getSnippet());
        markerOption.draggable(false);//设置Marker可拖动
        //添加图标
        // markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_tek_location)));
        Marker marker = aMap.addMarker(markerOption);
        if (markBean.isShowInfo()) {
            marker.showInfoWindow();
        }

        aMap.addCircle(new CircleOptions().
                center(latLng).
                radius(200).
                fillColor(Color.argb(127, 1, 1, 1)).//填充
                strokeColor(Color.argb(0, 1, 1, 1)). //边
                strokeWidth(15));
    }
}
