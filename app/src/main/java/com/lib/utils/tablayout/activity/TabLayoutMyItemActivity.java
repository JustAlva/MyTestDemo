package com.lib.utils.tablayout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.fragment.FindFragment;
import com.lib.utils.fragment.HomeFragment;
import com.lib.utils.fragment.NewsFragment;
import com.lib.utils.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutMyItemActivity extends BaseActivity {

    @BindView(R.id.vp_navigation_tab_bar)
    ViewPager mViewPager;
    @BindView(R.id.tl_navigation_tab)
    TabLayout mTabBar;

    private List<Fragment> mListPages = new ArrayList<>();
    private  MyPagerAdapter mPagerAdapter;
    private HomeFragment mHomeFragment = new HomeFragment();
    private FindFragment mFindFragment = new FindFragment();
    private NewsFragment mNewsFragment = new NewsFragment();
    private PersonalFragment mPersonalFragment = new PersonalFragment();
    private String[] mTitleList = {"one","two","three","four"};//标题list
    private int[] mIconList = {R.drawable.tab_icon_follow,R.drawable.tab_icon_find,R.drawable.tab_icon_news,R.drawable.tab_icon_personal};//图标list

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_navigation_tab_bar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        mListPages.add(mHomeFragment);
        mListPages.add(mFindFragment);
        mListPages.add(mNewsFragment);
        mListPages.add(mPersonalFragment);

    }

    @Override
    protected void initView() {

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabBar.setupWithViewPager(mViewPager);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabBar.getTabAt(i);
            tab.setCustomView(R.layout.layout_tabitem);
            TextView tvTitle = tab.getCustomView().findViewById(R.id.tv_tab_item_title);
            ImageView imgIcon = tab.getCustomView().findViewById(R.id.img_tab_item_icon);
            tvTitle.setText(mTitleList[i]);
            imgIcon.setImageResource(mIconList[i]);
        }
        mTabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tab_item_title).setSelected(true);
              //  tab.getCustomView().findViewById(R.id.img_tab_item_icon).setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tab_item_title).setSelected(false);
               // tab.getCustomView().findViewById(R.id.img_tab_item_icon).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mListPages.get(position);
        }

        @Override
        public int getCount() {
            return mListPages.size();
        }
    }
}
