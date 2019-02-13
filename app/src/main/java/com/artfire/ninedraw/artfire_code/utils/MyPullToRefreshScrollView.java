package com.artfire.ninedraw.artfire_code.utils;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class MyPullToRefreshScrollView extends PullToRefreshScrollView {

    public MyPullToRefreshScrollView(Context context) {
        super(context);
    }

    public MyPullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPullToRefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public MyPullToRefreshScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }
}
