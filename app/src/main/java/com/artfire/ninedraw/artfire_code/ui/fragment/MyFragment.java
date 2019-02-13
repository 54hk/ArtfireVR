package com.artfire.ninedraw.artfire_code.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.MyHostoryBean;
import com.artfire.ninedraw.artfire_code.adapter.MyHistoryAdater;
import com.artfire.ninedraw.artfire_code.base.BaseFragment;
import com.artfire.ninedraw.artfire_code.utils.SpacesItemLift3;
import com.artfire.ninedraw.artfire_code.utils.StatusBarUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 54hk on 2019/2/12.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.rv_hostory)
    RecyclerView rvHostory;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.my_fragment, null);
        //将状态栏弄成透明的
        StatusBarUtils.transparencyBar(getActivity());
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        ArrayList<MyHostoryBean> hostoryList = new ArrayList<>();
        MyHostoryBean myHostoryBean = new MyHostoryBean();
        myHostoryBean.img = "http://www.guangyuanol.cn/uploads/allimg/190128/12435242L-0.jpg";
        myHostoryBean.name="我的就是课程";
        hostoryList.add(myHostoryBean);
        MyHostoryBean myHostoryBean1 = new MyHostoryBean();
        myHostoryBean1.name = "你才我是打算卢卡斯";
        myHostoryBean1.img = "http://pic1.nipic.com/2008-11-20/20081120145730349_2.jpg";
        hostoryList.add(myHostoryBean1);
        MyHistoryAdater historyAdater = new MyHistoryAdater(getActivity(), R.layout.my_history_adapter, hostoryList);
        rvHostory.setAdapter(historyAdater);
        rvHostory.addItemDecoration(new SpacesItemLift3(getActivity(), 3));
        historyAdater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHostory.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
