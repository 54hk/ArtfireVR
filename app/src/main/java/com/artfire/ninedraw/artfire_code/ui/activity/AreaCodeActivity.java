package com.artfire.ninedraw.artfire_code.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.AreaCodeBean;
import com.artfire.ninedraw.artfire_code.Bean.ArtistListBean;
import com.artfire.ninedraw.artfire_code.adapter.AreaCodeAdapter;
import com.artfire.ninedraw.artfire_code.adapter.SearchArtistListAdapter;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.utils.FileUtilss;
import com.artfire.ninedraw.artfire_code.utils.GsonUtils;
import com.artfire.ninedraw.artfire_code.utils.SideBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择国家区号
 */
public class AreaCodeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    @BindView(R.id.lv_artist)
    ListView lvArtist;
    @BindView(R.id.title_layout_no_artist)
    TextView titleLayoutNoArtist;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_layout)
    LinearLayout titleLayout;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;

    List<AreaCodeBean.ContentListBean.DataBean> listData;
    AreaCodeAdapter mAdapter;

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    private SearchArtistListAdapter searchArtistListAdapter;
    private List<ArtistListBean.AppendDataBean.ListBean.DatasBean> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        sidrbar.setBg(R.color.bg);
        listData = new ArrayList<>();
        AreaCodeBean areaCodeBean = GsonUtils.json2bean(FileUtilss.readAssetsTxt(this, "AFCountryCodes"), AreaCodeBean.class);
        if(areaCodeBean != null && areaCodeBean.getContentList().size() > 0) {
            for (int i=0; i<areaCodeBean.getContentList().size(); i++) {
                for (int j=0; j<areaCodeBean.getContentList().get(i).getData().size(); j++) {
                    areaCodeBean.getContentList().get(i).getData().get(j).setTitle(areaCodeBean.getContentList().get(i).getName());
                    listData.add(areaCodeBean.getContentList().get(i).getData().get(j));
                }
            }
        }

        mAdapter = new AreaCodeAdapter(this, listData);
        lvArtist.setAdapter(mAdapter);
        lvArtist.setOnItemClickListener(this);
        searchList = new ArrayList<>();
        searchArtistListAdapter = new SearchArtistListAdapter(this, searchList);

        lvArtist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listData.size() == 0) {
                    return;
                }
                String section = getSectionForPosition(firstVisibleItem);
                String nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    tvTitle.setText(listData.get(
                            getPositionForSection(section)).getTitle());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        // 设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = mAdapter.getPositionForSection(s);
                if (position != -1) {
                    lvArtist.setSelection(position);
                }

            }
        });

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_area_code;
    }

    @Override
    public String setTitle() {
        return getResources().getString(R.string.sel_area);
    }

    @Override
    public void wingetListener() {
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
            if (sortStr != null && sortStr.equals(section)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = getIntent();
        intent.putExtra("area", AreaCodeActivity.this.getString(R.string.add_area)+listData.get(position).getCode());
        setResult(100, intent);
        finish();
    }
}
