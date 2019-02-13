package com.artfire.ninedraw.vuforia_lib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.vuforia_lib.utils.SharedPreferenceUtil;
import com.blankj.utilcode.util.FileUtils;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.IVideoPlayer;
import org.song.videoplayer.PlayListener;
import org.song.videoplayer.Util;
import org.song.videoplayer.media.AndroidMedia;
import org.song.videoplayer.media.BaseMedia;

import java.io.File;

/**
 * 作者:李少波
 * 邮箱:lishaobo@seengene.com
 * 日期:2018/5/14
 */

public class VideoViewActivity extends Activity {
    public final static String PARAM_VIDEO_PATH = "movieName";
    public final static String PARAM_SEEK_POSITION = "currentSeekPosition";

    public static String lightKey = "lightPercent";
    public static String firstLightChange = "firstLightFlag";

    String videoName = null;
    DemoQSVideoView demoVideoView;

    boolean flag;//记录退出时播放状态 回来的时候继续播放
    int position;//记录销毁时的进度 回来继续盖进度播放

    private int mSeekPosition = 0;

//    String mp4 = "content://media/external/video/media/14976";

    String videoTitle = "";

    public float curLight = 0.8f;
//    public float curLight = 0.2f;

    public float maxLight = 160;
    boolean isFristChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_video_preview);

        if(!MainActivity.isPhone){
            isFristChange =  SharedPreferenceUtil.getBoolean(this, firstLightChange, true);
            curLight = SharedPreferenceUtil.getFloat(this, lightKey, 0.8f);
//            curLight = SharedPreferenceUtil.getFloat(this, lightKey, 0.2f);
            setScreenBrightness(curLight);
        }
        else {
            isFristChange = false;
        }

//        setScreenBrightness(0.28f);

        if (getIntent().hasExtra(PARAM_VIDEO_PATH)) {
            videoName = getIntent().getStringExtra(PARAM_VIDEO_PATH);
        }
        String videoFilePath = Environment.getExternalStorageDirectory() + "/tianyuwenhua/";
        FileUtils.createOrExistsDir(videoFilePath);
        Toast.makeText(this ,videoName+"",Toast.LENGTH_LONG).show();
        File file = new File(videoFilePath + videoName);
     /*   if (videoName == null || videoName.isEmpty() || !file.exists()) {
            ToastUtils.showShort("图片文件路径错误");
            finish();
            overridePendingTransition(0, 0);
            return;
        }*/
        if (getIntent().hasExtra(PARAM_SEEK_POSITION)) {
            mSeekPosition = getIntent().getIntExtra(PARAM_SEEK_POSITION, 0);
        }

        videoTitle = file.getName();

        demoVideoView = findViewById(R.id.activity_videoPreview_video);
//        demoVideoView.getCoverImageView().setImageResource(R.mipmap.cover);
        demoVideoView.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDisplayMetrics().widthPixels * 9 / 16));
        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
        demoVideoView.enterFullMode = 0;
//        demoVideoView.enterFullMode = 1;
        //是否非全屏下也可以手势调节进度
        demoVideoView.isWindowGesture = true;


        demoVideoView.setPlayListener(new PlayListener() {
            @Override
            public void onStatus(int status) {//播放状态
             /*   if (status == IVideoPlayer.STATE_AUTO_COMPLETE) {
                    demoVideoView.quitWindowFullscreen();//播放完成退出全屏
                }*/
            }

            @Override//全屏/普通/浮窗
            public void onMode(int mode) {

            }

            @Override
            public void onEvent(int what, Integer... extra) {
                if (what == DemoQSVideoView.EVENT_CONTROL_VIEW & Build.VERSION.SDK_INT >= 19 & !demoVideoView.isWindowFloatMode())
                    if (extra[0] == 0)//状态栏隐藏/显示
                        Util.CLEAR_FULL(VideoViewActivity.this);
                    else
                        Util.SET_FULL(VideoViewActivity.this);
            }

        });


        demoVideoView.enterWindowFullscreen();

  /*      FloatParams floatParams = new FloatParams();
        floatParams.x = ScreenUtils.getScreenWidth()/2;//浮窗中心坐标x
        floatParams.y = ScreenUtils.getScreenHeight()/2;//浮窗中心坐标y
        floatParams.w = ScreenUtils.getScreenWidth();//宽
        floatParams.h = ScreenUtils.getScreenHeight();//高
//        floatParams.round = 30;//浮窗圆角 需SDK_INT >= 21
        floatParams.fade = 0.4f;//透明度 需SDK_INT >= 11
        floatParams.canMove = false;//是否可以拖动
        floatParams.systemFloat = false;//TRUE系统浮窗需要权限　FALSE界面内浮窗

        demoVideoView.enterWindowFloat(floatParams);*/

        Uri uri = Uri.parse(videoFilePath + videoName);

//        play(uri.toString(), IjkMedia.class);
//        play(uri.toString(), AndroidMedia.class);
        play("http://artfire-file.oss-cn-beijing.aliyuncs.com/yike_content/rWQGDrWbYQ.mp4", AndroidMedia.class);

       /* if (!demoVideoView.enterWindowFloat(floatParams)) {
            Toast.makeText(this,"没有浮窗权限",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }*/
//    }

    }


    public void onBackPressed() {
        // Request the media player to prepare for termination:
        prepareForTermination();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }


    private void play(String url, Class<? extends BaseMedia> decodeMedia) {
        demoVideoView.release();
        demoVideoView.setDecodeMedia(decodeMedia);
        demoVideoView.setUp(url, videoTitle);
//        demoVideoView.setUp(url, "这是一一一一一一一一一个标题");
        demoVideoView.seekTo(mSeekPosition);
        //不静音
        demoVideoView.setMute(false);
        demoVideoView.play();
//        this.url = url;
//        this.decodeMedia = decodeMedia;
    }


    //=======================以下生命周期控制=======================

    @Override
    public void onResume() {
        super.onResume();
        if (flag)
            demoVideoView.play();
//        handler.removeCallbacks(runnable);
        if (position > 0) {
            demoVideoView.seekTo(position);
            position = 0;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (demoVideoView.isSystemFloatMode())
            return;
        //暂停
        flag = demoVideoView.isPlaying();
        demoVideoView.pause();
        if (demoVideoView.getCurrentState() != IVideoPlayer.STATE_AUTO_COMPLETE) {
            position = demoVideoView.getPosition();
        }
    }

    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                prepareForTermination();
                overridePendingTransition(0, 0);
                finish();
                return true;

         /*   case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(isFristChange){
                    curLight -= 0.1f;
                    if (curLight <= 0.1f) {
                        curLight = 0.1f;
                    }
                    setScreenBrightness(curLight);
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(isFristChange){
                    curLight += 0.1f;
                    if (curLight * 255 > maxLight) {
                        curLight -= 0.1f;
                    }
                    setScreenBrightness(curLight);
                }
                break;*/


        }

        return super.onKeyDown(keyCode, event);
    }

    private void prepareForTermination() {
        if (demoVideoView.getCurrentState() != IVideoPlayer.STATE_AUTO_COMPLETE) {
            position = demoVideoView.getPosition();
        }
//        demoVideoView.quitWindowFloat();
        // This activity was started for result, thus we need to return
        // whether it was playing and in which position:
        Intent i = new Intent();
        i.putExtra("movieName", videoName);
        i.putExtra("currentSeekPosition", position);
        i.putExtra("playing", false);
        setResult(Activity.RESULT_OK, i);
    }


    @Override
    public void onStop() {
        super.onStop();
   /*     if (demoVideoView.isSystemFloatMode())
            return;
        //不马上销毁 延时15秒
//        handler.postDelayed(runnable, 1000 * 15);
        if (demoVideoView.getCurrentState() != IVideoPlayer.STATE_AUTO_COMPLETE) {
            position = demoVideoView.getPosition();
        }
        demoVideoView.release();*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();//销毁
       if(isFristChange){
           SharedPreferenceUtil.putFloat(this, lightKey, curLight);
           SharedPreferenceUtil.putBoolean(this, firstLightChange, false);
           isFristChange = false;
       }
    }
    /**
     * 设置屏幕的亮度
     */
    private void setScreenBrightness(float percent) {

        //设置当前窗口的亮度值.这种方法需要权限android.permission.WRITE_EXTERNAL_STORAGE
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//        float f = process/100f*lightTotalSize;

        localLayoutParams.screenBrightness = percent;
        getWindow().setAttributes(localLayoutParams);

        //修改系统的亮度值,以至于退出应用程序亮度保持
//        saveBrightness(getContentResolver(), (int) (percent*255));

    }


  /*  @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_DOWN:


                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }*/


}
