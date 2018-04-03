package com.lib.utils.xinge.beans;

/**
 * Created by keding.zhou on 2018/3/23.
 */

public class ActionBean {
    private String action_type = "";//动作类型，1打开activity或app本身，2打开浏览器，3打开Intent
    private String activity = "";
    private String aty_attr = ""; // activity属性，只针对action_type=1的情况
    private String intent = "";
    private BrowserBean browser  ;

    public ActionBean(String action_type, String activity, String intent, BrowserBean browser) {
        this.action_type = action_type;
        this.activity = activity;
        this.intent = intent;
        this.browser = browser;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public BrowserBean getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserBean browser) {
        this.browser = browser;
    }
}
