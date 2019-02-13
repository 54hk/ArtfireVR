package com.artfire.ninedraw.artfire_code.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;

/**
 * ViewPager的adapter
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    private Activity activity;

    public ViewPagerAdapter(Activity activity, FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }




}
