package com.lib.utils.tablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by keding.zhou on 2018/3/16.
 */

public class TabFragment extends BaseFragment {
    @BindView(R.id.tv_fragment_tab_content)
    TextView tvContent;
    Unbinder unbinder;


    @Override
    protected View setContentView(LayoutInflater inflater) {
        View convertView = inflater.inflate(R.layout.fragment_tab_view, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        tvContent.setText("Fragment");
    }

    public void setText(String text){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
