package com.artfire.ninedraw.vuforia_lib.bean;

import android.app.Activity;

import com.artfire.ninedraw.vuforia_lib.vuforiaDemo.VideoPlayerHelper;
import com.vuforia.Matrix44F;
import com.vuforia.Vec3F;

/**
 * 作者:李少波
 * 邮箱:lishaobo@seengene.com
 * 日期:2018/6/11
 */
public class MediaItem {
    public volatile  int mSeekPosition;
    public String mMovieName;

    public VideoPlayerHelper mVideoPlayerHelper;
    public VideoPlayerHelper.MEDIA_TYPE mCanRequestType;
    public VideoPlayerHelper.MEDIA_STATE currentStatus;
    public int[] videoPlaybackTextureID;
    public Matrix44F modelViewMatrix;
    public float videoQuadAspectRatio;
    public boolean mShouldPlayImmediately;
    public boolean mLoadRequested;
    private Activity activity;
    public float[] mTexCoordTransformationMatrix;

    public Vec3F targetPositiveDimensions;




    public boolean isFullScreen;

    public MediaItem(Activity activity, String mMovieName,boolean isFullScreen) {
        this.activity = activity;
        this.mMovieName = mMovieName;
//        mCanRequestType = VideoPlayerHelper.MEDIA_TYPE.ON_TEXTURE;
        mCanRequestType = VideoPlayerHelper.MEDIA_TYPE.ON_TEXTURE_FULLSCREEN;
        mSeekPosition = 0;
        mShouldPlayImmediately = false;
        mLoadRequested = false;


        modelViewMatrix= new Matrix44F();
        mTexCoordTransformationMatrix = new float[16];

        float[] temp = {0f, 0f, 0f};
        targetPositiveDimensions = new Vec3F();
        targetPositiveDimensions.setData(temp);
        videoPlaybackTextureID = new int[1];
        mVideoPlayerHelper = new VideoPlayerHelper();
        mVideoPlayerHelper.init();
        mVideoPlayerHelper.setActivity(activity);
        this.isFullScreen = isFullScreen;
    }

    public void setmSeekPosition(int mSeekPosition) {
        this.mSeekPosition = mSeekPosition;
    }
}
