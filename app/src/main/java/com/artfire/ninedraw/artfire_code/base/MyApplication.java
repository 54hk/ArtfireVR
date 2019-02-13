package com.artfire.ninedraw.artfire_code.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class MyApplication extends MultiDexApplication{

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Globals.context = getApplicationContext();
//        Utils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }
}
