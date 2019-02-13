/*===============================================================================
Copyright (c) 2016 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package com.artfire.ninedraw.vuforia_lib.vuforiaDemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.artfire.ninedraw.vuforia_lib.MainActivity;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleAppRenderer;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleAppRendererControl;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.SampleApplicationSession;
import com.artfire.ninedraw.vuforia_lib.SampleApplication.utils.SampleUtils;
import com.artfire.ninedraw.vuforia_lib.bean.RecognizeItem;
import com.vuforia.Device;
import com.vuforia.ImageTarget;
import com.vuforia.Renderer;
import com.vuforia.State;
import com.vuforia.Tool;
import com.vuforia.TrackableResult;
import com.vuforia.Vuforia;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


// The renderer class for the VideoPlayback sample.
public class VideoPlaybackRenderer implements GLSurfaceView.Renderer, SampleAppRendererControl {
    private static final String LOGTAG = "VideoPlaybackRenderer";

    SampleApplicationSession vuforiaAppSession;
    SampleAppRenderer mSampleAppRenderer;

    // Video Playback Rendering Specific
    private int videoPlaybackShaderID = 0;
    private int videoPlaybackVertexHandle = 0;
    private int videoPlaybackTexCoordHandle = 0;
    private int videoPlaybackMVPMatrixHandle = 0;
    private int videoPlaybackTexSamplerOESHandle = 0;

    // Video Playback Textures for the two targets
//    public int[] videoPlaybackTextureID = new int[MainActivity.NUM_TARGETS];

    // Keyframe and icon rendering specific
//    private int keyframeShaderID = 0;
 /*   private int keyframeVertexHandle = 0;
    private int keyframeTexCoordHandle = 0;
    private int keyframeMVPMatrixHandle = 0;
    private int keyframeTexSampler2DHandle = 0;
*/
    // We cannot use the default texture coordinates of the quad since these
    // will change depending on the video itself
    private float videoQuadTextureCoords[] = {0.0f, 0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 1.0f,};

    // This variable will hold the transformed coordinates (changes every frame)
//    private float videoQuadTextureCoordsTransformedStones[] = {0.0f, 0.0f,
//            1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,};

//    private float videoQuadTextureCoordsTransformedChips[] = {0.0f, 0.0f,
//            1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,};

    // Trackable dimensions
//    public Vec3F[] targetPositiveDimensions = new Vec3F[MainActivity.NUM_TARGETS];

    static int NUM_QUAD_VERTEX = 4;
    static int NUM_QUAD_INDEX = 6;

    double quadVerticesArray[] = {-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, -1.0f, 1.0f, 0.0f};

    double quadTexCoordsArray[] = {0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f,
            1.0f};

    double quadNormalsArray[] = {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,};

    short quadIndicesArray[] = {0, 1, 2, 2, 3, 0};

    Buffer quadVertices, quadTexCoords, quadIndices, quadNormals;

    private boolean mIsActive = false;
//    private Matrix44F tappingProjectionMatrix = null;

//    private float[][] mTexCoordTransformationMatrix = null;
//    private VideoPlayerHelper mVideoPlayerHelper[] = null;
//    private String mMovieName[] = null;
//    private VideoPlayerHelper.MEDIA_TYPE mCanRequestType[] = null;
//    private int mSeekPosition[] = null;
//    private boolean mShouldPlayImmediately[] = null;
//    private long mLostTrackingSince[] = null;
//    private boolean mLoadRequested[] = null;

    Activity mActivity;

    // Needed to calculate whether a screen tap is inside the target
//    Matrix44F modelViewMatrix[] = new Matrix44F[MainActivity.NUM_TARGETS];

//    private Vector<Texture> mTextures;

//    boolean isTracking[] = new boolean[MainActivity.NUM_TARGETS];
//    VideoPlayerHelper.MEDIA_STATE currentStatus[] = new VideoPlayerHelper.MEDIA_STATE[MainActivity.NUM_TARGETS];

    // These hold the aspect ratio of both the video and the
    // keyframe
//    float videoQuadAspectRatio[] = new float[MainActivity.NUM_TARGETS];
//    float keyframeQuadAspectRatio[] = new float[MainActivity.NUM_TARGETS];

    List<RecognizeItem> recognizeItems;

    public VideoPlaybackRenderer(Activity activity,
                                 SampleApplicationSession session, List<RecognizeItem> recognizeItems) {
        this.recognizeItems = recognizeItems;
        mActivity = activity;
        vuforiaAppSession = session;

        // SampleAppRenderer used to encapsulate the use of RenderingPrimitives setting
        // the device mode AR/VR and stereo mode
        mSampleAppRenderer = new SampleAppRenderer(this, mActivity, Device.MODE.MODE_AR, false, 0.01f, 5f);

        // Create an array of the size of the number of targets we have
    /*    mVideoPlayerHelper = new VideoPlayerHelper[MainActivity.NUM_TARGETS];
        mMovieName = new String[MainActivity.NUM_TARGETS];
        mCanRequestType = new VideoPlayerHelper.MEDIA_TYPE[MainActivity.NUM_TARGETS];
        mSeekPosition = new int[MainActivity.NUM_TARGETS];
        mShouldPlayImmediately = new boolean[MainActivity.NUM_TARGETS];
        mLostTrackingSince = new long[MainActivity.NUM_TARGETS];
        mLoadRequested = new boolean[MainActivity.NUM_TARGETS];*/
//        mTexCoordTransformationMatrix = new float[MainActivity.NUM_TARGETS][16];

        // Initialize the arrays to default values
/*        for (int i = 0; i < MainActivity.NUM_TARGETS; i++)
        {
            mVideoPlayerHelper[i] = null;
            mMovieName[i] = "";
            mCanRequestType[i] = VideoPlayerHelper.MEDIA_TYPE.ON_TEXTURE_FULLSCREEN;
            mSeekPosition[i] = 0;
            mShouldPlayImmediately[i] = false;
            mLostTrackingSince[i] = -1;
            mLoadRequested[i] = false;
        }*/
        
      /*  for (int i = 0; i < MainActivity.NUM_TARGETS; i++)
            targetPositiveDimensions[i] = new Vec3F();*/
        
      /*  for (int i = 0; i < MainActivity.NUM_TARGETS; i++)
            modelViewMatrix[i] = new Matrix44F();*/
    }


    // Store the Player Helper object passed from the main activity
  /*  public void setVideoPlayerHelper(int target,
        VideoPlayerHelper newVideoPlayerHelper)
    {
        mVideoPlayerHelper[target] = newVideoPlayerHelper;
    }*/


    public void requestLoad(int target, String movieName, int seekPosition,
                            boolean playImmediately) {
       /* mMovieName[target] = movieName;
        mSeekPosition[target] = seekPosition;
        mShouldPlayImmediately[target] = playImmediately;
        mLoadRequested[target] = true;*/
    }

    public void requestLoad(RecognizeItem recognizeItem) {
     /*   mMovieName[target] = movieName;
        mSeekPosition[target] = seekPosition;
        mShouldPlayImmediately[target] = playImmediately;
        mLoadRequested[target] = true;*/

        recognizeItem.mediaItem.mLoadRequested = true;

    }


    // Called when the surface is created or recreated.
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Call function to initialize rendering:
        // The video texture is also created on this step
        initRendering();

        // Call Vuforia function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        Vuforia.onSurfaceCreated();

        mSampleAppRenderer.onSurfaceCreated();

        for (RecognizeItem recognizeItem : recognizeItems) {

            if (recognizeItem.mediaItem.mVideoPlayerHelper != null) {
                // The VideoPlayerHelper needs to setup a surface texture given
                // the texture id
                // Here we inform the video player that we would like to play
                // the movie
                // both on texture and on full screen
                // Notice that this does not mean that the platform will be able
                // to do what we request
                // After the file has been loaded one must always check with
                // isPlayableOnTexture() whether
                // this can be played embedded in the AR scene
                if (!recognizeItem.mediaItem.mVideoPlayerHelper.setupSurfaceTexture(recognizeItem.mediaItem.videoPlaybackTextureID[0]))
                    recognizeItem.mediaItem.mCanRequestType = VideoPlayerHelper.MEDIA_TYPE.FULLSCREEN;
                else
                    recognizeItem.mediaItem.mCanRequestType = VideoPlayerHelper.MEDIA_TYPE.ON_TEXTURE_FULLSCREEN;

                // And now check if a load has been requested with the
                // parameters passed from the main activity
                if (recognizeItem.mediaItem.mLoadRequested) {
                    recognizeItem.mediaItem.mVideoPlayerHelper.load(recognizeItem.mediaItem.mMovieName,
                            recognizeItem.mediaItem.mCanRequestType, recognizeItem.mediaItem.mShouldPlayImmediately,
                            recognizeItem.mediaItem.mSeekPosition);
                    recognizeItem.mediaItem.mLoadRequested = false;
                }
            }
        }
    }


    // Called when the surface changed size.
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Call Vuforia function to handle render surface size changes:
        Vuforia.onSurfaceChanged(width, height);

        // RenderingPrimitives to be updated when some rendering change is done
        mSampleAppRenderer.onConfigurationChanged(mIsActive);

        // Upon every on pause the movie had to be unloaded to release resources
        // Thus, upon every surface create or surface change this has to be
        // reloaded
        // See:
        // http://developer.android.com/reference/android/media/MediaPlayer.html#release()
        for (RecognizeItem recognizeItem : recognizeItems) {
            if (recognizeItem.mediaItem.mLoadRequested && recognizeItem.mediaItem.mVideoPlayerHelper != null) {
                recognizeItem.mediaItem.mVideoPlayerHelper.load(recognizeItem.mediaItem.mMovieName,
                        recognizeItem.mediaItem.mCanRequestType, recognizeItem.mediaItem.mShouldPlayImmediately,
                        recognizeItem.mediaItem.mSeekPosition);
                recognizeItem.mediaItem.mLoadRequested = false;
            }
        }
    }


    // Called to draw the current frame.
    public void onDrawFrame(GL10 gl) {
        if (!mIsActive)
            return;

        for (RecognizeItem recognizeItem : recognizeItems) {

            if (recognizeItem.mediaItem.mVideoPlayerHelper != null) {
                if (recognizeItem.mediaItem.mVideoPlayerHelper.isPlayableOnTexture()) {
                    // First we need to update the video data. This is a built
                    // in Android call
                    // Here, the decoded data is uploaded to the OES texture
                    // We only need to do this if the movie is playing
                    if (recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.PLAYING) {
                        recognizeItem.mediaItem.mVideoPlayerHelper.updateVideoData();
                    }

                    // According to the Android API
                    // (http://developer.android.com/reference/android/graphics/SurfaceTexture.html)
                    // transforming the texture coordinates needs to happen
                    // every frame.
                    recognizeItem.mediaItem.mVideoPlayerHelper
                            .getSurfaceTextureTransformMatrix(recognizeItem.mediaItem.mTexCoordTransformationMatrix);
                    setVideoDimensions(recognizeItem,
                            recognizeItem.mediaItem.mVideoPlayerHelper.getVideoWidth(),
                            recognizeItem.mediaItem.mVideoPlayerHelper.getVideoHeight(),
                            recognizeItem.mediaItem.mTexCoordTransformationMatrix);
                }

                setStatus(recognizeItem, recognizeItem.mediaItem.mVideoPlayerHelper.getStatus().getNumericType());
            }
        }

        // Call our function to render content from SampleAppRenderer class
        mSampleAppRenderer.render();

        for (RecognizeItem recognizeItem : recognizeItems) {
            // Ask whether the target is currently being tracked and if so react
            // to it
            if (recognizeItem.isTracking) {
                // If it is tracking reset the timestamp for lost tracking
                recognizeItem.mLostTrackingSince = -1;

                //如果正在追踪且不是在播放则直接播放
                if ((recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.PAUSED)
                        || (recognizeItem.mediaItem.isFullScreen==true&&recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.NOT_READY)
                        || (recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.READY)
                        || (recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.STOPPED)
                        ) {
                    recognizeItem.mediaItem.mVideoPlayerHelper.play(recognizeItem.mediaItem.isFullScreen,
                            recognizeItem.mediaItem.mSeekPosition);
                } else if ((recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.REACHED_END)) {
                    recognizeItem.mediaItem.mVideoPlayerHelper.play(recognizeItem.mediaItem.isFullScreen,
                            0);
                }
            } else {
                // If it isn't tracking
                // check whether it just lost it or if it's been a while
                if (recognizeItem.mLostTrackingSince < 0)
                    recognizeItem.mLostTrackingSince = SystemClock.uptimeMillis();
                else {
                    if ((SystemClock.uptimeMillis() - recognizeItem.mLostTrackingSince) > 300) {
                        if (recognizeItem.mediaItem.mVideoPlayerHelper != null && recognizeItem.mediaItem.mVideoPlayerHelper.getStatus() == VideoPlayerHelper.MEDIA_STATE.PLAYING) {
                            recognizeItem.mediaItem.setmSeekPosition(recognizeItem.mediaItem.mVideoPlayerHelper.getCurrentPosition());
//                            LogUtil.d(LOGTAG, "render保存的值:" + recognizeItem.mediaItem.mVideoPlayerHelper.getCurrentPosition());
                            recognizeItem.mediaItem.mVideoPlayerHelper.pause();

                        }
                    }
                    // If it's been more than 300 毫 seconds then pause the player
                   /* if ((SystemClock.uptimeMillis() - recognizeItem.mLostTrackingSince) > 300) {
                        if (recognizeItem.mediaItem.mVideoPlayerHelper != null){
                            recognizeItem.mediaItem.setmSeekPosition(recognizeItem.mediaItem.mVideoPlayerHelper.getCurrentPosition());
                            recognizeItem.mediaItem.mVideoPlayerHelper.pause();

                        }
                    }*/
                }
            }
        }

        // If you would like the video to start playing as soon as it starts
        // tracking
        // and pause as soon as tracking is lost you can do that here by
        // commenting
        // the for-loop above and instead checking whether the isTracking()
        // value has
        // changed since the last frame. Notice that you need to be careful not
        // to
        // trigger automatic playback for fullscreen since that will be
        // inconvenient
        // for your users.

    }


    public void setActive(boolean active) {
        mIsActive = active;

        if (mIsActive)
            mSampleAppRenderer.configureVideoBackground();
    }


    @SuppressLint("InlinedApi")
    void initRendering() {
        Log.d(LOGTAG, "VideoPlayback VideoPlaybackRenderer initRendering");

        // Define clear color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, Vuforia.requiresAlpha() ? 0.0f
                : 1.0f);

        // Now generate the OpenGL texture objects and add settings
       /* for (Texture t : mTextures) {
            // Here we create the textures for the keyframe
            // and for all the icons
            GLES20.glGenTextures(1, t.mTextureID, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, t.mTextureID[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA,
                    t.mWidth, t.mHeight, 0, GLES20.GL_RGBA,
                    GLES20.GL_UNSIGNED_BYTE, t.mData);
        }*/

        // Now we create the texture for the video data from the movie
        // IMPORTANT:
        // Notice that the textures are not typical GL_TEXTURE_2D textures
        // but instead are GL_TEXTURE_EXTERNAL_OES extension textures
        // This is required by the Android SurfaceTexture
//        for (int i = 0; i < MainActivity.NUM_TARGETS; i++) {
        for (int i = 0; i < recognizeItems.size(); i++) {
            RecognizeItem recognizeItem = recognizeItems.get(i);
            GLES20.glGenTextures(1, recognizeItem.mediaItem.videoPlaybackTextureID, 0);
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                    recognizeItem.mediaItem.videoPlaybackTextureID[0]);
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                    GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                    GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);


        }

        // The first shader is the one that will display the video data of the
        // movie
        // (it is aware of the GL_TEXTURE_EXTERNAL_OES extension)
        videoPlaybackShaderID = SampleUtils.createProgramFromShaderSrc(
                VideoPlaybackShaders.VIDEO_PLAYBACK_VERTEX_SHADER,
                VideoPlaybackShaders.VIDEO_PLAYBACK_FRAGMENT_SHADER);
        videoPlaybackVertexHandle = GLES20.glGetAttribLocation(
                videoPlaybackShaderID, "vertexPosition");
        videoPlaybackTexCoordHandle = GLES20.glGetAttribLocation(
                videoPlaybackShaderID, "vertexTexCoord");
        videoPlaybackMVPMatrixHandle = GLES20.glGetUniformLocation(
                videoPlaybackShaderID, "modelViewProjectionMatrix");
        videoPlaybackTexSamplerOESHandle = GLES20.glGetUniformLocation(
                videoPlaybackShaderID, "texSamplerOES");


     /*   // This is a simpler shader with regular 2D textures
        keyframeShaderID = SampleUtils.createProgramFromShaderSrc(
                KeyFrameShaders.KEY_FRAME_VERTEX_SHADER,
                KeyFrameShaders.KEY_FRAME_FRAGMENT_SHADER);
        keyframeVertexHandle = GLES20.glGetAttribLocation(keyframeShaderID,
                "vertexPosition");
        keyframeTexCoordHandle = GLES20.glGetAttribLocation(keyframeShaderID,
                "vertexTexCoord");
        keyframeMVPMatrixHandle = GLES20.glGetUniformLocation(keyframeShaderID,
                "modelViewProjectionMatrix");
        keyframeTexSampler2DHandle = GLES20.glGetUniformLocation(
                keyframeShaderID, "texSampler2D");*/

      /*  keyframeQuadAspectRatio[MainActivity.STONES] = (float) mTextures
                .get(0).mHeight / (float) mTextures.get(0).mWidth;
        keyframeQuadAspectRatio[MainActivity.CHIPS] = (float) mTextures.get(1).mHeight
                / (float) mTextures.get(1).mWidth;*/

        quadVertices = fillBuffer(quadVerticesArray);
        quadTexCoords = fillBuffer(quadTexCoordsArray);
        quadIndices = fillBuffer(quadIndicesArray);
        quadNormals = fillBuffer(quadNormalsArray);

    }


    private Buffer fillBuffer(double[] array) {
        // Convert to floats because OpenGL doesnt work on doubles, and manually
        // casting each input value would take too much time.
        ByteBuffer bb = ByteBuffer.allocateDirect(4 * array.length); // each float takes 4 bytes
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (double d : array)
            bb.putFloat((float) d);
        bb.rewind();

        return bb;

    }


    private Buffer fillBuffer(short[] array) {
        ByteBuffer bb = ByteBuffer.allocateDirect(2 * array.length); // each
        // short
        // takes 2
        // bytes
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (short s : array)
            bb.putShort(s);
        bb.rewind();

        return bb;

    }


    private Buffer fillBuffer(float[] array) {
        // Convert to floats because OpenGL doesnt work on doubles, and manually
        // casting each input value would take too much time.
        ByteBuffer bb = ByteBuffer.allocateDirect(4 * array.length); // each float takes 4 bytes
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (float d : array)
            bb.putFloat(d);
        bb.rewind();

        return bb;

    }


    public void updateRenderingPrimitives() {
        mSampleAppRenderer.updateRenderingPrimitives();
    }


    @SuppressLint("InlinedApi")
    // The render function called from SampleAppRendering by using RenderingPrimitives views.
    // The state is owned by SampleAppRenderer which is controlling it's lifecycle.
    // State should not be cached outside this method.
    public void renderFrame(State state, float[] projectionMatrix) {
        // Renders video background replacing Renderer.DrawVideoBackground()

        if (MainActivity.isSHowPrew) {
            mSampleAppRenderer.renderVideoBackground();
        }

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        // We must detect if background reflection is active and adjust the
        // culling direction.
        // If the reflection is active, this means the post matrix has been
        // reflected as well,
        // therefore standard counter clockwise face culling will result in
        // "inside out" models.
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK);

      /*  if (tappingProjectionMatrix == null) {
            tappingProjectionMatrix = new Matrix44F();
            tappingProjectionMatrix.setData(projectionMatrix);
        }*/

        float temp[] = {0.0f, 0.0f, 0.0f};
        for (RecognizeItem recognizeItem : recognizeItems) {
            recognizeItem.isTracking = false;
            recognizeItem.mediaItem.targetPositiveDimensions.setData(temp);
        }

        // Did we find any trackables this frame?
        for (int tIdx = 0; tIdx < state.getNumTrackableResults(); tIdx++) {
            // Get the trackable:
            TrackableResult trackableResult = state.getTrackableResult(tIdx);

            ImageTarget imageTarget = (ImageTarget) trackableResult
                    .getTrackable();

            /*int currentTarget;

            // We store the modelview matrix to be used later by the tap
            // calculation
            if (imageTarget.getName().compareTo("stones") == 0)
                currentTarget = MainActivity.STONES;
            else
                currentTarget = MainActivity.CHIPS;*/

            RecognizeItem recognizeItem = findRecognizeByDatName(imageTarget.getName());
            if (recognizeItem == null) {
                continue;
            }

            recognizeItem.mediaItem.modelViewMatrix = Tool
                    .convertPose2GLMatrix(trackableResult.getPose());
            recognizeItem.isTracking = true;

//            modelViewMatrix[currentTarget] = Tool
//                    .convertPose2GLMatrix(trackableResult.getPose());

//            isTracking[currentTarget] = true;

            recognizeItem.mediaItem.targetPositiveDimensions = imageTarget.getSize();

            // The pose delivers the center of the target, thus the dimensions
            // go from -width/2 to width/2, same for height
            temp[0] = recognizeItem.mediaItem.targetPositiveDimensions.getData()[0] / 2.0f;
            temp[1] = recognizeItem.mediaItem.targetPositiveDimensions.getData()[1] / 2.0f;
            recognizeItem.mediaItem.targetPositiveDimensions.setData(temp);

            // If the movie is ready to start playing or it has reached the end
            // of playback we render the keyframe
           /* if ((currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.READY)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.REACHED_END)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.NOT_READY)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.ERROR))
            {
                float[] modelViewMatrixKeyframe = Tool.convertPose2GLMatrix(
                    trackableResult.getPose()).getData();
                float[] modelViewProjectionKeyframe = new float[16];
                // Matrix.translateM(modelViewMatrixKeyframe, 0, 0.0f, 0.0f,
                // targetPositiveDimensions[currentTarget].getData()[0]);

                // Here we use the aspect ratio of the keyframe since it
                // is likely that it is not a perfect square

                float ratio = 1.0f;
                if (mTextures.get(currentTarget).mSuccess)
                    ratio = keyframeQuadAspectRatio[currentTarget];
                else
                    ratio = targetPositiveDimensions[currentTarget].getData()[1]
                        / targetPositiveDimensions[currentTarget].getData()[0];

                Matrix.scaleM(modelViewMatrixKeyframe, 0,
                    targetPositiveDimensions[currentTarget].getData()[0],
                    targetPositiveDimensions[currentTarget].getData()[0]
                        * ratio,
                    targetPositiveDimensions[currentTarget].getData()[0]);
                Matrix.multiplyMM(modelViewProjectionKeyframe, 0,
                        projectionMatrix, 0, modelViewMatrixKeyframe, 0);

                GLES20.glUseProgram(keyframeShaderID);

                // Prepare for rendering the keyframe
                GLES20.glVertexAttribPointer(keyframeVertexHandle, 3,
                    GLES20.GL_FLOAT, false, 0, quadVertices);
                GLES20.glVertexAttribPointer(keyframeTexCoordHandle, 2,
                    GLES20.GL_FLOAT, false, 0, quadTexCoords);

                GLES20.glEnableVertexAttribArray(keyframeVertexHandle);
                GLES20.glEnableVertexAttribArray(keyframeTexCoordHandle);

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

                // The first loaded texture from the assets folder is the
                // keyframe
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                    mTextures.get(currentTarget).mTextureID[0]);
                GLES20.glUniformMatrix4fv(keyframeMVPMatrixHandle, 1, false,
                    modelViewProjectionKeyframe, 0);
                GLES20.glUniform1i(keyframeTexSampler2DHandle, 0);

                // Render
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, NUM_QUAD_INDEX,
                    GLES20.GL_UNSIGNED_SHORT, quadIndices);

                GLES20.glDisableVertexAttribArray(keyframeVertexHandle);
                GLES20.glDisableVertexAttribArray(keyframeTexCoordHandle);

                GLES20.glUseProgram(0);
            } else
            // In any other case, such as playing or paused, we render
            // the actual contents
            {*/
            float[] modelViewMatrixVideo = Tool.convertPose2GLMatrix(
                    trackableResult.getPose()).getData();
            float[] modelViewProjectionVideo = new float[16];
            // Matrix.translateM(modelViewMatrixVideo, 0, 0.0f, 0.0f,
            // targetPositiveDimensions[currentTarget].getData()[0]);

            // Here we use the aspect ratio of the video frame
            Matrix.scaleM(modelViewMatrixVideo, 0,
                    recognizeItem.mediaItem.targetPositiveDimensions.getData()[0],
                    recognizeItem.mediaItem.targetPositiveDimensions.getData()[0]
                            * recognizeItem.mediaItem.videoQuadAspectRatio,
                    recognizeItem.mediaItem.targetPositiveDimensions.getData()[0]);
            Matrix.multiplyMM(modelViewProjectionVideo, 0,
                    projectionMatrix, 0, modelViewMatrixVideo, 0);

            GLES20.glUseProgram(videoPlaybackShaderID);

            // Prepare for rendering the keyframe
            GLES20.glVertexAttribPointer(videoPlaybackVertexHandle, 3,
                    GLES20.GL_FLOAT, false, 0, quadVertices);

            GLES20.glVertexAttribPointer(videoPlaybackTexCoordHandle,
                    2, GLES20.GL_FLOAT, false, 0,
                    fillBuffer(recognizeItem.videoQuadTextureCoordsTransformedStones));

          /*  if (imageTarget.getName().compareTo("stones") == 0)
                GLES20.glVertexAttribPointer(videoPlaybackTexCoordHandle,
                        2, GLES20.GL_FLOAT, false, 0,
                        fillBuffer(videoQuadTextureCoordsTransformedStones));
            else
                GLES20.glVertexAttribPointer(videoPlaybackTexCoordHandle,
                        2, GLES20.GL_FLOAT, false, 0,
                        fillBuffer(videoQuadTextureCoordsTransformedChips));*/

            GLES20.glEnableVertexAttribArray(videoPlaybackVertexHandle);
            GLES20.glEnableVertexAttribArray(videoPlaybackTexCoordHandle);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

            // IMPORTANT:
            // Notice here that the texture that we are binding is not the
            // typical GL_TEXTURE_2D but instead the GL_TEXTURE_EXTERNAL_OES
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, recognizeItem.mediaItem.videoPlaybackTextureID[0]);
            GLES20.glUniformMatrix4fv(videoPlaybackMVPMatrixHandle, 1,
                    false, modelViewProjectionVideo, 0);
            GLES20.glUniform1i(videoPlaybackTexSamplerOESHandle, 0);

            // Render
            GLES20.glDrawElements(GLES20.GL_TRIANGLES, NUM_QUAD_INDEX,
                    GLES20.GL_UNSIGNED_SHORT, quadIndices);

            GLES20.glDisableVertexAttribArray(videoPlaybackVertexHandle);
            GLES20.glDisableVertexAttribArray(videoPlaybackTexCoordHandle);

            GLES20.glUseProgram(0);

//            }

            // The following section renders the icons. The actual textures used
            // are loaded from the assets folder
            
            /*if ((currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.READY)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.REACHED_END)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.PAUSED)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.NOT_READY)
                || (currentStatus[currentTarget] == VideoPlayerHelper.MEDIA_STATE.ERROR))
            {
                // If the movie is ready to be played, pause, has reached end or
                // is not
                // ready then we display one of the icons
                float[] modelViewMatrixButton = Tool.convertPose2GLMatrix(
                    trackableResult.getPose()).getData();
                float[] modelViewProjectionButton = new float[16];

                GLES20.glDepthFunc(GLES20.GL_LEQUAL);

                GLES20.glEnable(GLES20.GL_BLEND);
                GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA,
                    GLES20.GL_ONE_MINUS_SRC_ALPHA);

                // The inacuracy of the rendering process in some devices means
                // that
                // even if we use the "Less or Equal" version of the depth
                // function
                // it is likely that we will get ugly artifacts
                // That is the translation in the Z direction is slightly
                // different
                // Another posibility would be to use a depth func "ALWAYS" but
                // that is typically not a good idea
                Matrix
                    .translateM(
                        modelViewMatrixButton,
                        0,
                        0.0f,
                        0.0f,
                        targetPositiveDimensions[currentTarget].getData()[1] / 10.98f);
                Matrix
                    .scaleM(
                        modelViewMatrixButton,
                        0,
                        (targetPositiveDimensions[currentTarget].getData()[1] / 2.0f),
                        (targetPositiveDimensions[currentTarget].getData()[1] / 2.0f),
                        (targetPositiveDimensions[currentTarget].getData()[1] / 2.0f));
                Matrix.multiplyMM(modelViewProjectionButton, 0,
                        projectionMatrix, 0, modelViewMatrixButton, 0);

                GLES20.glUseProgram(keyframeShaderID);

                GLES20.glVertexAttribPointer(keyframeVertexHandle, 3,
                    GLES20.GL_FLOAT, false, 0, quadVertices);
                GLES20.glVertexAttribPointer(keyframeTexCoordHandle, 2,
                    GLES20.GL_FLOAT, false, 0, quadTexCoords);

                GLES20.glEnableVertexAttribArray(keyframeVertexHandle);
                GLES20.glEnableVertexAttribArray(keyframeTexCoordHandle);

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

                // Depending on the status in which we are we choose the
                // appropriate
                // texture to display. Notice that unlike the video these are
                // regular
                // GL_TEXTURE_2D textures
                switch (currentStatus[currentTarget])
                {
                    case READY:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(2).mTextureID[0]);
                        break;
                    case REACHED_END:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(2).mTextureID[0]);
                        break;
                    case PAUSED:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(2).mTextureID[0]);
                        break;
                    case NOT_READY:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(3).mTextureID[0]);
                        break;
                    case ERROR:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(4).mTextureID[0]);
                        break;
                    default:
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,
                            mTextures.get(3).mTextureID[0]);
                        break;
                }
                GLES20.glUniformMatrix4fv(keyframeMVPMatrixHandle, 1, false,
                    modelViewProjectionButton, 0);
                GLES20.glUniform1i(keyframeTexSampler2DHandle, 0);

                // Render
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, NUM_QUAD_INDEX,
                    GLES20.GL_UNSIGNED_SHORT, quadIndices);

                GLES20.glDisableVertexAttribArray(keyframeVertexHandle);
                GLES20.glDisableVertexAttribArray(keyframeTexCoordHandle);

                GLES20.glUseProgram(0);

                // Finally we return the depth func to its original state
                GLES20.glDepthFunc(GLES20.GL_LESS);
                GLES20.glDisable(GLES20.GL_BLEND);
            }*/

            SampleUtils.checkGLError("VideoPlayback renderFrame");
        }

        GLES20.glDisable(GLES20.GL_DEPTH_TEST);

        Renderer.getInstance().end();

    }


    private RecognizeItem findRecognizeByDatName(String datName) {
        if (recognizeItems == null || datName == null) {
            return null;
        }
        for (RecognizeItem recognizeItem : recognizeItems) {
            if (datName.equals(recognizeItem.datName)) {
                return recognizeItem;
            }
        }
        return null;
    }


  /*  boolean isTapOnScreenInsideTarget(int target, float x, float y) {
        // Here we calculate that the touch event is inside the target
        Vec3F intersection;
        // Vec3F lineStart = new Vec3F();
        // Vec3F lineEnd = new Vec3F();

        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        intersection = SampleMath.getPointToPlaneIntersection(SampleMath
                        .Matrix44FInverse(tappingProjectionMatrix),
                modelViewMatrix[target], metrics.widthPixels, metrics.heightPixels,
                new Vec2F(x, y), new Vec3F(0, 0, 0), new Vec3F(0, 0, 1));

        // The target returns as pose the center of the trackable. The following
        // if-statement simply checks that the tap is within this range
        if ((intersection.getData()[0] >= -(targetPositiveDimensions[target]
                .getData()[0]))
                && (intersection.getData()[0] <= (targetPositiveDimensions[target]
                .getData()[0]))
                && (intersection.getData()[1] >= -(targetPositiveDimensions[target]
                .getData()[1]))
                && (intersection.getData()[1] <= (targetPositiveDimensions[target]
                .getData()[1])))
            return true;
        else
            return false;
    }*/


    void setVideoDimensions(RecognizeItem recognizeItem, float videoWidth, float videoHeight,
                            float[] textureCoordMatrix) {
        // The quad originaly comes as a perfect square, however, the video
        // often has a different aspect ration such as 4:3 or 16:9,
        // To mitigate this we have two options:
        // 1) We can either scale the width (typically up)
        // 2) We can scale the height (typically down)
        // Which one to use is just a matter of preference. This example scales
        // the height down.
        // (see the render call in renderFrame)
        recognizeItem.mediaItem.videoQuadAspectRatio = videoHeight / videoWidth;

        float mtx[] = textureCoordMatrix;
        float tempUVMultRes[] = new float[2];

        tempUVMultRes = uvMultMat4f(
                recognizeItem.videoQuadTextureCoordsTransformedStones[0],
                recognizeItem.videoQuadTextureCoordsTransformedStones[1],
                videoQuadTextureCoords[0], videoQuadTextureCoords[1], mtx);
        recognizeItem.videoQuadTextureCoordsTransformedStones[0] = tempUVMultRes[0];
        recognizeItem.videoQuadTextureCoordsTransformedStones[1] = tempUVMultRes[1];
        tempUVMultRes = uvMultMat4f(
                recognizeItem.videoQuadTextureCoordsTransformedStones[2],
                recognizeItem.videoQuadTextureCoordsTransformedStones[3],
                videoQuadTextureCoords[2], videoQuadTextureCoords[3], mtx);
        recognizeItem.videoQuadTextureCoordsTransformedStones[2] = tempUVMultRes[0];
        recognizeItem.videoQuadTextureCoordsTransformedStones[3] = tempUVMultRes[1];
        tempUVMultRes = uvMultMat4f(
                recognizeItem.videoQuadTextureCoordsTransformedStones[4],
                recognizeItem.videoQuadTextureCoordsTransformedStones[5],
                videoQuadTextureCoords[4], videoQuadTextureCoords[5], mtx);
        recognizeItem.videoQuadTextureCoordsTransformedStones[4] = tempUVMultRes[0];
        recognizeItem.videoQuadTextureCoordsTransformedStones[5] = tempUVMultRes[1];
        tempUVMultRes = uvMultMat4f(
                recognizeItem.videoQuadTextureCoordsTransformedStones[6],
                recognizeItem.videoQuadTextureCoordsTransformedStones[7],
                videoQuadTextureCoords[6], videoQuadTextureCoords[7], mtx);
        recognizeItem.videoQuadTextureCoordsTransformedStones[6] = tempUVMultRes[0];
        recognizeItem.videoQuadTextureCoordsTransformedStones[7] = tempUVMultRes[1];

        // textureCoordMatrix = mtx;
    }


    // Multiply the UV coordinates by the given transformation matrix
    float[] uvMultMat4f(float transformedU, float transformedV, float u,
                        float v, float[] pMat) {
        float x = pMat[0] * u + pMat[4] * v /* + pMat[ 8]*0.f */ + pMat[12]
                * 1.f;
        float y = pMat[1] * u + pMat[5] * v /* + pMat[ 9]*0.f */ + pMat[13]
                * 1.f;
        // float z = pMat[2]*u + pMat[6]*v + pMat[10]*0.f + pMat[14]*1.f; // We
        // dont need z and w so we comment them out
        // float w = pMat[3]*u + pMat[7]*v + pMat[11]*0.f + pMat[15]*1.f;

        float result[] = new float[2];
        // transformedU = x;
        // transformedV = y;
        result[0] = x;
        result[1] = y;
        return result;
    }


    void setStatus(RecognizeItem recognizeItem, int value) {
        // Transform the value passed from java to our own values
        switch (value) {
            case 0:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.REACHED_END;
                break;
            case 1:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.PAUSED;
                break;
            case 2:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.STOPPED;
                break;
            case 3:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.PLAYING;
                break;
            case 4:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.READY;
                break;
            case 5:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.NOT_READY;
                break;
            case 6:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.ERROR;
                break;
            default:
                recognizeItem.mediaItem.currentStatus = VideoPlayerHelper.MEDIA_STATE.NOT_READY;
                break;
        }
    }


  /*  public void setTextures(Vector<Texture> textures) {
        mTextures = textures;
    }*/

}