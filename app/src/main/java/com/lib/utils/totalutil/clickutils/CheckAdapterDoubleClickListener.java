package com.lib.utils.totalutil.clickutils;

import android.util.Log;
import android.view.View;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * describe: Adapter itemview 的多次点击监听
 * creator: keding.zhou
 * date: 2018/1/5 9:10
 */
public class CheckAdapterDoubleClickListener implements RecyclerArrayAdapter.OnItemClickListener {
    public final static int MIN_CLICK_TIME = 600;

    private long lastClickTime = 0;
    private OnItemCheckDoubleClick onCheckDoubleClick;

    public CheckAdapterDoubleClickListener(OnItemCheckDoubleClick onCheckDoubleClick ){
        this.onCheckDoubleClick=onCheckDoubleClick;
    }

    @Override
    public void onItemClick(int position) {
        //api 24
       /* long currentTime = Calendar.getInstance().getTimeInMillis();
        Log.i("zkd:currentTime-->", currentTime+"" );*/

        long currentTime = System.currentTimeMillis();
        if(currentTime-lastClickTime>MIN_CLICK_TIME){
            lastClickTime = currentTime;
            onCheckDoubleClick.onItemClick(position);
        }
    }
}
