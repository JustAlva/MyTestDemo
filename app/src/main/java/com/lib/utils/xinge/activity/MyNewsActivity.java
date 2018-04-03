package com.lib.utils.xinge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewStub;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.xinge.beans.MessageBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNewsActivity extends BaseActivity {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tv_my_news_content)
    TextView tvNewsContent;

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_news);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setTopBarTitle(tvTopbarTitle, "我的消息", true, vsTopbarBack);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (null!=intent) {
            Bundle bundle = intent.getExtras();
            if (null!=bundle) {
                MessageBean msgBean = (MessageBean) bundle.get("msg");
                tvNewsContent.setText(msgBean.getTitle() + "\n"+msgBean.getContent() + "\n"   + msgBean.getCustom_content());
            }
        }
    }



}
