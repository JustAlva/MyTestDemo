package com.lib.utils.filedownload.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * describe: 文件下载
 * creator: keding.zhou
 * date: 2018/2/25 9:48
 */
public class FileDownloadActivity extends BaseActivity {


    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_file_download);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
