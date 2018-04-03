package com.lib.utils.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.utils.R;

/**
 * describe:  Fragment 基类
 * creator: keding.zhou
 * date: 2018/1/24 14:19
 */
public abstract class BaseFragment extends Fragment {
    private final static String TAG_IF_ELSE = "IF_ELSE";
    protected Context mContext;
    protected View convertView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        try {
            mContext = getActivity();
           // inflater = LayoutInflater.from(mContext);
            convertView = setContentView(inflater  );
            initData();
            initView(convertView);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("BaseFragment:"+e.toString());
        }
        return convertView;
    }

    protected abstract View setContentView(LayoutInflater inflater  );

    protected abstract void initData();

    protected abstract void initView(View convertView);

    /**
     * @param text
     * @param <T>
     */
    protected <T> void showToast(T text) {
        if (null != text) {
            Toast.makeText(mContext, text + "", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG_IF_ELSE, "输入值不能为空！");
        }
    }
    /**
     * 跳转
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }


}
