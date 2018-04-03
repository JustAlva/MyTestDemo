package com.lib.utils.floatingactionbutton.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingActionButtonActivity extends BaseActivity {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.erv_fab_content)
    EasyRecyclerView ervContent;
    @BindView(R.id.fab_fab_add)
    FloatingActionButton fabAdd;

    private MyEasyRecyclerViewAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_floating_action_button);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("content:" + (i + 1));
        }
    }

    @Override
    protected void initView() {
        setTopBarTitle(tvTopbarTitle, "FloatingActionButton", true, vsTopbarBack);

        ervContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyEasyRecyclerViewAdapter(this, mList);
        ervContent.setAdapter(mAdapter);
        ervContent.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<String> list = new ArrayList<>();
                for (int i = 1; i < 4; i++) {
                    list.add(0, "refresh" + i);
                }
                mAdapter.addAll(list);
            }
        });
    }

    @OnClick({R.id.fab_fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_fab_add:
                mAdapter.add("add");
                break;
        }

    }


    private class MyEasyRecyclerViewAdapter extends RecyclerArrayAdapter<String> {

        public MyEasyRecyclerViewAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new TextViewHolder(parent, R.layout.layout_text);
        }
    }

    private class TextViewHolder extends BaseViewHolder<String> {
        TextView tv;

        public TextViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            this.tv = $(R.id.tv_item_content);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            if (!TextUtils.isEmpty(data)) {
                tv.setText(data);
            }

        }
    }

}
