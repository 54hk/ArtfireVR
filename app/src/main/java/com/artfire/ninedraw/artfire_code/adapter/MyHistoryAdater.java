package com.artfire.ninedraw.artfire_code.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.MyHostoryBean;
import com.artfire.ninedraw.artfire_code.utils.AppUtil;
import com.artfire.ninedraw.artfire_code.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 54hk on 2019/2/13.
 * MyFragment 中使用
 */

public class MyHistoryAdater extends BaseQuickAdapter<MyHostoryBean, BaseViewHolder> {

    private RelativeLayout rlParent;
    private LinearLayout llParent;
    private ImageView ivCourse;
    private final int screenWidth;
    private Context context;
    private TextView tvName;

    public MyHistoryAdater(Context context, int layoutResId, @Nullable List<MyHostoryBean> data) {
        super(layoutResId, data);
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected void convert(BaseViewHolder helper, MyHostoryBean item) {
        rlParent = helper.getView(R.id.rl_parent);
        ivCourse = helper.getView(R.id.iv_course);
        tvName = helper.getView(R.id.tv_name);
        // 将宽评分
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivCourse.getLayoutParams();
        layoutParams.width = (screenWidth - AppUtil.dp2px(context, 26 * 2 + 3)) / 2;
        ivCourse.setLayoutParams(layoutParams);

        GlideUtil.loadAty(item.img, ivCourse);
        tvName.setText(item.name);
    }
}
