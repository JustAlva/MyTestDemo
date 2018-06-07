package com.lib.utils.answer.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;

import butterknife.ButterKnife;

public class AnswerMainActivity extends BaseActivity {

    /**
     * 设置主布局文件
     *
     * @param savedInstanceState
     */
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_answer_main);
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {

    }
}
