package com.lib.utils.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.blankj.activity.BlankjCacheActivity;
import com.lib.utils.blankj.activity.BlankjPermissionActivity;
import com.lib.utils.blankj.activity.BlankjStatusBarActivity;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;
import com.lib.utils.views.MenuLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe: 个人中心
 * creator: keding.zhou
 * date: 2018/1/24 14:21
 */
public class PersonalFragment extends BaseFragment implements OnCheckDoubleClick {

    Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.ml_personal_status_bar)
    MenuLinearLayout mlStatusBar;
    @BindView(R.id.ml_personal_cache)
    MenuLinearLayout mlCache;
    @BindView(R.id.ml_personal_permission)
    MenuLinearLayout mlPermission;
    @Override
    protected View setContentView(LayoutInflater inflater  ) {
        View convertView = inflater.inflate(R.layout.fragment_main_personal, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        //标题
        tvTopbarTitle.setText("Blankj");
        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        mlStatusBar.setOnClickListener(doubleClickListener);
        mlCache.setOnClickListener(doubleClickListener);
        mlPermission.setOnClickListener(doubleClickListener);
    }

    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ml_personal_status_bar:
                //状态栏
                startActivity(BlankjStatusBarActivity.class);
                break;
            case R.id.ml_personal_cache:
                //缓存相关
                startActivity(BlankjCacheActivity.class);
                break;
            case R.id.ml_personal_permission:
                //权限相关
                startActivity(BlankjPermissionActivity.class);
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
