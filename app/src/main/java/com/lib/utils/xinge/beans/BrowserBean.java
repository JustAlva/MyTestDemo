package com.lib.utils.xinge.beans;

/**
 * Created by keding.zhou on 2018/3/23.
 */

public class BrowserBean {

    //url：打开的url，confirm是否需要用户确认
    private String url = "";
    private String confirm = "";

    public BrowserBean(String url, String confirm) {
        this.url = url;
        this.confirm = confirm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
