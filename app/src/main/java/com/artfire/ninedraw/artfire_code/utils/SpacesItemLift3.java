package com.artfire.ninedraw.artfire_code.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class SpacesItemLift3 extends RecyclerView.ItemDecoration {
    private Context context;
    private int mSpace;
    public SpacesItemLift3(Context context,int space) {
        this.context = context;
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


            if (parent.getChildLayoutPosition(view) == 0) {

            } else {
                //只设置头编辑
                outRect.left = AppUtil.dp2px(context, mSpace);
        }


    }
}
