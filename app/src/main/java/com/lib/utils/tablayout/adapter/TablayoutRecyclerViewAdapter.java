package com.lib.utils.tablayout.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lib.utils.R;
import com.lib.utils.tablayout.bean.RecyclerBean;

import java.util.List;

public class TablayoutRecyclerViewAdapter extends RecyclerArrayAdapter<RecyclerBean> {
    /**
     * Constructor
     */
    public TablayoutRecyclerViewAdapter(Context context, List<RecyclerBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent, R.layout.item_recycler_textview);
    }


    private class MyViewHolder extends BaseViewHolder<RecyclerBean> {
        private TextView tvContent;
        public MyViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            tvContent = $(R.id.tv_item_recycler_content);
        }

        @Override
        public void setData(RecyclerBean data) {
            super.setData(data);
            if (data!=null) {
                String content = data.getContent();
                tvContent.setText(content);
            }
        }
    }
}

