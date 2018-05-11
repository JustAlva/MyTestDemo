package com.lib.utils.filepreview.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.lib.utils.R;
import com.lib.utils.base.BaseActivity;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilePreviewActivity extends BaseActivity implements TbsReaderView.ReaderCallback {


    @BindView(R.id.rl_file_preview)
    RelativeLayout rlLayout;
    @BindView(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @BindView(R.id.vs_topbar_back)
    ViewStub vsTopbarBack;

    private TbsReaderView mTbsReaderView;
    private DownloadManager mDownloadManager;
    private long mRequestId;
    private DownloadObserver mDownloadObserver;
    private String mFileUrl = "http://www.beijing.gov.cn/zhuanti/ggfw/htsfwbxzzt/shxfl/fw/P020150720516332194302.doc";
    //private String mFileUrl = "http://10.108.1.30:8080/invertImg/20180509094948598.docx";
    private String mFileName;

    /**
     * 设置主布局文件
     *
     * @param savedInstanceState
     */
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_file_preview);
        ButterKnife.bind(this);
        //标题
        setTopBarTitle(tvTopbarTitle,"文件预览",true,vsTopbarBack);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        //申请定位权限
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.PHONE)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //同意
                        //下载文件
                        dealFile(mFileUrl);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        //拒绝
                        showToast(getResources().getString(R.string.permission_rationale_message));
                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);
                    }
                })
                .request();
    }

    private void dealFile(String url) {
        mTbsReaderView = new TbsReaderView(this, this);
        rlLayout.addView(mTbsReaderView, new RelativeLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mFileName = parseName(url);
        if (isLocalExist(mFileName)) {
            displayFile(mFileName);
        } else {
            startDownload(url, mFileName);
        }
    }

    //显示
    private void displayFile(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", getLocalFile(name).getPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(name), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    private boolean isLocalExist(String name) {
        return getLocalFile(name).exists();
    }

    private File getLocalFile(String name) {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
    }

    /**
     * 下载
     *
     * @param url
     * @param name
     */
    private void startDownload(String url, String name) {
        mDownloadObserver = new DownloadObserver(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true, mDownloadObserver);

        mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        mRequestId = mDownloadManager.enqueue(request);
    }

    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(mRequestId);
        Cursor cursor = null;
        try {
            cursor = mDownloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载的字节数
                int currentBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //总需下载的字节数
                int totalBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //状态所在的列索引
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                Log.i("downloadUpdate: ", currentBytes + " " + totalBytes + " " + status);
                if (DownloadManager.STATUS_SUCCESSFUL == status) {
                    displayFile(mFileName);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
        if (mDownloadObserver != null) {
            getContentResolver().unregisterContentObserver(mDownloadObserver);
        }
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }



    private class DownloadObserver extends ContentObserver {

        private DownloadObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            Log.i("downloadUpdate: ", "onChange(boolean selfChange, Uri uri)");
            queryDownloadStatus();
        }
    }

}
