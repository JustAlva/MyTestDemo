package com.lib.utils.materialdialog.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.ui.layout.dialog.material.DialogAction;
import com.lib.ui.layout.dialog.material.GravityEnum;
import com.lib.ui.layout.dialog.material.MaterialDialog;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.lib.utils.totalutil.LoadingDialog;
import com.lib.utils.totalutil.clickutils.CheckDoubleClickListener;
import com.lib.utils.totalutil.clickutils.OnCheckDoubleClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialDialogActivity extends BaseActivity implements OnCheckDoubleClick {

    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;
    @BindView(R.id.cv_news_basic_dialog)
    CardView cvBasicDialog;
    @BindView(R.id.cv_news_loading_dialog)
    CardView cvLoadingDialog;
    @BindView(R.id.cv_news_process_dialog)
    CardView cvProcessDialog;
    @BindView(R.id.cv_news_avi_dialog)
    CardView cvAviDialog;

    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_material_dialog);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //标题
        setTopBarTitle(tvTopbarTitle, "MaterialDialog", true, vsTopbarBack);

        CheckDoubleClickListener doubleClickListener = new CheckDoubleClickListener(this);
        cvBasicDialog.setOnClickListener(doubleClickListener);
        cvLoadingDialog.setOnClickListener(doubleClickListener);
        cvProcessDialog.setOnClickListener(doubleClickListener);
        cvAviDialog.setOnClickListener(doubleClickListener);
    }

    Thread thread;

    private void startThread(Runnable run) {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(run);
        thread.start();
    }

    @Override
    public void onCheckDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.cv_news_basic_dialog:
                //basic dialog
                new MaterialDialog.Builder(this)
                        .title("Basic Dialog")
                        .content("这是一个Dialog!")
                        .positiveText("OK")
                        .negativeText("CANCLE")
                        .canceledOnTouchOutside(false)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                showToast("OK");
                                dialog.dismiss();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                showToast("CANCLE");
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.cv_news_loading_dialog:
                //loading dialog
                new MaterialDialog.Builder(this)
                        .content("正在加载...")
                        .progress(true, 0)
                        .show();
                break;
            case R.id.cv_news_process_dialog:
                //带进度条
                new MaterialDialog.Builder(this)
                        .content("正在下载...")
                        .contentGravity(GravityEnum.CENTER)
                        .progress(false, 100, true)
                       // .canceledOnTouchOutside(false)
                        .cancelable(false)
                        .cancelListener(
                                new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        if (thread != null) {
                                            thread.interrupt();
                                        }
                                    }
                                }
                        )
                        .showListener(
                                new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialogI) {
                                        final MaterialDialog dialog = (MaterialDialog) dialogI;
                                        startThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            // Loop until the dialog's progress value reaches the max (150)
                                                            while (dialog.getCurrentProgress() != dialog.getMaxProgress()) {
                                                                // If the progress dialog is cancelled (the user closes it before it's done), break the loop
                                                                if (dialog.isCancelled()) break;
                                                                // Wait 50 milliseconds to simulate doing work that requires progress
                                                                try {
                                                                    Thread.sleep(50);
                                                                } catch (InterruptedException e) {
                                                                    break;
                                                                }
                                                                // Increment the dialog's progress by 1 after sleeping for 50ms
                                                                dialog.incrementProgress(1);
                                                            }
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    thread = null;
                                                                    dialog.setContent("下载完成");
                                                                    dialog.setCancelable(true);

                                                                }
                                                            });
                                                        }
                                                    }
                                        );
                                    }
                                })
                        .show();
                break;

            case R.id.cv_news_avi_dialog:
                //AviLoading
                LoadingDialog loadingDialog = new LoadingDialog();
                loadingDialog.createLoadingDialog(
                        this, "加载数据...", true);
                break;
        }
    }

}
