package com.lib.utils.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.floatingactionbutton.activity.FloatingActionButtonActivity;
import com.lib.utils.tablayout.activity.TabLayoutHeadActivity;
import com.lib.utils.tablayout.activity.TabLayoutMyItemActivity;
import com.lib.utils.tablayout.activity.TablayoutWithRecyclerActivity;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;
import com.lib.utils.viewpagertest.activity.MultiplexViewPagerActivity;
import com.lib.utils.views.MenuLinearLayout;
import com.lib.utils.xinge.activity.MyNewsActivity;
import com.lib.utils.xinge.beans.MessageBean;

import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe: 首页
 * creator: keding.zhou
 * date: 2018/1/24 14:21
 */
public class HomeFragment extends BaseFragment implements OnCheckDoubleClick {

    Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.ml_home_floating_action_btn)
    MenuLinearLayout mlFAB;
    @BindView(R.id.ml_home_views)
    MenuLinearLayout mlViews;
    @BindView(R.id.ml_home_navigation_tabbar)
    MenuLinearLayout mlNavigationTabBar;
    @BindView(R.id.ml_home_tablayout_head)
    MenuLinearLayout mlTabLayoutHead;
    @BindView(R.id.ml_home_tablayout_news)
    MenuLinearLayout mlNews;
    @BindView(R.id.ml_home_tablayout_multiplex_viewpager)
    MenuLinearLayout mlViewPager;
    @BindView(R.id.ml_home_tablayout_with_recyclerview)
    MenuLinearLayout mlTabWithRecyclerView;

    private MessageBean messageBean ;

    @SuppressLint("HandlerLeak")
    android.os.Handler handler =new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mlNews.showPoint();
                    break;
            }
        }
    };

    @Override
    protected View setContentView(LayoutInflater inflater) {
        View convertView = inflater.inflate(R.layout.fragment_main_home, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        //标题
        tvTopbarTitle.setText("首页");

        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        //FloatingActionButton
        mlFAB.setOnClickListener(doubleClickListener);
        //views
        mlViews.setOnClickListener(doubleClickListener);
        //自定义tablayout item
        mlNavigationTabBar.setOnClickListener(doubleClickListener);
        //tab在头部
        mlTabLayoutHead.setOnClickListener(doubleClickListener);
        //我的消息
        mlNews.setOnClickListener(doubleClickListener);
        //multiplex viewpager
        mlViewPager.setOnClickListener(doubleClickListener);
        //tablayout with recyclerview
        mlTabWithRecyclerView.setOnClickListener(doubleClickListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public  void hasNewMsg(final MessageBean msgBean) {
     /* *//*  getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mlNews.showPoint();
                messageBean =msgBean;
            }
        });*//*
        Message msg = new Message();
        msg.what =1;
        messageBean = msgBean;
        handler.sendMessage(msg);*/
        mlNews.showPoint();
        messageBean =msgBean;
    }

    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ml_home_floating_action_btn:
                startActivity(FloatingActionButtonActivity.class);
                break;
            case R.id.ml_home_views:

                break;
            case R.id.ml_home_navigation_tabbar:
                startActivity(TabLayoutMyItemActivity.class);
                break;
            case R.id.ml_home_tablayout_head:
                startActivity(TabLayoutHeadActivity.class);
                break;
            case R.id.ml_home_tablayout_news:
                Intent intent = new Intent(mContext, MyNewsActivity.class);
                intent.putExtra("msg", messageBean);
                mContext.startActivity(intent);
                mlNews.dismissPoint();
                break;
            case R.id.ml_home_tablayout_multiplex_viewpager:
                startActivity(MultiplexViewPagerActivity.class);
                break;
            case R.id.ml_home_tablayout_with_recyclerview:
                startActivity(TablayoutWithRecyclerActivity.class);
                break;

        }
    }
}
