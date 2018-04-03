package com.lib.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.utils.R;

/**
 * Created by keding.zhou on 2018/3/15.
 */

public class MyTabItemLayout extends LinearLayout {
    private Context mContext;
    private String title;
    private Drawable icon;
    private int titleColor;
    private ImageView imgIcon;
    private TextView tvTitle;
    public MyTabItemLayout(Context context) {
        this(context, null);
    }

    public MyTabItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        if (attrs != null) {
            TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.MyTabItemLayout);
            try {
                title = ta.getString(R.styleable.MyTabItemLayout_titleContent);
                icon = ta.getDrawable(R.styleable.MyTabItemLayout_iconImg);
                titleColor = ta.getColor(R.styleable.MyTabItemLayout_titleColor, context.getResources().getColor(R.color.black));

            } finally {
                ta.recycle();  //注意回收
            }
        }
        LayoutInflater.from(mContext).inflate(R.layout.layout_tabitem, this, true);
          imgIcon = findViewById(R.id.img_tab_item_icon);
         tvTitle = findViewById(R.id.tv_tab_item_title);
        if (null != title) {
            tvTitle.setText(title);
        }
        tvTitle.setTextColor(titleColor);
        if (null != icon) {
            imgIcon.setImageDrawable(icon);
        }
    }

    public void setText(String title){
        if (null!=title) {
            tvTitle.setText(title);
        }
    }

    public void setIcon(Drawable icon) {
        if (null!=icon) {
            imgIcon.setImageDrawable(icon);
        }
    }

}
