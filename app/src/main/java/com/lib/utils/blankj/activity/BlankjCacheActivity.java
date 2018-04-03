package com.lib.utils.blankj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlankjCacheActivity extends BaseActivity  {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tv_blankj_cache_content)
    TextView tvContent;
    @BindView(R.id.btn_blankj_cache_get_size)
    Button btnGetSize;
    @BindView(R.id.btn_blankj_cache_get_count)
    Button btnGetCount;
    @BindView(R.id.edt_blankj_cache_write_key)
    EditText edtWriteKey;
    @BindView(R.id.edt_blankj_cache_write_value)
    EditText edtWriteValue;
    @BindView(R.id.btn_blankj_cache_write)
    Button btnWrite;
    @BindView(R.id.edt_blankj_cache_read)
    EditText edtRead;
    @BindView(R.id.btn_blankj_cache_read)
    Button btnRead;
    @BindView(R.id.edt_blankj_cache_remove)
    EditText edtRemove;
    @BindView(R.id.btn_blankj_cache_remove)
    Button btnRemove;
    @BindView(R.id.btn_blankj_cache_clear)
    Button btnClear;

    private String strContent = "";
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_blankj_cache);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_blankj_cache_get_size, R.id.btn_blankj_cache_get_count, R.id.btn_blankj_cache_write, R.id.btn_blankj_cache_read, R.id.btn_blankj_cache_remove, R.id.btn_blankj_cache_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_blankj_cache_get_size:
                //读取大小

                break;
            case R.id.btn_blankj_cache_get_count:
                break;
            case R.id.btn_blankj_cache_write:
                break;
            case R.id.btn_blankj_cache_read:
                break;
            case R.id.btn_blankj_cache_remove:
                break;
            case R.id.btn_blankj_cache_clear:
                break;
        }
    }

}
