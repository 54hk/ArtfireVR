package com.artfire.ninedraw.vuforia_lib;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleApplicationControl;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleApplicationException;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleApplicationSession;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.utils.SampleApplicationGLView;
import com.artfire.ninedraw.vuforia_lib.bean.MediaItem;
import com.artfire.ninedraw.vuforia_lib.bean.RecognizeItem;
import com.artfire.ninedraw.vuforia_lib.bean.VuforiaImageTarget;
import com.artfire.ninedraw.vuforia_lib.tools.LogUtil;
import com.artfire.ninedraw.vuforia_lib.utils.DateUtil;
import com.artfire.ninedraw.vuforia_lib.vuforiaDemo.VideoPlaybackRenderer;
import com.artfire.ninedraw.vuforia_lib.vuforiaDemo.VideoPlayerHelper;
import com.artfire.ninedraw.vuforia_lib.widget.PowerIconView;
import com.blankj.utilcode.util.ToastUtils;
import com.vuforia.CameraDevice;
import com.vuforia.DataSet;
import com.vuforia.HINT;
import com.vuforia.ObjectTracker;
import com.vuforia.STORAGE_TYPE;
import com.vuforia.State;
import com.vuforia.Tracker;
import com.vuforia.TrackerManager;
import com.vuforia.Vuforia;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements SampleApplicationControl {
    private static final String LOGTAG = "MainActivity";

    public static boolean isPhone = false;
    //    public static boolean isSHowPrew = true;
    public static boolean isSHowPrew = true;


    public static int REQUEST_CODE_FULL_SCREEN = 10001;
    SampleApplicationSession vuforiaAppSession;
    // Our OpenGL view:
    private SampleApplicationGLView mGlView;

    // Our renderer:
    private VideoPlaybackRenderer mRenderer;

    // The textures we will use for rendering:
//    private Vector<Texture> mTextures;

    DataSet tianYuWenHuaDat = null;
//    private boolean mPlayFullscreenVideo = false;

    boolean mIsInitialized = false;


    // A boolean to indicate whether we come from full screen:
    private boolean mReturningFromFullScreen = false;
    // Alert Dialog used to display SDK errors
//    private AlertDialog mErrorDialog;

    ArrayList<RecognizeItem> recognizeItemArrayList = new ArrayList<>();


    public PowerIconView powerView;//电量图标
    public TextView powerText;//电量文本
    public TextView miniTimeView;//电量文本
    private BackgroundBroadcastReceiver backgroundBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
//        setContentView(R.layout.activity_main);
        vuforiaAppSession = new SampleApplicationSession(this);
        vuforiaAppSession.initAR(this, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initBroadCastReceiver();


    }


    @Override
    protected void onResume() {
        super.onResume();

        vuforiaAppSession.onResume();
        // Reload all the movies
        if (mRenderer != null) {
            for (RecognizeItem recognizeItem : recognizeItemArrayList) {
                if (mReturningFromFullScreen) {
                    recognizeItem.mediaItem.mShouldPlayImmediately = recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.PLAYING;
                } else {
                    recognizeItem.mediaItem.mShouldPlayImmediately = false;
                }
//                mRenderer.requestLoad(recognizeItem);

            }
        }

        mReturningFromFullScreen = false;
    }


    public void onConfigurationChanged(Configuration config) {
        Log.d(LOGTAG, "onConfigurationChanged");
        super.onConfigurationChanged(config);

        vuforiaAppSession.onConfigurationChanged();
    }


    // Called when the system is about to start resuming a previous activity.
    protected void onPause() {
        Log.d(LOGTAG, "onPause");
        super.onPause();

        if (mGlView != null) {
            mGlView.setVisibility(View.INVISIBLE);
            mGlView.onPause();
        }


        for (RecognizeItem recognizeItem : recognizeItemArrayList) {
            // If the activity is paused we need to store the position in which
            // this was currently playing:
            if (recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.PLAYING) {
                recognizeItem.mediaItem.setmSeekPosition(recognizeItem.mediaItem.mVideoPlayerHelper.getCurrentPosition());

                LogUtil.d(LOGTAG, "onpause保存的值:" + recognizeItem.mediaItem.mVideoPlayerHelper.getCurrentPosition());
            }

            // We also need to release the resources used by the helper, though
            // we don't need to destroy it:
            if (recognizeItem.mediaItem.mVideoPlayerHelper != null) {
                recognizeItem.mediaItem.mVideoPlayerHelper.unload();

            }

        }
        // Store the playback state of the movies and unload them:

        mReturningFromFullScreen = false;

        try {
            vuforiaAppSession.pauseAR();
        } catch (SampleApplicationException e) {
            Log.e(LOGTAG, e.getString());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // The final call you receive before your activity is destroyed.
    protected void onDestroy() {
        Log.d(LOGTAG, "onDestroy");
        super.onDestroy();
        if (backgroundBroadcastReceiver != null) {
            unregisterReceiver(backgroundBroadcastReceiver);
        }

        for (RecognizeItem recognizeItem : recognizeItemArrayList) {
            // If the activity is paused we need to store the position in which
            // this was currently playing:
            if (recognizeItem.mediaItem.mVideoPlayerHelper != null) {
                recognizeItem.mediaItem.mVideoPlayerHelper.deinit();
                recognizeItem.mediaItem.mVideoPlayerHelper = null;
            }

        }

        try {
            vuforiaAppSession.stopAR();
        } catch (SampleApplicationException e) {
            Log.e(LOGTAG, e.getString());
        }

        System.gc();
    }


    // Initializes AR application components.
    private void initApplicationAR() {

        initRecogList();
        // Create OpenGL ES view:
        int depthSize = 16;
        int stencilSize = 0;
        boolean translucent = Vuforia.requiresAlpha();

        mGlView = new SampleApplicationGLView(this);
        mGlView.init(translucent, depthSize, stencilSize);

        mRenderer = new VideoPlaybackRenderer(this, vuforiaAppSession, recognizeItemArrayList);

        for (RecognizeItem recognizeItem : recognizeItemArrayList) {
            mRenderer.requestLoad(recognizeItem);

        }
        mGlView.setRenderer(mRenderer);


    }


    @Override
    public boolean doInitTrackers() {
        // Indicate if the trackers were initialized correctly
        boolean result = true;

        // Initialize the image tracker:
        TrackerManager trackerManager = TrackerManager.getInstance();
        Tracker tracker = trackerManager.initTracker(ObjectTracker
                .getClassType());
        if (tracker == null) {
            Log.d(LOGTAG, "Failed to initialize ObjectTracker.");
            result = false;
        }

        return result;
    }


    @Override
    public boolean doLoadTrackersData() {
        // Get the image tracker:
        TrackerManager trackerManager = TrackerManager.getInstance();
        ObjectTracker objectTracker = (ObjectTracker) trackerManager
                .getTracker(ObjectTracker.getClassType());
        if (objectTracker == null) {
            Log.d(
                    LOGTAG,
                    "Failed to load tracking data set because the ObjectTracker has not been initialized.");
            return false;
        }

        // Create the data sets:
        tianYuWenHuaDat = objectTracker.createDataSet();
        if (tianYuWenHuaDat == null) {
            Log.d(LOGTAG, "Failed to create a new tracking data.");
            return false;
        }

        // Load the data sets:
        if (!tianYuWenHuaDat.load(Environment.getExternalStorageDirectory() + "/yihuodownload/hk.xml",
//        if (!tianYuWenHuaDat.load("yang.xml",
                STORAGE_TYPE.STORAGE_ABSOLUTE)) {
            Log.d(LOGTAG, "Failed to load data set.");
            return false;
        }

        // Activate the data set:
        if (!objectTracker.activateDataSet(tianYuWenHuaDat)) {
            Log.d(LOGTAG, "Failed to activate data set.");
            return false;
        }

        Log.d(LOGTAG, "Successfully loaded and activated data set.");
        return true;
    }


    @Override
    public boolean doStartTrackers() {
        // Indicate if the trackers were started correctly
        boolean result = true;

        Tracker objectTracker = TrackerManager.getInstance().getTracker(
                ObjectTracker.getClassType());
        if (objectTracker != null) {
            objectTracker.start();
            Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 2);
        } else
            result = false;

        return result;
    }


    @Override
    public boolean doStopTrackers() {
        // Indicate if the trackers were stopped correctly
        boolean result = true;

        Tracker objectTracker = TrackerManager.getInstance().getTracker(
                ObjectTracker.getClassType());
        if (objectTracker != null)
            objectTracker.stop();
        else
            result = false;

        return result;
    }


    @Override
    public boolean doUnloadTrackersData() {
        // Indicate if the trackers were unloaded correctly
        boolean result = true;

        // Get the image tracker:
        TrackerManager trackerManager = TrackerManager.getInstance();
        ObjectTracker objectTracker = (ObjectTracker) trackerManager
                .getTracker(ObjectTracker.getClassType());
        if (objectTracker == null) {
            Log.d(
                    LOGTAG,
                    "Failed to destroy the tracking data set because the ObjectTracker has not been initialized.");
            return false;
        }

        if (tianYuWenHuaDat != null) {
            if (objectTracker.getActiveDataSet(0) == tianYuWenHuaDat
                    && !objectTracker.deactivateDataSet(tianYuWenHuaDat)) {
                Log.d(
                        LOGTAG,
                        "Failed to destroy the tracking data set StonesAndChips because the data set could not be deactivated.");
                result = false;
            } else if (!objectTracker.destroyDataSet(tianYuWenHuaDat)) {
                Log.d(LOGTAG,
                        "Failed to destroy the tracking data set StonesAndChips.");
                result = false;
            }

            tianYuWenHuaDat = null;
        }

        return result;
    }


    @Override
    public boolean doDeinitTrackers() {
        // Indicate if the trackers were deinitialized correctly
        boolean result = true;

        // Deinit the image tracker:
        TrackerManager trackerManager = TrackerManager.getInstance();
        trackerManager.deinitTracker(ObjectTracker.getClassType());

        return result;
    }


    @Override
    public void onInitARDone(SampleApplicationException exception) {

        if (exception == null) {
            initApplicationAR();

            mRenderer.setActive(true);

            // Now add the GL surface view. It is important
            // that the OpenGL ES surface view gets added
            // BEFORE the camera is started and video
            // background is configured.
            addContentView(mGlView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            View statusView = getLayoutInflater().inflate(R.layout.layout_statusbar, null);


            FrameLayout.LayoutParams statusLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            statusLayoutParams.gravity = Gravity.TOP;
            addContentView(statusView, statusLayoutParams);
            powerView = statusView.findViewById(R.id.launcher_statusbar_powericon);
            powerView.setVisibility(View.INVISIBLE);
            powerText = statusView.findViewById(R.id.launcher_statusbar_powertext);
            powerText.setVisibility(View.INVISIBLE);
            miniTimeView = statusView.findViewById(R.id.launcher_statusbar_timertext);
            updateTime();

            View tipsView = getLayoutInflater().inflate(R.layout.layout_focus_tips, null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            addContentView(tipsView, layoutParams);


            vuforiaAppSession.startAR(CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_DEFAULT);

            mIsInitialized = true;

        } else {
            Log.e(LOGTAG, exception.getString());
            showInitializationErrorMessage(exception.getString());
        }

    }

    // Shows initialization error messages as System dialogs
    public void showInitializationErrorMessage(String message) {
        ToastUtils.showLong(message);
        finish();

    }

    @Override
    public void onVuforiaResumed() {
        if (mGlView != null) {
            mGlView.setVisibility(View.VISIBLE);
            mGlView.onResume();
        }
    }

    @Override
    public void onVuforiaStarted() {
        mRenderer.updateRenderingPrimitives();

        // Set camera focus mode
        if (!CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_CONTINUOUSAUTO)) {
            // If continuous autofocus mode fails, attempt to set to a different mode
            if (!CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_TRIGGERAUTO)) {
                CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_NORMAL);
            }
        }

    }

    @Override
    public void onVuforiaUpdate(State state) {
    }


    @Override
    // Called when returning from the full screen player
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_FULL_SCREEN) {

//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            if (resultCode == RESULT_OK) {
                // The following values are used to indicate the position in
                // which the video was being played and whether it was being
                // played or not:
                String movieBeingPlayed = data.getStringExtra("movieName");
                mReturningFromFullScreen = true;

                for (RecognizeItem recognizeItem : recognizeItemArrayList) {
                    if (recognizeItem.fileName.equals(movieBeingPlayed)) {
                        recognizeItem.mediaItem.mSeekPosition = data.getIntExtra("currentSeekPosition", 0);
                        break;
                    }
                }


            }
        }
    }


    private void initRecogList() {

        recognizeItemArrayList = new ArrayList<>();


        File file = new File(Environment.getExternalStorageDirectory() + "/yihuodownload/hk.xml");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            List<VuforiaImageTarget> vuforiaImageTargets = null;
//                        vuforiaImageTargets = readXML(getAssets().open("yang.xml"));
            vuforiaImageTargets = readXML(fileInputStream);
            if (vuforiaImageTargets != null) {
                for (VuforiaImageTarget vuforiaImageTarget : vuforiaImageTargets) {
                    RecognizeItem recognizeItem = new RecognizeItem();
                    recognizeItem.datName = vuforiaImageTarget.name;
                    recognizeItem.fileName = vuforiaImageTarget.name + ".mp4";
                    recognizeItem.isTracking = false;
                    recognizeItem.mediaItem = new MediaItem(this, recognizeItem.fileName, true);
                    recognizeItemArrayList.add(recognizeItem);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //读取XML
    public static List<VuforiaImageTarget> readXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inStream, "UTF-8");
            int eventType = parser.getEventType();
            VuforiaImageTarget currentPerson = null;
            List<VuforiaImageTarget> persons = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT://文档开始事件,可以进行数据初始化处理
                        persons = new ArrayList<VuforiaImageTarget>();
                        break;
                    case XmlPullParser.START_TAG://开始元素事件
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("ImageTarget")) {
                            currentPerson = new VuforiaImageTarget();
                            currentPerson.name = parser.getAttributeValue(null, "name");// 如果后面是Text元素,即返回它的值
                            currentPerson.size = parser.getAttributeValue(null, "size");
                        }
                        break;
                    case XmlPullParser.END_TAG://结束元素事件
                        LogUtil.d(LOGTAG, "tag_end:" + parser.getName());
                        if (parser.getName().equalsIgnoreCase("ImageTarget") && currentPerson != null) {
                            persons.add(currentPerson);
                            currentPerson = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            inStream.close();
            return persons;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 更新时钟
     */
    public void updateTime() {
        Date now = new Date();


//        String time = DateUtil.intOne2TwoStr(DateUtil.getHour(now)) + ":" + DateUtil.intOne2TwoStr(DateUtil.getMinute(now));
//        String date = DateUtil.formatDateByStr(now, "M月d日");
        String weekday = DateUtil.getWeekDay(now);


        String time = DateUtil.formatDateByStr(now, "hh:mm a");

        time = time.replace("上午", "AM");
        time = time.replace("下午", "PM");

        /*timeView.setText(time);
        dateView.setText(date + weekday);*/
        if (miniTimeView != null) {

            miniTimeView.setText(weekday + time);
        }

    }

    /**
     * 更新电池电量
     *
     * @param intent
     */
    public void updateBattery(Intent intent) {
        if (powerText == null || powerView == null) {
            return;
        }
        powerView.setVisibility(View.VISIBLE);
        powerText.setVisibility(View.VISIBLE);
        Bundle bundle = intent.getExtras();
        // 获取当前电量
        int current = bundle.getInt("level");
        // 获取总电量
        int total = bundle.getInt("scale");
        float battery = current * 100 / total;

        int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {//充电中
            powerView.setValue(battery, 2);
            powerText.setText((int) battery + "%");


        } else if (battery > 0.2) {//电量较多
            powerView.setValue(battery, 1);
            powerText.setText((int) battery + "%");

        } else {
            powerView.setValue(battery, 3);//快没电了
            powerText.setText((int) battery + "%");

        }
    }


    //注册相关广播接收器
    private void initBroadCastReceiver() {
        IntentFilter backgroundFilter = new IntentFilter();
        backgroundFilter.addAction(Intent.ACTION_BATTERY_CHANGED);//电量变化广播
//        backgroundFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);//是否联网
        backgroundFilter.addAction(Intent.ACTION_TIME_TICK);//时钟变化
//        backgroundFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);//蓝牙数据通讯状态变更
//        backgroundFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        backgroundBroadcastReceiver = new BackgroundBroadcastReceiver();
        registerReceiver(backgroundBroadcastReceiver, backgroundFilter);
    }


    /**
     * 后台广播接收器
     */
    class BackgroundBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //电池电量变化广播
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                updateBattery(intent);

            }//系统时钟变化广播
            else if (action.equals(Intent.ACTION_TIME_TICK)) {
                updateTime();
            }
        }
    }


}
