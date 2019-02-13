package com.artfire.ninedraw.artfire_code.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.ArtistListBean;
import com.artfire.ninedraw.artfire_code.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2017/9/22. 获得手机号区号
 */

public class SearchArtistListAdapter extends BaseAdapter {
    Context context;
    List<ArtistListBean.AppendDataBean.ListBean.DatasBean> listData;

    public SearchArtistListAdapter(Context context, List<ArtistListBean.AppendDataBean.ListBean.DatasBean> listData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_artist_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ArtistListBean.AppendDataBean.ListBean.DatasBean datasBean = listData.get(position);
        if (!TextUtils.isEmpty(listData.get(position).getTitle())) {
            viewHolder.ivArtistName.setText(listData.get(position).getTitle());
        }
        if (!TextUtils.isEmpty(listData.get(position).getIcon())) {
            GlideUtil.loadRoundPortraits(listData.get(position).getIcon(), viewHolder.ivArtistIcon);
        } else {
            GlideUtil.loadRoundPortraits("", viewHolder.ivArtistIcon);
        }
        if (!TextUtils.isEmpty(listData.get(position).getCname()) && !TextUtils.isEmpty(listData.get(position).getOname())) {
            viewHolder.ivArtistName.setText(listData.get(position).getCname() + "(" + listData.get(position).getOname() + ")");
        } else if (!TextUtils.isEmpty(listData.get(position).getCname())) {
            viewHolder.ivArtistName.setText(listData.get(position).getCname());
        } else if (!TextUtils.isEmpty(listData.get(position).getOname())) {
            viewHolder.ivArtistName.setText(listData.get(position).getOname());
        }
        if (!TextUtils.isEmpty(listData.get(position).getBirthlocation())) {
            viewHolder.ivArtistAddress.setText(listData.get(position).getBirthlocation());
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_artist_icon)
        ImageView ivArtistIcon;
        @BindView(R.id.iv_artist_name)
        TextView ivArtistName;
        @BindView(R.id.iv_artist_address)
        TextView ivArtistAddress;
        @BindView(R.id.view_line)
        View viewLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
