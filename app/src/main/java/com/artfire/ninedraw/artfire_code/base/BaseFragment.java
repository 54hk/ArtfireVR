package com.artfire.ninedraw.artfire_code.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by 54hk on 2019/1/29.
 */

public abstract class BaseFragment extends Fragment {
    protected Boolean isVisible = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }
    protected abstract void lazyLoad();
    protected void onInvisible(){}


}