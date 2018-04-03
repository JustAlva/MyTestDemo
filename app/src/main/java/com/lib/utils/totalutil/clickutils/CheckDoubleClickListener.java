package com.lib.utils.totalutil.clickutils;

import android.util.Log;
import android.view.View;

/**
 * describe: view 的多次点击监听
 * creator: keding.zhou
 * date: 2018/1/5 9:10
 */
public class CheckDoubleClickListener implements View.OnClickListener {
    public final static int MIN_CLICK_TIME = 600;

    private long lastClickTime = 0;
    private OnCheckDoubleClick onCheckDoubleClick;

    public CheckDoubleClickListener(OnCheckDoubleClick onCheckDoubleClick ){
        this.onCheckDoubleClick=onCheckDoubleClick;
    }


    @Override
    public void onClick(View v) {
        //api 24
       /* long currentTime = Calendar.getInstance().getTimeInMillis();
        Log.i("zkd:currentTime-->", currentTime+"" );*/

        long currentTime = System.currentTimeMillis();
        if(currentTime-lastClickTime>MIN_CLICK_TIME){
            lastClickTime = currentTime;
            onCheckDoubleClick.onCheckDoubleClick(v);
        }
    }
}
