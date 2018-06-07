package com.lib.utils.tablayout.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.tablayout.bean.RecyclerBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TablayoutWithRecyclerActivity extends BaseActivity {

    @BindView(R.id.tv_const_top_bar_title)
    TextView tvConstTopBarTitle;
    @BindView(R.id.vs_const_top_bar_back)
    ViewStub vsConstTopBarBack;
    @BindView(R.id.tb_tablayout_recyclerview)
    TabLayout mTablayout;
    @BindView(R.id.rv_tablayout_recyclerview)
    RecyclerView mRecyclerView;

    private List<RecyclerBean> mDatalist = new ArrayList<>();
    private boolean isClick = false;

    /**
     * 设置主布局文件
     */
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_tablayout_with_recycler);
        ButterKnife.bind(this);
        //标题
        setTopBarTitle(tvConstTopBarTitle, "TabLayout联动RecyclerView", true, vsConstTopBarBack);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        for (int i = 0; i < 15; i++) {
            RecyclerBean bean = new RecyclerBean("content" + i);
            mDatalist.add(bean);
        }
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        MyAdapter mAdapter = new MyAdapter(TablayoutWithRecyclerActivity.this, mDatalist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < mDatalist.size(); i++) {
            TabLayout.Tab tab = mTablayout.newTab();
            tab.setText(mDatalist.get(i).getContent());
            mTablayout.addTab(tab);
        }
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("zkd", "[TablayoutWithRecyclerActivity][onScrollStateChanged]==> State : " + newState);
                if (newState == 0) {
                    isClick = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isClick) {
                    LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstPosition = l.findFirstVisibleItemPosition();
                    int lastPosition = l.findLastVisibleItemPosition();
                    int allItems = l.getItemCount();
                    mTablayout.setScrollPosition(firstPosition, 0f, true);
                    Log.i("zkd", "[TablayoutWithRecyclerActivity][onScrollStateChanged]==> firstPosition : " + firstPosition + ",\nlastPosition:" + lastPosition + ",\nall:" + allItems);
                }
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("zkd", "[TablayoutWithRecyclerActivity][onTabSelected]==>  : " + "=====================点击================");
                isClick = true;
                int position = tab.getPosition();
                LinearLayoutManager l = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int firstPosition = l.findFirstVisibleItemPosition();
                int lastPosition = l.findLastVisibleItemPosition();
                if (position > lastPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else if (position < firstPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else {
                    int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    mRecyclerView.smoothScrollBy(0, top);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * implementation 'com.jude:easyrecyclerview:4.4.2'
     */
    private class MyAdapter extends RecyclerArrayAdapter<RecyclerBean> {
        /**
         * Constructor
         */
        MyAdapter(Context context, List<RecyclerBean> objects) {
            super(context, objects);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(parent, R.layout.item_recycler_textview);
        }

        private class MyViewHolder extends BaseViewHolder<RecyclerBean> {
            private TextView tvContent;
            private View vEmpty;

            MyViewHolder(ViewGroup parent, int res) {
                super(parent, res);
                tvContent = $(R.id.tv_item_recycler_content);
                vEmpty = $(R.id.v_item_recycler_empty);
            }

            @Override
            public void setData(RecyclerBean data) {
                super.setData(data);
                if (data != null) {
                    String content = data.getContent();
                    tvContent.setText(content);
                    int position = getDataPosition();
                    if (position == (getCount() - 1)) {
                        vEmpty.setVisibility(View.VISIBLE);
                    } else {
                        vEmpty.setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}
