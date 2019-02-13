package com.artfire.ninedraw.artfire_code.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.adapter.ViewPagerAdapter;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.ui.fragment.HomePagerFragment;
import com.artfire.ninedraw.artfire_code.ui.fragment.MyFragment;
import com.artfire.ninedraw.artfire_code.utils.SpecialTab;
import com.artfire.ninedraw.artfire_code.utils.SpecialTabRound;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainArtActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.tab)
    PageNavigationView tab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private Context mContext;
    private ArrayList<Fragment> list;
    private ViewPagerAdapter pagerAdapter;
    private NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mContext = MainArtActivity.this;
        isShowTitle(false);
        initView();

    }


    @Override
    public int setLayoutId() {
        return R.layout.activity_main_art;
    }

    @Override
    public String setTitle() {
        return null;
    }


    @Override
    public void wingetListener() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initView() {
        list = new ArrayList();
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        list.add(homePagerFragment);
        list.add(new MyFragment());
        pagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), list);
        navigationController = tab.custom()
                .addItem(newItem(R.mipmap.home, R.mipmap.home, "首页"))
                .addItem(newRoundItem(R.mipmap.scan, R.mipmap.scan, ""))
                .addItem(newItem(R.mipmap.my, R.mipmap.my, "我的"))
                .build();

        mViewPager.setAdapter(pagerAdapter);

        //自动适配ViewPager页面切换
//        navigationController.setupWithViewPager(viewPager);
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {

                if (index == 0) {//首页
                    mViewPager.setCurrentItem(0);
                } else if (index == 2) {  //我的
                    mViewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    /**
     * 正常tab
     */
    @TargetApi(Build.VERSION_CODES.M)
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(getColor(R.color.red));
        mainTab.setTextCheckedColor(getColor(R.color.web5fec46));
        return mainTab;
    }

    /**
     * 圆形tab
     */
    @TargetApi(Build.VERSION_CODES.M)
    private BaseTabItem newRoundItem(int drawable, int checkedDrawable, String text) {
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(getColor(R.color.red));
        mainTab.setTextCheckedColor(getColor(R.color.web5fec46));
        return mainTab;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            //首页
            if(null!=navigationController){
                navigationController.setSelect(0);
            }
        }else if(position == 1){
            // 我的
            if(null!=navigationController){
                navigationController.setSelect(2);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
