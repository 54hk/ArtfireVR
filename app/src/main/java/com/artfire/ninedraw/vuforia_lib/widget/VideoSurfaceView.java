package com.artfire.ninedraw.vuforia_lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@SuppressLint("ViewConstructor")
public class VideoSurfaceView extends GLSurfaceView {

    String TAG = "VideoSurfaceView";

    public VideoRender mRenderer;
    private MediaPlayer mMediaPlayer = null;
    public android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    Surface surface = (Surface) msg.obj;
                    mMediaPlayer.setSurface(surface);

//                    mMediaPlayer.setScreenOnWhilePlaying(true);
                    break;
            }
        }
    };

    public VideoSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mRenderer = new VideoRender(context);
        mRenderer.setHandler(handler);
        setRenderer(mRenderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        mRenderer.setVideoSurfaceView(this);



    }
    public void prepareVideo(String path){
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.setDataSource(path);
        } catch (Exception e) {
            Log.e("OpenglVideo", e.getMessage(), e);
        }
        try {
            mMediaPlayer.prepare();
        } catch (IOException t) {
            Log.e(TAG, "media player prepare failed");
        }


    }


    public void startPlay(int position) {
        mMediaPlayer.start();
        mMediaPlayer.seekTo(position);
    }

    public int stopPlay() {

        int position = -1;
        if (mMediaPlayer.isPlaying()) {
            position = getPosition();
//            mMediaPlayer.pause();

            mMediaPlayer.pause();
        }
        return position;
    }


    public void releaseMedia() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();
    }

    public int getPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    public boolean isPlaying(){
        return mMediaPlayer.isPlaying();
    }
/*
    public int getPosition() {
        queueEvent(new Runnable() {
            public void run() {
                mRenderer.getPosition();
            }
        });
    }*/


    /*@Override
    public void onResume() {
        queueEvent(new Runnable() {
            public void run() {
                mRenderer.setMediaPlayer(mMediaPlayer);
            }
        });

        super.onResume();
    }
*/
    private static class VideoRender
            implements Renderer, SurfaceTexture.OnFrameAvailableListener {
        private static String TAG = "VideoRender";

        private static final int FLOAT_SIZE_BYTES = 4;
        private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 5 * FLOAT_SIZE_BYTES;
        private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
        private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
        private final float[] mTriangleVerticesData = {
                // X, Y, Z, U, V
                -1.0f, -1.0f, 0, 0.f, 0.f,
                1.0f, -1.0f, 0, 1.f, 0.f,
                -1.0f, 1.0f, 0, 0.f, 1.f,
                1.0f, 1.0f, 0, 1.f, 1.f,
        };

        private FloatBuffer mTriangleVertices;

        private final String mVertexShader =
                "uniform mat4 uMVPMatrix;\n" +
                        "uniform mat4 uSTMatrix;\n" +
                        "attribute vec4 aPosition;\n" +
                        "attribute vec4 aTextureCoord;\n" +
                        "varying vec2 vTextureCoord;\n" +
                        "void main() {\n" +
                        "  gl_Position = uMVPMatrix * aPosition;\n" +
                        "  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n" +
                        "}\n";

        private final String mFragmentShader =
                "#extension GL_OES_EGL_image_external : require\n" +
                        "precision mediump float;\n" +
                        "varying vec2 vTextureCoord;\n" +
                        "uniform samplerExternalOES sTexture;\n" +
                        "void main() {\n" +
                        "  gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
                        "  gl_FragColor.a = 0.11;\n" +
                        "}\n";

        private float[] mMVPMatrix = new float[16];
        private float[] mSTMatrix = new float[16];

        private int mProgram;
        private int mTextureID;
        private int muMVPMatrixHandle;
        private int muSTMatrixHandle;
        private int maPositionHandle;
        private int maTextureHandle;

        private SurfaceTexture mSurface;

        private static int GL_TEXTURE_EXTERNAL_OES = 0x8D65;

        android.os.Handler handler;

        VideoSurfaceView videoSurfaceView;

        public VideoRender(Context context) {
            mTriangleVertices = ByteBuffer.allocateDirect(
                    mTriangleVerticesData.length * FLOAT_SIZE_BYTES)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
            mTriangleVertices.put(mTriangleVerticesData).position(0);

            Matrix.setIdentityM(mSTMatrix, 0);
        }

        public void setHandler(android.os.Handler handler) {
            this.handler = handler;
        }

        public void setVideoSurfaceView(VideoSurfaceView videoSurfaceView) {
            this.videoSurfaceView = videoSurfaceView;
        }

        @Override
        public void onDrawFrame(GL10 glUnused) {
            if(mSurface==null){
                return;
            }
            mSurface.updateTexImage();
            mSurface.getTransformMatrix(mSTMatrix);

            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

            GLES20.glUseProgram(mProgram);
            checkGlError("glUseProgram");

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, mTextureID);

            mTriangleVertices.position(TRIANGLE_VERTICES_DATA_POS_OFFSET);
            GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false,
                    TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
            checkGlError("glVertexAttribPointer maPosition");
            GLES20.glEnableVertexAttribArray(maPositionHandle);
            checkGlError("glEnableVertexAttribArray maPositionHandle");

            mTriangleVertices.position(TRIANGLE_VERTICES_DATA_UV_OFFSET);
            GLES20.glVertexAttribPointer(maTextureHandle, 3, GLES20.GL_FLOAT, false,
                    TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
            checkGlError("glVertexAttribPointer maTextureHandle");
            GLES20.glEnableVertexAttribArray(maTextureHandle);
            checkGlError("glEnableVertexAttribArray maTextureHandle");

            Matrix.setIdentityM(mMVPMatrix, 0);
            GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mMVPMatrix, 0);
            GLES20.glUniformMatrix4fv(muSTMatrixHandle, 1, false, mSTMatrix, 0);

            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
            checkGlError("glDrawArrays");
            GLES20.glFinish();

        }

        @Override
        public void onSurfaceChanged(GL10 glUnused, int width, int height) {

        }


        @Override
        public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
            mProgram = createProgram(mVertexShader, mFragmentShader);
            if (mProgram == 0) {
                return;
            }
            maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
            checkGlError("glGetAttribLocation aPosition");
            if (maPositionHandle == -1) {
                throw new RuntimeException("Could not get attrib location for aPosition");
            }
            maTextureHandle = GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
            checkGlError("glGetAttribLocation aTextureCoord");
            if (maTextureHandle == -1) {
                throw new RuntimeException("Could not get attrib location for aTextureCoord");
            }

            muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
            checkGlError("glGetUniformLocation uMVPMatrix");
            if (muMVPMatrixHandle == -1) {
                throw new RuntimeException("Could not get attrib location for uMVPMatrix");
            }

            muSTMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uSTMatrix");
            checkGlError("glGetUniformLocation uSTMatrix");
            if (muSTMatrixHandle == -1) {
                throw new RuntimeException("Could not get attrib location for uSTMatrix");
            }


            int[] textures = new int[1];
            GLES20.glGenTextures(1, textures, 0);

            mTextureID = textures[0];
            GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, mTextureID);
            checkGlError("glBindTexture mTextureID");

            GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER,
                    GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER,
                    GLES20.GL_LINEAR);
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            /*
             * Create the SurfaceTexture that will feed this textureID,
             * and pass it to the MediaPlayer
             */
            mSurface = new SurfaceTexture(mTextureID);
            mSurface.setOnFrameAvailableListener(this);
            Surface surface = new Surface(mSurface);
            Message msg = Message.obtain(handler, 1, surface);

            msg.sendToTarget();

            /*mMediaPlayer.setSurface(surface);
            mMediaPlayer.setScreenOnWhilePlaying(true);*/
//            surface.release();
            /* */

          /*  try {
                mMediaPlayer.prepare();
            } catch (IOException t) {
                Log.e(TAG, "media player prepare failed");
            }

            synchronized(this) {
                updateSurface = false;
            }
            
            mMediaPlayer.start();*/
        }

        public void onFrameAvailable(SurfaceTexture surface) {
            videoSurfaceView.requestRender();
    /*        mSurface.updateTexImage();
            mSurface.getTransformMatrix(mSTMatrix);*/
        }

        private int loadShader(int shaderType, String source) {
            int shader = GLES20.glCreateShader(shaderType);
            if (shader != 0) {
                GLES20.glShaderSource(shader, source);
                GLES20.glCompileShader(shader);
                int[] compiled = new int[1];
                GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
                if (compiled[0] == 0) {
                    Log.e(TAG, "Could not compile shader " + shaderType + ":");
                    Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
                    GLES20.glDeleteShader(shader);
                    shader = 0;
                }
            }
            return shader;
        }

        private int createProgram(String vertexSource, String fragmentSource) {
            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
            if (vertexShader == 0) {
                return 0;
            }
            int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
            if (pixelShader == 0) {
                return 0;
            }

            int program = GLES20.glCreateProgram();
            if (program != 0) {
                GLES20.glAttachShader(program, vertexShader);
                checkGlError("glAttachShader");
                GLES20.glAttachShader(program, pixelShader);
                checkGlError("glAttachShader");
                GLES20.glLinkProgram(program);
                int[] linkStatus = new int[1];
                GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
                if (linkStatus[0] != GLES20.GL_TRUE) {
                    Log.e(TAG, "Could not link program: ");
                    Log.e(TAG, GLES20.glGetProgramInfoLog(program));
                    GLES20.glDeleteProgram(program);
                    program = 0;
                }
            }
            return program;
        }

        private void checkGlError(String op) {
            int error;
            while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
                Log.e(TAG, op + ": glError " + error);
                throw new RuntimeException(op + ": glError " + error);
            }
        }


    }  // End of class VideoRender.

}  // End of class VideoSurfaceView.
