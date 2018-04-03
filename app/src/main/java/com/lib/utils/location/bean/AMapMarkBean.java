package com.lib.utils.location.bean;

/**
 * describe: 高德地图标记点bean
 * creator: keding.zhou
 * date: 2018/3/14 10:05
 */
public class AMapMarkBean {
    private  double latitude=0.00d;//纬度
    private double longtitude=0.00d;//经度
    private String title="";//标题
    private String snippet = "";//显示内容
    private boolean isShowInfo = false;//是否显示信息窗口

    public AMapMarkBean(double latitude, double longtitude, String title, String snippet,boolean isShowInfo) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.title = title;
        this.snippet = snippet;
        this.isShowInfo = isShowInfo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public boolean isShowInfo() {
        return isShowInfo;
    }

    public void setShowInfo(boolean showInfo) {
        isShowInfo = showInfo;
    }
}
