package com.lib.utils.blankj.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlankjStatusBarActivity extends BaseActivity {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tv_blankj_status_bar_content)
    TextView tvContent;
    @BindView(R.id.btn_blankj_status_bar_change_bg_color)
    Button btnChangeBgColor;
    @BindView(R.id.btn_blankj_status_bar_change_text_color)
    Button btnChangeTextColor;
    @BindView(R.id.btn_blankj_status_bar_change_visible)
    Button btnChangeVisible;
    @BindView(R.id.btn_blankj_status_bar_light_mode)
    Button btnLightMode;
    @BindView(R.id.edt_blankj_status_bar_alpha)
    EditText edtAlpha;
    @BindView(R.id.btn_blankj_status_bar_alpha)
    Button btnAlpha;
    @BindView(R.id.ll_blankj_main)
    LinearLayout llMain;

    private String showStr = "";



    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_blankj_status_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTopBarTitle(tvTopbarTitle, "状态栏属性相关", true, vsTopbarBack);


        showStr += "StatusBarHeight:" + BarUtils.getStatusBarHeight() + "\n" +
                "ActionBarHeight:" + BarUtils.getActionBarHeight() + "\n" +
                "";
        tvContent.setText(showStr);
    }

    @OnClick({R.id.btn_blankj_status_bar_change_visible, R.id.btn_blankj_status_bar_light_mode,  R.id.btn_blankj_status_bar_alpha,R.id.btn_blankj_status_bar_change_bg_color, R.id.btn_blankj_status_bar_change_text_color})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_blankj_status_bar_change_bg_color:
                //改变状态栏背景色
                // StatusBarUtils.setStatusBarLightMode(BlankjStatusBarActivity.this , Color.RED);
                //在顶部添加一个状态栏高度的view，需要给主布局加上顶部margin
                BarUtils.setStatusBarColor(BlankjStatusBarActivity.this, Color.RED,0 );
                BarUtils.addMarginTopEqualStatusBarHeight(llMain);// 其实这个只需要调用一次即可
                break;
            case R.id.btn_blankj_status_bar_change_text_color:
                //改变状态栏字体色
                break;
            case R.id.btn_blankj_status_bar_change_visible:
                //改变状态栏是否隐藏
                boolean isVisible = BarUtils.isStatusBarVisible(BlankjStatusBarActivity.this);
                if (isVisible) {
                    BarUtils.setStatusBarVisibility(BlankjStatusBarActivity.this,false);
                }else {
                    BarUtils.setStatusBarVisibility(BlankjStatusBarActivity.this,true);
                }
                break;
            case R.id.btn_blankj_status_bar_light_mode:
                //设为 light mode
                BarUtils.setStatusBarLightMode(BlankjStatusBarActivity.this,true);
                break;
            case R.id.btn_blankj_status_bar_alpha:
                //设置透明度
                String alpha = edtAlpha.getText().toString();
                BarUtils.setStatusBarAlpha(BlankjStatusBarActivity.this,Integer.valueOf(alpha));
                break;
        }
    }


}
