package com.lib.utils.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseFragment;
import com.lib.utils.materialdialog.activity.MaterialDialogActivity;
import com.lib.utils.statusbar.activity.DrawableLayoutBarActivity;
import com.lib.utils.test.activity.TestActivity;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;
import com.lib.utils.views.MenuLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * describe: 消息
 * creator: keding.zhou
 * date: 2018/1/24 14:21
 */
public class NewsFragment extends BaseFragment implements OnCheckDoubleClick {

    Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.ml_news_material_dialog)
    MenuLinearLayout mlMaterialDialog;
    @BindView(R.id.ml_news_material_drawable_topbar)
    MenuLinearLayout mlDrawableLayout;
    @BindView(R.id.ml_news_material_test)
    MenuLinearLayout mlTest;

    @Override
    protected View setContentView(LayoutInflater inflater  ) {
        View convertView = inflater.inflate(R.layout.fragment_main_news, null);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View convertView) {
        //标题
        tvTopbarTitle.setText("消息");

        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        //MaterialDialog
        mlMaterialDialog.setOnClickListener(doubleClickListener);
        mlDrawableLayout.setOnClickListener(doubleClickListener);
        mlTest.setOnClickListener(doubleClickListener);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ml_news_material_dialog:
                //dialog
                startActivity(MaterialDialogActivity.class);
                break;
            case R.id.ml_news_material_drawable_topbar:
                //沉浸式状态栏
                startActivity(DrawableLayoutBarActivity.class);
                break;
            case R.id.ml_news_material_test:
                //
                startActivity(TestActivity.class);
                break;
        }
    }
}
