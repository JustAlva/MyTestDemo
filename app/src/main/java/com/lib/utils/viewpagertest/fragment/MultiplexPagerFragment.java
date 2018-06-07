package com.lib.utils.viewpagertest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.viewpagertest.bean.FragmentDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe: 复用pager
 * creator: keding.zhou
 * date: 2018/5/14 9:12
 */
public class MultiplexPagerFragment extends BaseFragment {

    @BindView(R.id.tv_fragment_multiplex_title)
    TextView tvTitle;
    @BindView(R.id.tv_fragment_multiplex_content)
    TextView tvContent;

    private FragmentDataBean mCurrentData;
    Unbinder unbinder;

    public void setmCurrentData(FragmentDataBean mCurrentData) {
        this.mCurrentData = mCurrentData;
    }


    @Override
    protected View setContentView(LayoutInflater inflater) {
        View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_multiplex_pager, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        if (mCurrentData != null) {
            String title = mCurrentData.getTitle();
            String content = mCurrentData.getContent();
            tvTitle.setText(title);
            tvContent.setText(content);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
