package com.artfire.ninedraw.vuforia_lib.bean;

/**
 * 作者:李少波
 * 邮箱:lishaobo@seengene.com
 * 日期:2018/6/11
 */
public class RecognizeItem {
    // This variable will hold the transformed coordinates (changes every frame)
    public float videoQuadTextureCoordsTransformedStones[] = {0.0f, 0.0f,
            1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,};

    public String datName;
    public String fileName;
//    public Buffer videoQuadBuffer;
    public MediaItem mediaItem;

    public boolean isTracking;

    public long mLostTrackingSince;



    public RecognizeItem() {
//        videoQuadBuffer = fillBuffer(videoQuadTextureCoordsTransformedStones);
        mLostTrackingSince = -1;
    }




}
