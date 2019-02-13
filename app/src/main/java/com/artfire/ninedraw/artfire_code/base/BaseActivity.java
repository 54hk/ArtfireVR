package com.artfire.ninedraw.artfire_code.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.fl_base_content)
    FrameLayout flBaseContent;
    @BindView(R.id.tv_title_text)
    TextView tvTitleText;
    @BindView(R.id.title_left1)
    FrameLayout titleLeft1;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    private LayoutInflater inflater;
    private View layoutContent;   //当前页面的布局
    private View layoutNetError;   //网络异常布局
    private View layoutLoading;   //网络异常布局
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        flBaseContent = (FrameLayout) findViewById(R.id.fl_base_content);
        inflater = this.getLayoutInflater();

        layoutContent = inflater.inflate(setLayoutId(), null);
        layoutNetError = inflater.inflate(R.layout.network_error, null);
        layoutLoading = inflater.inflate(R.layout.loading, null);
        flBaseContent.addView(layoutContent);

        ButterKnife.bind(this);
        initTitle();
        wingetListener();
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    public boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //设置标题
    public void initTitle() {
        String title = setTitle();
        if (!TextUtils.isEmpty(title)) {
            tvTitleText.setText(setTitle());
        }
        titleLeft1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });
    }

    /**
     * 隐藏头部
     */
    protected void isShowTitle(Boolean b) {
        if (b) {
            rlTitle.setVisibility(View.VISIBLE);
        } else {
            rlTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 获取title的左边第一个按钮
     *
     * @return
     */
    protected FrameLayout getTitleLeft1() {
        return titleLeft1;
    }

    /**
     * 获取title
     *
     * @return
     */
    protected TextView getTitleText() {
        return tvTitleText;
    }


    /**
     * 显示网络错误页面
     */
    public void showNetError() {
        Boolean isHasNetError = false;
        layoutContent.setVisibility(View.GONE);
        for (int i = 0; i < flBaseContent.getChildCount(); i++) {
            if (flBaseContent.getChildAt(i) == layoutNetError) {
                isHasNetError = true;
                return;
            }
        }
        if (isHasNetError) {
            layoutNetError.setVisibility(View.VISIBLE);
        } else {
            flBaseContent.addView(layoutNetError);
        }
    }

    /**
     * 关闭网络错误页面
     */
    public void closeNetError() {
        layoutNetError.setVisibility(View.GONE);
        layoutContent.setVisibility(View.VISIBLE);
    }

    public abstract int setLayoutId();

    public abstract String setTitle();

    public abstract void wingetListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


}
