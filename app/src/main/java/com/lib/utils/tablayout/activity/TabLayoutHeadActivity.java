package com.lib.utils.tablayout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewStub;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.tablayout.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe: tablayout 在头部
 * creator: keding.zhou
 * date: 2018/3/16 10:54
 */
public class TabLayoutHeadActivity extends BaseActivity {


    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.tl_tab_layout_head)
    TabLayout mTab;
    @BindView(R.id.vp_tab_layout_head)
    ViewPager mViewPager;

    private  MyTabViewPagerAdapter pagerAdapter ;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_tab_layout_head);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 5; i++) {
            TabFragment tabFragment = new TabFragment();
            tabFragment.setText("第" + i + "个Fragment");
            mFragmentList.add(tabFragment);
        }
        pagerAdapter = new MyTabViewPagerAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initView() {
        setTopBarTitle(tvTopbarTitle, "TabLayout", true, vsTopbarBack);

        mViewPager.setAdapter(pagerAdapter);
        mTab.setupWithViewPager(mViewPager);

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            mTab.getTabAt(i).setText("第" + i + "个");
        }
    }


    public class MyTabViewPagerAdapter extends FragmentPagerAdapter{

        public MyTabViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
