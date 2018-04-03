package com.lib.utils.coordinatorlayout.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
/**
 * describe: CoordinatorLayout
 * creator: keding.zhou
 * date: 2018/1/26 10:54
 */
public class CoordinatorLayoutActivity extends BaseActivity {
  
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_coordinator_layout);
       // setTopBarTitle("CoordinatorLayout",true,);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
