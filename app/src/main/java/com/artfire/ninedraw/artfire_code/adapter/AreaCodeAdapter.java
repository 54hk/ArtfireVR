package com.artfire.ninedraw.artfire_code.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.AreaCodeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2017/9/22.  获得手机号区号
 */

public class AreaCodeAdapter extends BaseAdapter {
    Context context;
    List<AreaCodeBean.ContentListBean.DataBean> listData;

    public AreaCodeAdapter(Context context, List<AreaCodeBean.ContentListBean.DataBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_area_code, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AreaCodeBean.ContentListBean.DataBean datasBean = listData.get(position);
//        if (!TextUtils.isEmpty(listData.get(position).getTitle())) {
//            viewHolder.ivArtistName.setText(listData.get(position).getTitle());
//        }
        if (!TextUtils.isEmpty(listData.get(position).getName()) && !TextUtils.isEmpty(listData.get(position).getCode())) {
            viewHolder.ivArtistName.setText(listData.get(position).getName() + "+" + listData.get(position).getCode());
        }
        if (!TextUtils.isEmpty(listData.get(position).getTitle())) {
            viewHolder.tvItemTit.setText(listData.get(position).getTitle());
        }

        // 根据position获取分类的首字母的Char ascii值
        String section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.llTitleLayout.setVisibility(View.VISIBLE);
        } else {
            viewHolder.llTitleLayout.setVisibility(View.GONE);

        }
        if (position + 1 == listData.size()) {
            viewHolder.viewLine.setVisibility(View.VISIBLE);
        } else {
            if (listData.get(position + 1).getTitle().equals(datasBean.getTitle())) {
                viewHolder.viewLine.setVisibility(View.VISIBLE);
            } else {
                viewHolder.viewLine.setVisibility(View.GONE);
            }
        }

        return convertView;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public String getSectionForPosition(int position) {
        return listData.get(position).getTitle();
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(String section) {
        for (int i = 0; i < listData.size(); i++) {
            String sortStr = listData.get(i).getTitle();
//            char firstChar = sortStr.toUpperCase().charAt(0);
            if (sortStr.equals(section)) {
                return i;
            }
        }
        return -1;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_tit)
        TextView tvItemTit;
        @BindView(R.id.ll_title_layout)
        LinearLayout llTitleLayout;
        @BindView(R.id.iv_artist_name)
        TextView ivArtistName;
        @BindView(R.id.view_line)
        View viewLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
