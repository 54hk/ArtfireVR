package com.artfire.ninedraw.alive.achieve;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.multidex.MultiDex;

import com.artfire.ninedraw.alive.core.utils.FileUtils;


//import com.squareup.leakcanary.LeakCanary;

public class MutiApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(new ConnectivityChangedReceiver(), filter);
//        if(BuildConfig.DEBUG) {
//            LogUtil.enalbeDebug();
//        } else {
//            LogUtil.disableDebug();
//        }
//        AlivcLivePusher.showDebugView(this);
        //复制asset中的资源到内存卡
//        copyTask.execute();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    class ConnectivityChangedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
    private AsyncTask copyTask=new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] objects) {
            FileUtils.copyAll(getApplicationContext());
            return null;
        }
    };

}
