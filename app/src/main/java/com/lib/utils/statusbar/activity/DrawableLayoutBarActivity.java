package com.lib.utils.statusbar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableLayoutBarActivity extends BaseActivity {

    @BindView(R.id.tv_drawable_top_bar_title)
    TextView tvTitle;

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_drawable_layout_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        BarUtils.setStatusBarAlpha(DrawableLayoutBarActivity.this, 0);
    }

    @Override
    protected void initView() {
         int statusHeight = BarUtils.getStatusBarHeight();
        // tvTitle.
    }


}
