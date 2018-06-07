package com.lib.utils.viewpagertest.bean;

/**
 * Created by keding.zhou on 2018/5/14.
 */

public class FragmentDataBean  {

    private String title = "";
    private String content = "";

    public FragmentDataBean(String title, String content) {
        this.title = title;
        this.content = content;
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
}
