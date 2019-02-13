package com.artfire.ninedraw.artfire_code.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseFragment;

/**
 * Created by 54hk on 2019/2/12.
 */

public class HomePagerFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_pager_fragment, null);
        return inflate;
    }

    @Override
    protected void lazyLoad() {

    }
}
