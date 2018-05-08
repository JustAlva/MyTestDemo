package com.lib.utils.totalutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        aviLoadView =  v.findViewById(R.id.avi_loading_dialog);
        TextView tvContent =   v.findViewById(R.id.tv_loading_dialog);

        // 加载动画
        aviLoadView.show();
        //加载动画样式
       aviLoadView.setIndicator("LineSpinFadeLoaderIndicator");
        // 使用ImageView显示动画
        tvContent.setText(msg);// 设置加载信息

        loadingDialog = new AlertDialog.Builder(context)
                .setInverseBackgroundForced(false)
                .create();// 创建自定义样式dialog
        loadingDialog.show();
        WindowManager m = ((Activity)context).getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = loadingDialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height =(int)MeasureUtil.dp2px(context,150);    //高度设置为屏幕的0.3
        p.width =(int)MeasureUtil.dp2px(context,150);    //宽度设置为屏幕的0.5
        p.alpha = 0.5f;    // 设置透明度为0.5
        Window window = loadingDialog.getWindow();
        window.setAttributes(p);     //设置生效

        window.setContentView(v);
        window.setDimAmount(0);
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
