package com.lib.utils.xinge.beans;

import java.io.Serializable;

/**
 * describe: 推送的消息内容
 * creator: keding.zhou
 * date: 2018/3/23 9:02
 */
public class MessageBean implements Serializable{

    private String title = "";//notification 显示的标题
    private String content = "";//notification 显示的内容
    private String accept_time = "";//表示消息将在哪些时间段允许推送给用户，选填
    private String n_id = "";// //通知id，选填。若大于0，则会覆盖先前弹出的相同id通知；若为0，展示本条通知且不影响其他通知；若为-1，将清除先前弹出的所有通知，仅展示本条通知。默认为0
    private String builder_id = "";// 本地通知样式
    private String ring = "";//是否响铃，0否，1是，下同。选填，默认1
    private String ring_raw = "";//指定应用内的声音（ring.mp3），选填
    private String vibrate = "";// 是否振动，选填，默认1
    private String lights = "";//是否呼吸灯，0否，1是，选填，默认1
    private String clearable = "";//通知栏是否可清除，选填，默认1
    private String icon_type = "";//默认0，通知栏图标是应用内图标还是上传图标,0是应用内图标，1是上传图标,选填
    private String icon_res = "";//应用内图标文件名（xg.png）或者下载图标的url地址，选填
    private String style_id = "";//Web端设置是否覆盖编号的通知样式，默认1，0否，1是,选填
    private String small_icon = "";//"xg"指定状态栏的小图片(xg.png),选填
    private ActionBean action ;//动作，选填。默认为打开app
    private String custom_content = "";//自定义内容

    public  MessageBean (){

    };
    public MessageBean(String title, String content, String accept_time, String n_id, String builder_id, String ring, String ring_raw, String vibrate, String lights, String clearable, String icon_type, String icon_res, String style_id, String small_icon, ActionBean action, String custom_content) {
        this.title = title;
        this.content = content;
        this.accept_time = accept_time;
        this.n_id = n_id;
        this.builder_id = builder_id;
        this.ring = ring;
        this.ring_raw = ring_raw;
        this.vibrate = vibrate;
        this.lights = lights;
        this.clearable = clearable;
        this.icon_type = icon_type;
        this.icon_res = icon_res;
        this.style_id = style_id;
        this.small_icon = small_icon;
        this.action = action;
        this.custom_content = custom_content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getN_id() {
        return n_id;
    }

    public void setN_id(String n_id) {
        this.n_id = n_id;
    }

    public String getBuilder_id() {
        return builder_id;
    }

    public void setBuilder_id(String builder_id) {
        this.builder_id = builder_id;
    }

    public String getRing() {
        return ring;
    }

    public void setRing(String ring) {
        this.ring = ring;
    }

    public String getRing_raw() {
        return ring_raw;
    }

    public void setRing_raw(String ring_raw) {
        this.ring_raw = ring_raw;
    }

    public String getVibrate() {
        return vibrate;
    }

    public void setVibrate(String vibrate) {
        this.vibrate = vibrate;
    }

    public String getLights() {
        return lights;
    }

    public void setLights(String lights) {
        this.lights = lights;
    }

    public String getClearable() {
        return clearable;
    }

    public void setClearable(String clearable) {
        this.clearable = clearable;
    }

    public String getIcon_type() {
        return icon_type;
    }

    public void setIcon_type(String icon_type) {
        this.icon_type = icon_type;
    }

    public String getIcon_res() {
        return icon_res;
    }

    public void setIcon_res(String icon_res) {
        this.icon_res = icon_res;
    }

    public String getStyle_id() {
        return style_id;
    }

    public void setStyle_id(String style_id) {
        this.style_id = style_id;
    }

    public String getSmall_icon() {
        return small_icon;
    }

    public void setSmall_icon(String small_icon) {
        this.small_icon = small_icon;
    }

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public String getCustom_content() {
        return custom_content;
    }

    public void setCustom_content(String custom_content) {
        this.custom_content = custom_content;
    }
}

