package com.lib.utils.viewpagertest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.utils.R;
import com.lib.utils.base.BaseViewHolder;
import com.lib.utils.viewpagertest.bean.FragmentDataBean;

import java.util.List;

/**
 * describe: view复用viewpager adapter
 * creator: keding.zhou
 * date: 2018/5/14 9:03
 */
public class MyMultiplexViewpagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<FragmentDataBean> mList;

    public MyMultiplexViewpagerAdapter(Context mContext, List<FragmentDataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {

        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MyPagerViewHolder viewHolder = null;
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_multiplex_pager, null, false);
        if (viewHolder == null) {
            viewHolder = new MyPagerViewHolder(convertView);
        }
        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvContent.setText(mList.get(position).getContent());
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = (View) object;
        container.removeView(contentView);
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    private class MyPagerViewHolder extends BaseViewHolder {

        private TextView tvTitle;
        private TextView tvContent;

        public MyPagerViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_fragment_multiplex_title);
            tvContent = itemView.findViewById(R.id.tv_fragment_multiplex_content);
        }
    }

}
