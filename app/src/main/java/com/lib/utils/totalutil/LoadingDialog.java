package com.lib.utils.totalutil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.utils.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Create by keding.zhou 
 * Describe 加载 dialog
 * Date: 2017/6/20 14:02
 */
public class LoadingDialog {

    //加载动画View
    private AVLoadingIndicatorView aviLoadView;
    private  Dialog loadingDialog;
    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg  加载显示文字内容
     * @param tag 是否可取消
     * @return
     */
    public  Dialog createLoadingDialog(Context context, String msg ,boolean tag) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view

        aviLoadView = (AVLoadingIndicatorView) v.findViewById(R.id.avi_loading_dialog);
        TextView tvContent = (TextView) v.findViewById(R.id.tv_loading_dialog);

        // 加载动画
        aviLoadView.show();
        //加载动画样式
        aviLoadView.setIndicator("PacmanIndicator");
        // 使用ImageView显示动画
        tvContent.setText(msg);// 设置加载信息

        loadingDialog = new AlertDialog.Builder(context).create();// 创建自定义样式dialog
        loadingDialog.show();
        loadingDialog.getWindow().setContentView(v);
        loadingDialog.setCancelable(tag);// 用“返回键”取消

        return loadingDialog;

    }

    /**
     * 关闭 LoadingDialog
     */
    public void cancleLoadingDialog(){
        if (null!=loadingDialog) {
            loadingDialog.cancel();
            aviLoadView.hide();
            loadingDialog = null;
        }
    }

}
