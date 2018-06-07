package com.lib.utils.viewpagertest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.viewpagertest.adapter.MyMultiplexViewpagerAdapter;
import com.lib.utils.viewpagertest.bean.FragmentDataBean;
import com.lib.utils.viewpagertest.fragment.MultiplexPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe: 复用viewpager view 复用
 * creator: keding.zhou
 * date: 2018/5/14 8:36
 */
public class MultiplexViewPagerActivity extends BaseActivity {

    @BindView(R.id.tv_const_top_bar_title)
    TextView tvConstTopBarTitle;
    @BindView(R.id.vs_const_top_bar_back)
    ViewStub vsConstTopBarBack;
    @BindView(R.id.vs_const_top_bar_sure)
    ViewStub vsConstTopBarSure;
    @BindView(R.id.vp_multiplex_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tl_fragment_multiplex_tab_layout_head)
    TabLayout mTab;

    private MyMultiplexViewpagerAdapter mAdapter;
    private List<FragmentDataBean> mDataList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    /**
     * 设置主布局文件
     *
     * @param savedInstanceState
     */
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_multiplex_view_pager);
        ButterKnife.bind(this);
        //标题
        setTopBarTitle(tvConstTopBarTitle, "复用ViewPager", true, vsConstTopBarBack);

        View vSure = vsConstTopBarSure.inflate();
        LinearLayout llTopSure = vSure.findViewById(R.id.ll_topbar_sure);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            FragmentDataBean data = new FragmentDataBean("第" + i + "页", "content:" + i);
            mDataList.add(data);
        }
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        mAdapter =new MyMultiplexViewpagerAdapter(MultiplexViewPagerActivity.this,mDataList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTab.setupWithViewPager(mViewPager);

        for (int i = 0; i < 3; i++) {
            mTab.getTabAt(i).setText("第" + i + "个");
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

}
