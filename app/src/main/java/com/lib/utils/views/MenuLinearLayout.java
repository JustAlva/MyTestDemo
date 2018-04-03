package com.lib.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.utils.R;

/**
 * describe: 线性菜单栏
 * creator: keding.zhou
 * date: 2018/1/25 15:45
 */
public class MenuLinearLayout extends LinearLayout {
    private Context mContext;
    private String titleContent;
    private Drawable iconLeft;
    private Drawable iconRight;
    private ImageView imgPoint;


    public MenuLinearLayout(Context context) {
        this(context, null);
    }

    public MenuLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        if (attrs != null) {
            TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.MenuLinearLayout);
            try {
                titleContent = ta.getString(R.styleable.MenuLinearLayout_textContent);
                iconLeft = ta.getDrawable(R.styleable.MenuLinearLayout_iconLeft);
                iconRight = ta.getDrawable(R.styleable.MenuLinearLayout_iconRight);
            } finally {
                ta.recycle();  //注意回收
            }
        }
        LayoutInflater.from(mContext).inflate(R.layout.linearlayout_menu, this, true);
        ImageView imgLeft =  findViewById(R.id.img_linear_menu_icon_left);
        TextView tvContent =  findViewById(R.id.tv_linear_menu_content);
        ImageView imgRight =  findViewById(R.id.img_linear_menu_icon_right);
        imgPoint = findViewById(R.id.img_linear_menu_icon_point);
        if (iconLeft!=null) {
            imgLeft.setImageDrawable(iconLeft);
        }
        tvContent.setText(titleContent);
        if (iconRight!=null) {
            imgRight.setImageDrawable(iconRight);
        }
    }

    public void showPoint(){
        imgPoint = findViewById(R.id.img_linear_menu_icon_point);
        imgPoint.setVisibility(View.VISIBLE);
    }

    public void dismissPoint(){
        imgPoint.setVisibility(View.GONE);
    }

}
