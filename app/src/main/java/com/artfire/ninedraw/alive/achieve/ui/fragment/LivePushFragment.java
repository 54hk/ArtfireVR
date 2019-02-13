package com.artfire.ninedraw.alive.achieve.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alivc.live.pusher.AlivcLivePushBGMListener;
import com.alivc.live.pusher.AlivcLivePushError;
import com.alivc.live.pusher.AlivcLivePushErrorListener;
import com.alivc.live.pusher.AlivcLivePushInfoListener;
import com.alivc.live.pusher.AlivcLivePushNetworkListener;
import com.alivc.live.pusher.AlivcLivePusher;
import com.aliyun.vod.common.utils.ToastUtil;
import com.artfire.ninedraw.R;
import com.artfire.ninedraw.alive.achieve.listener.DialogVisibleListener;
import com.artfire.ninedraw.alive.achieve.ui.activity.LivePushActivity;
import com.artfire.ninedraw.alive.achieve.ui.myview.PushBeautyDialog;
import com.artfire.ninedraw.alive.achieve.ui.myview.PushMoreDialog;
import com.artfire.ninedraw.alive.achieve.utils.NetWorkUtils;
import com.artfire.ninedraw.alive.achieve.utils.SharedPreferenceUtils;
import com.artfire.ninedraw.alive.core.module.BeautyParams;
import com.artfire.ninedraw.alive.core.utils.AnimUitls;
import com.artfire.ninedraw.artfire_code.Bean.ALiHostoryBean;
import com.artfire.ninedraw.artfire_code.Bean.AliBeginBean;
import com.artfire.ninedraw.artfire_code.Bean.AliPlayPersonSum;
import com.artfire.ninedraw.artfire_code.adapter.AlivePlayAdapter;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.model.AliPayerModel;
import com.artfire.ninedraw.artfire_code.model.AliPayerModelImpl;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.utils.GlideUtil;
import com.artfire.ninedraw.artfire_code.utils.GsonUtils;
import com.artfire.ninedraw.artfire_code.utils.MyDialog;
import com.artfire.ninedraw.heartanim.HiPraiseAnimationView;
import com.artfire.ninedraw.heartanim.HiPraiseWithCallback;
import com.artfire.ninedraw.heartanim.IPraise;
import com.artfire.ninedraw.heartanim.OnDrawCallback;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;

import static com.alivc.live.pusher.AlivcLivePushCameraTypeEnum.CAMERA_TYPE_BACK;
import static com.alivc.live.pusher.AlivcLivePushCameraTypeEnum.CAMERA_TYPE_FRONT;

public class LivePushFragment extends Fragment implements Runnable, TIMMessageListener, ApiRequestCallback {
    public static final String TAG = "LivePushFragment";

    private static final String URL_KEY = "url_key";
    private static final String ASYNC_KEY = "async_key";
    private static final String AUDIO_ONLY_KEY = "audio_only_key";
    private static final String VIDEO_ONLY_KEY = "video_only_key";
    private static final String QUALITY_MODE_KEY = "quality_mode_key";
    private static final String CAMERA_ID = "camera_id";
    private static final String FLASH_ON = "flash_on";
    private static final String AUTH_TIME = "auth_time";
    private static final String PRIVACY_KEY = "privacy_key";
    private static final String MIX_EXTERN = "mix_extern";
    private static final String MIX_MAIN = "mix_main";
    public static final String CRID = "crid";
    private final long REFRESH_INTERVAL = 2000;

    private ImageView mExit;

    private ImageView mFlash;
    private ImageView mCamera;
    private ImageView mBeautyButton;
    private ImageView barrageButton;

    private LinearLayout mTopBar;


    private Button mPreviewButton;
    private Button mPushButton;
    private Button mOperaButton;
    private Button mMore;
    private Button mRestartButton;
    private AlivcLivePusher mAlivcLivePusher = null;
    private String mPushUrl = null;
    private SurfaceView mSurfaceView = null;
    private boolean mAsync = false;
    private boolean isMAsync = true; //开始按钮判断startpush
    private boolean isExit = true; //关闭直播  开始按钮判断
    private boolean mAudio = false;
    private boolean mVideoOnly = false;
    private boolean isPushing = false;
    private Handler mHandler = new Handler();

    private LivePushActivity.PauseState mStateListener = null;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
    private boolean isFlash = true;
    private boolean mMixExtern = false;
    private boolean mMixMain = false;
    private boolean flashState = true;

    private int mQualityMode = 0;

    ScheduledExecutorService mExecutorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
    private boolean videoThreadOn = false;
    private boolean videoThreadOn2 = false;
    private boolean audioThreadOn = false;


    private String mAuthString = "?auth_key=%1$d-%2$d-%3$d-%4$s";
    private String mMd5String = "%1$s-%2$d-%3$d-%4$d-%5$s";
    private String mTempUrl = null;
    private String mAuthTime = "";
    private String mPrivacyKey = "";
    private String crid = "";

    Vector<Integer> mDynamicals = new Vector<>();
    //隐藏
    private LinearLayout mBottomMenu;
    //头部4个头像
    ImageView imgPersonal1;
    ImageView imgPersonal2;
    ImageView imgPersonal3;
    ImageView imgPersonal4;
    //听课人数
    TextView listenPersonSum;
    //点赞人数
    TextView tvZanNum;
    private long startTime; //推流按钮防止疯狂点击
    private boolean isEnd = false; //判断结束的直播

    //点赞心
    HiPraiseAnimationView mHiPraiseAnimationView;
    private static final int HEARDS[] = new int[]{
            R.drawable.heart_1,
            R.drawable.heart_2,
            R.drawable.heart_3,
            R.drawable.heart_4,
            R.drawable.heart_5,
            R.drawable.heart_6,
            R.drawable.heart_7,
            R.drawable.heart_8,
            R.drawable.heart_9,
            R.drawable.heart_10,
            R.drawable.heart_11,
            R.drawable.heart_12

    };
    private SparseArray<SoftReference<Bitmap>> mBitmapCacheArray = new SparseArray<>();
    private AliPayerModel aliPayerModel;
    private HashMap<String, String> liveParams;//接口参数
    private AliBeginBean aliBeginBean;// 初次进页面网络请求的实体类
    private HashMap<String, String> sigParams; //IM sign参数
    private int mZanNum; //点赞的数量
    private ListView pullToLvSl; //历史聊天记录
    private ImageView llPush; //开始推流 和 关流的按钮
    private boolean isMyPushing = false;//这是判断推流的状态    false 未推流    true 正推流
    private RelativeLayout rlparent;
    private LinearLayout llSeeParent;
    private LinearLayout llLvParent;
    private RelativeLayout rlZanNum;

    public static LivePushFragment newInstance(String url, boolean async, boolean mAudio, boolean mVideoOnly, int cameraId, boolean isFlash, int mode, String authTime, String privacyKey, boolean mixExtern, boolean mixMain, String crid) {
        LivePushFragment livePushFragment = new LivePushFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL_KEY, url);
        bundle.putBoolean(ASYNC_KEY, async);
        bundle.putBoolean(AUDIO_ONLY_KEY, mAudio);
        bundle.putBoolean(VIDEO_ONLY_KEY, mVideoOnly);
        bundle.putInt(QUALITY_MODE_KEY, mode);
        bundle.putInt(CAMERA_ID, cameraId);
        bundle.putBoolean(FLASH_ON, isFlash);
        bundle.putString(AUTH_TIME, authTime);
        bundle.putString(PRIVACY_KEY, privacyKey);
        bundle.putBoolean(MIX_EXTERN, mixExtern);
        bundle.putBoolean(MIX_MAIN, mixMain);
        bundle.putString(CRID, crid);
        livePushFragment.setArguments(bundle);
        return livePushFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPushUrl = getArguments().getString(URL_KEY);
            mTempUrl = mPushUrl;
            mAsync = getArguments().getBoolean(ASYNC_KEY, false);
            mAudio = getArguments().getBoolean(AUDIO_ONLY_KEY, false);
            mVideoOnly = getArguments().getBoolean(VIDEO_ONLY_KEY, false);
//            mCameraId = getArguments().getInt(CAMERA_ID);
            isFlash = getArguments().getBoolean(FLASH_ON, false);
            mMixExtern = getArguments().getBoolean(MIX_EXTERN, false);
            mMixMain = getArguments().getBoolean(MIX_MAIN, false);
            mQualityMode = getArguments().getInt(QUALITY_MODE_KEY);
            mAuthTime = getArguments().getString(AUTH_TIME);
            mPrivacyKey = getArguments().getString(PRIVACY_KEY);
            crid = getArguments().getString(CRID);
            flashState = isFlash;
        }
        if (mAlivcLivePusher != null) {
            mAlivcLivePusher.setLivePushInfoListener(mPushInfoListener);
            mAlivcLivePusher.setLivePushErrorListener(mPushErrorListener);
            mAlivcLivePusher.setLivePushNetworkListener(mPushNetworkListener);
            mAlivcLivePusher.setLivePushBGMListener(mPushBGMListener);
            isPushing = mAlivcLivePusher.isPushing();
        }

        if (mMixExtern) {
            //startYUV(getActivity());
            //startYUV2(getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.push_fragment, container, false);
    }

    private ArrayList<ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX> hostorylist; //聊天记录的集合
    private AlivePlayAdapter mAdapter; //聊天记录adapter

    //初始化listView
    private void initHostoryListView() {
//        pullToSl.setMode(PullToRefreshBase.Mode.BOTH);
        hostorylist = new ArrayList<>();
        mAdapter = new AlivePlayAdapter(getActivity(), 1, hostorylist);
        pullToLvSl.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pullToLvSl = view.findViewById(R.id.message_lv);
        imgPersonal1 = view.findViewById(R.id.img_personal_1);
        imgPersonal2 = view.findViewById(R.id.img_personal_2);
        imgPersonal3 = view.findViewById(R.id.img_personal_3);
        imgPersonal4 = view.findViewById(R.id.img_personal_4);
        listenPersonSum = view.findViewById(R.id.listen_person_sum);
        tvZanNum = view.findViewById(R.id.tv_zan_num);
        rlZanNum = view.findViewById(R.id.rl_zan_num);
        mHiPraiseAnimationView = view.findViewById(R.id.praise_animation);
        rlparent = view.findViewById(R.id.rl_parent);
        llLvParent = view.findViewById(R.id.ll_lv_parent);

        mExit = (ImageView) view.findViewById(R.id.exit);
        barrageButton = view.findViewById(R.id.barrage_button);
        barrageButton.setSelected(true);
        mFlash = (ImageView) view.findViewById(R.id.flash);
        mFlash.setSelected(isFlash);
        mCamera = (ImageView) view.findViewById(R.id.camera);
        mCamera.setSelected(true);
        mPreviewButton = (Button) view.findViewById(R.id.preview_button);
        mPreviewButton.setSelected(false);
        mPushButton = (Button) view.findViewById(R.id.push_button);
        mPushButton.setSelected(true);
        llPush = view.findViewById(R.id.ll_push);
        llPush.setSelected(true);
        mOperaButton = (Button) view.findViewById(R.id.opera_button);
        mOperaButton.setSelected(false);
        mMore = (Button) view.findViewById(R.id.more);
        mBeautyButton = (ImageView) view.findViewById(R.id.beauty_button);
        llSeeParent = view.findViewById(R.id.ll_see_parent);
        mBeautyButton.setSelected(SharedPreferenceUtils.isBeautyOn(getActivity().getApplicationContext()));

        mRestartButton = (Button) view.findViewById(R.id.restart_button);
        mBottomMenu = view.findViewById(R.id.ll_bottom_menu);
        mTopBar = (LinearLayout) view.findViewById(R.id.top_bar);


        mExit.setOnClickListener(onClickListener);
        //闪光灯
        mFlash.setOnClickListener(onClickListener);
        // 夫的监听
        rlparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果显示
                if (llSeeParent.getVisibility() == View.VISIBLE) {
                    setWingetVisibility(false);
                } else {
                    setWingetVisibility(true);
                }
            }
        });

        mCamera.setOnClickListener(onClickListener);
        mPreviewButton.setOnClickListener(onClickListener);
        mPushButton.setOnClickListener(onClickListener);
        llPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //大于0.5秒方个通过   第一次进来的时候默认通过的（因为时间小于500）
                if (System.currentTimeMillis() - startTime <= 1000) {
                    ToastUtil.showToast(getContext(), "切换太快了");
                    return;
                }
                //用当前时间赋值给starTime
                startTime = System.currentTimeMillis();
                isMyPushing = true; //正推流
                if (isExit) {

                    //判断当前的直播是否结束
//                    if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getStartTime() - System.currentTimeMillis() < 1000 * 60 * 10
//                            && System.currentTimeMillis() <= aliBeginBean.getAppendData().getBaseInfo().getEndTime()) {
                    // 退流开始的后台连接
                    liveParams.put("type", "1");
                    aliPayerModel.startOrEndPush(getActivity(), LivePushFragment.this,
                            "START_END_PUSH", liveParams, false,
                            false, false, null);
                    if (isMAsync)
                        if (mAsync) {
                            mAlivcLivePusher.startPushAysnc(getAuthString(mAuthTime));
                        } else {
                            mAlivcLivePusher.startPush(getAuthString(mAuthTime));
                        }
                    isMAsync = true;
                    llPush.setImageResource(R.mipmap.record_video_stop);
//                    if (mMixExtern) {
//                        //startMixPCM(getActivity());
//                    } else if (mMixMain) {
//                        startPCM(getActivity());
//                    }
                    isExit = false;

                } else {

                    exitMothod();

                }

              /*  llPush.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isPush) {
                            llPush.setImageResource(R.mipmap.record_video_stop);
                        } else {
                            llPush.setImageResource(R.mipmap.record_video_start);
                        }
                        llPush.setSelected(!isPush);
                    }
                });*/

            }
        });
        mOperaButton.setOnClickListener(onClickListener);
        mBeautyButton.setOnClickListener(onClickListener);
        barrageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isBarrage = barrageButton.isSelected();

                if (isBarrage) {
                    barrageButton.setSelected(false);
                    pullToLvSl.setVisibility(View.GONE);
                } else {
                    barrageButton.setSelected(true);
                    pullToLvSl.setVisibility(View.VISIBLE);
                }
            }
        });

        mRestartButton.setOnClickListener(onClickListener);
        mMore.setOnClickListener(onClickListener);
//        if(SharedPreferenceUtils.isGuide(getActivity().getApplicationContext())) {
//            mGuide.setVisibility(View.VISIBLE);
//            mGuide.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    if(mGuide != null) {
//                        mGuide.setVisibility(View.GONE);
//                        SharedPreferenceUtils.setGuide(getActivity().getApplicationContext(), false);
//                    }
//                    return false;
//                }
//            });
//        }

        if (mVideoOnly) {

        }
        if (mAudio) {
            mPreviewButton.setVisibility(View.GONE);
        }
        if (mMixMain) {
            mBeautyButton.setVisibility(View.GONE);


            mFlash.setVisibility(View.GONE);
            mCamera.setVisibility(View.GONE);
        }
        mMore.setVisibility(mAudio ? View.GONE : View.VISIBLE);
//        mTopBar.setVisibility(mAudio ? View.GONE : View.VISIBLE);
        mBeautyButton.setVisibility(mAudio ? View.GONE : View.VISIBLE);
        mFlash.setVisibility(mAudio ? View.GONE : View.VISIBLE);
        mCamera.setVisibility(mAudio ? View.GONE : View.VISIBLE);
        mFlash.setClickable(mCameraId == CAMERA_TYPE_FRONT.getCameraId() ? false : true);
        //判断有没有网
        isNetWork();

        //初始化 聊天记录
        initHostoryListView();
    }


    private MyDialog dialog;

    private void initData() {
        aliPayerModel = new AliPayerModelImpl();
        liveParams = new HashMap<>();
        liveParams.put("umiid", Globals.USER_UMIID);
        liveParams.put("utoken", Globals.USER_UTOKEN);
        liveParams.put("crid", crid);
        liveParams.put("length", Globals.TEACHER_PAGE_SIZE);
        aliPayerModel.beginLiveRequest(getActivity(), this, "BEGIN_LIVE_URL", liveParams, true,
                true, false, null);
    }

    //判断IM是否登录
    private void initIM() {
        if (!TextUtils.isEmpty(Globals.USER_UTOKEN)) {
            //获取IM sign

            if (!TIMManager.getInstance().getLoginUser().equals(Globals.USER_UMIID)) {
                sigParams = new HashMap<>();
                sigParams.put("umiid", Globals.USER_UMIID);
                sigParams.put("utoken", Globals.USER_UTOKEN);
                aliPayerModel.getTimSig(getActivity(), "GET_IM_SIG", sigParams,
                        false, false, false, null);
            }
        }
        TIMManager.getInstance().addMessageListener(this);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final int id = view.getId();

            if (mAlivcLivePusher == null) {
                if (getActivity() != null) {
                    mAlivcLivePusher = ((LivePushActivity) getActivity()).getLivePusher();
                }

                if (mAlivcLivePusher == null) {
                    return;
                }
            }

            mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (id == R.id.exit) {
                            //离开直播间的操作
                            exitMothod();

                        } else if (id == R.id.flash) {


                            mAlivcLivePusher.setFlash(!mFlash.isSelected());

                            flashState = !mFlash.isSelected();
                            mFlash.post(new Runnable() {
                                @Override
                                public void run() {
                                    mFlash.setSelected(!mFlash.isSelected());
                                }
                            });

                        } else if (id == R.id.camera) {
                            if (mCameraId == CAMERA_TYPE_FRONT.getCameraId()) {
                                mCameraId = CAMERA_TYPE_BACK.getCameraId();
                            } else {
                                mCameraId = CAMERA_TYPE_FRONT.getCameraId();
                            }
                            mAlivcLivePusher.switchCamera();
                            mFlash.post(new Runnable() {
                                @Override
                                public void run() {
                                    mFlash.setClickable(mCameraId == CAMERA_TYPE_FRONT.getCameraId() ? false : true);
                                    if (mCameraId == CAMERA_TYPE_FRONT.getCameraId()) {
                                        mFlash.setSelected(false);
                                    } else {
                                        mFlash.setSelected(flashState);
                                    }
                                }
                            });


                        } else if (id == R.id.preview_button) {
                            final boolean isPreview = mPreviewButton.isSelected();
                            if (mSurfaceView == null && getActivity() != null) {
                                mSurfaceView = ((LivePushActivity) getActivity()).getPreviewView();
                            }
                            if (!isPreview) {
                                mAlivcLivePusher.stopPreview();
                                stopYUV();
                            } else {
                                if (mAsync) {
                                    mAlivcLivePusher.startPreviewAysnc(mSurfaceView);
                                } else {
                                    mAlivcLivePusher.startPreview(mSurfaceView);
                                }
                                if (mMixExtern) {
                                    //startYUV(getActivity());
                                }
                            }

                            mPreviewButton.post(new Runnable() {
                                @Override
                                public void run() {
                                    mPreviewButton.setText(isPreview ? getString(R.string.stop_preview_button) : getString(R.string.start_preview_button));
                                    mPreviewButton.setSelected(!isPreview);
                                }
                            });

                            //开始推流
                        } else if (id == R.id.push_button) {
                            final boolean isPush = mPushButton.isSelected();
                            if (isPush) {
                                if (mAsync) {
                                    mAlivcLivePusher.startPushAysnc(getAuthString(mAuthTime));
                                } else {
                                    mAlivcLivePusher.startPush(getAuthString(mAuthTime));
                                }
                                if (mMixExtern) {
                                    //startMixPCM(getActivity());
                                } else if (mMixMain) {
                                    startPCM(getActivity());
                                }
                            } else {
                                mAlivcLivePusher.stopPush();
                                stopPcm();
                                mOperaButton.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mOperaButton.setText(getString(R.string.pause_button));
                                        mOperaButton.setSelected(false);
                                    }
                                });
                                if (mStateListener != null) {
                                    mStateListener.updatePause(false);
                                }
                            }

                            mPushButton.post(new Runnable() {
                                @Override
                                public void run() {
                                    mPushButton.setText(isPush ? getString(R.string.stop_button) : getString(R.string.start_button));
                                    mPushButton.setSelected(!isPush);
                                }
                            });

                            // 暂停 恢复 推流
                        } else if (id == R.id.opera_button) {
                            final boolean isPause = mOperaButton.isSelected();
                            if (!isPause) {
                                mAlivcLivePusher.pause();
                            } else {
                                if (mAsync) {
                                    mAlivcLivePusher.resumeAsync();

                                } else {
                                    mAlivcLivePusher.resume();
                                }
                            }

                            if (mStateListener != null) {
                                mStateListener.updatePause(!isPause);
                            }
                            mOperaButton.post(new Runnable() {
                                @Override
                                public void run() {
                                    mOperaButton.setText(isPause ? getString(R.string.pause_button) : getString(R.string.resume_button));
                                    mOperaButton.setSelected(!isPause);
                                }
                            });

                            //美颜
                        } else if (id == R.id.beauty_button) {
                            PushBeautyDialog pushBeautyDialog = PushBeautyDialog.newInstance(mBeautyButton.isSelected());
                            // 美颜列表show 底部列表隐藏
                            pushBeautyDialog.setVisibleListener(dialogVisibleListener);
                            pushBeautyDialog.setAlivcLivePusher(mAlivcLivePusher);
                            pushBeautyDialog.setBeautyListener(mBeautyListener);
                            pushBeautyDialog.show(getFragmentManager(), "beautyDialog");
                            //重新推流
                        } else if (id == R.id.restart_button) {/*if(mMixExtern) {
                                    stopYUV();
                                    stopPcm();
                                }*/
                            if (mAsync) {
                                mAlivcLivePusher.restartPushAync();
                            } else {
                                mAlivcLivePusher.restartPush();
                            }
                                /*if(mMixExtern) {
                                    startYUV(getActivity());
                                    startPCM(getActivity());
                                }*/

                        } else if (id == R.id.more) {

                            PushMoreDialog pushMoreDialog = new PushMoreDialog();
                            pushMoreDialog.setVisibleListener(dialogVisibleListener);
                            pushMoreDialog.setAlivcLivePusher(mAlivcLivePusher, new DynamicListern() {
                                @Override
                                public void onAddDynamic() {
                                    if (mAlivcLivePusher != null && mDynamicals.size() < 5) {
                                        float startX = 0.1f + mDynamicals.size() * 0.2f;
                                        float startY = 0.1f + mDynamicals.size() * 0.2f;
                                        int id = mAlivcLivePusher.addDynamicsAddons(Environment.getExternalStorageDirectory().getPath() + File.separator + "alivc_resource/qizi/", startX, startY, 0.2f, 0.2f);
                                        if (id > 0) {
                                            mDynamicals.add(id);
                                        }
                                    }
                                }

                                @Override
                                public void onRemoveDynamic() {
                                    if (mDynamicals.size() > 0) {
                                        int id = mDynamicals.get(0);
                                        mAlivcLivePusher.removeDynamicsAddons(id);
                                        mDynamicals.remove(0);
                                    }
                                }
                            });
                            pushMoreDialog.setQualityMode(mQualityMode);
                            pushMoreDialog.setPushUrl(mPushUrl);
                            pushMoreDialog.show(getFragmentManager(), "moreDialog");

                        } else {

                        }
                    } catch (IllegalArgumentException e) {
//                        showDialog(e.getMessage());
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
//                        showDialog(e.getMessage());
                        e.printStackTrace();
                    }
                }
            });

        }
    };


    public void setAlivcLivePusher(AlivcLivePusher alivcLivePusher) {
        this.mAlivcLivePusher = alivcLivePusher;
    }

    public void setStateListener(LivePushActivity.PauseState listener) {
        this.mStateListener = listener;
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        this.mSurfaceView = surfaceView;
    }


    AlivcLivePushInfoListener mPushInfoListener = new AlivcLivePushInfoListener() {
        @Override
        public void onPreviewStarted(AlivcLivePusher pusher) {
//            showToast(getString(R.string.start_preview));
        }

        @Override
        public void onPreviewStoped(AlivcLivePusher pusher) {
//            showToast(getString(R.string.stop_preview));
        }

        @Override
        public void onPushStarted(AlivcLivePusher pusher) {
//            showToast(getString(R.string.start_push));
        }

        @Override
        public void onFirstAVFramePushed(AlivcLivePusher alivcLivePusher) {

        }

        @Override
        public void onPushPauesed(AlivcLivePusher pusher) {
//            showToast(getString(R.string.pause_push));
        }

        @Override
        public void onPushResumed(AlivcLivePusher pusher) {
//            showToast(getString(R.string.resume_push));
        }

        @Override
        public void onPushStoped(AlivcLivePusher pusher) {
//            showToast(getString(R.string.stop_push));
        }

        /**
         * 推流重启通知
         *
         * @param pusher AlivcLivePusher实例
         */
        @Override
        public void onPushRestarted(AlivcLivePusher pusher) {
//            showToast(getString(R.string.restart_success));
        }

        @Override
        public void onFirstFramePreviewed(AlivcLivePusher pusher) {
//            showToast(getString(R.string.first_frame));
        }

        @Override
        public void onDropFrame(AlivcLivePusher pusher, int countBef, int countAft) {
//            showToast(getString(R.string.drop_frame) + ", 丢帧前：" + countBef + ", 丢帧后：" + countAft);
        }

        @Override
        public void onAdjustBitRate(AlivcLivePusher pusher, int curBr, int targetBr) {
//            showToast(getString(R.string.adjust_bitrate) + ", 当前码率：" + curBr + "Kps, 目标码率：" + targetBr + "Kps");
        }

        @Override
        public void onAdjustFps(AlivcLivePusher pusher, int curFps, int targetFps) {
//            showToast(getString(R.string.adjust_fps) + ", 当前帧率：" + curFps + ", 目标帧率：" + targetFps);
        }


    };

    AlivcLivePushErrorListener mPushErrorListener = new AlivcLivePushErrorListener() {

        @Override
        public void onSystemError(AlivcLivePusher livePusher, AlivcLivePushError error) {
//            showDialog(getString(R.string.system_error) + error.toString());
            sdkError();
        }

        @Override
        public void onSDKError(AlivcLivePusher livePusher, AlivcLivePushError error) {
            //销毁当前直播，重新创建或调用restartPush/restartPushAsync重启AlivcLivePusher
//            aliPayerModel.beginLiveRequest(getActivity(), LivePushFragment.this, "AUTHENTICATION_BEGIN_LIVE_URL", liveParams, true,
//                    true, false, null);
//            mAlivcLivePusher.startPush(getAuthString(mAuthTime));
            sdkError();
        }
    };

    AlivcLivePushNetworkListener mPushNetworkListener = new AlivcLivePushNetworkListener() {
        @Override
        public void onNetworkPoor(AlivcLivePusher pusher) {
//            showNetWorkDialog(getString(R.string.network_poor));
        }

        @Override
        public void onNetworkRecovery(AlivcLivePusher pusher) {
//            showToast(getString(R.string.network_recovery));
        }

        @Override
        public void onReconnectStart(AlivcLivePusher pusher) {

//            showToastShort(getString(R.string.reconnect_start));
        }

        @Override
        public void onReconnectFail(AlivcLivePusher pusher) {
            //重连失败通知
//            showDialog(getString(R.string.reconnect_fail));
//            pusher.restartPush();
        }

        @Override
        public void onReconnectSucceed(AlivcLivePusher pusher) {
//            showToast(getString(R.string.reconnect_success));
        }

        @Override
        public void onSendDataTimeout(AlivcLivePusher pusher) {
            //发送数据超时通知
//            showDialog(getString(R.string.senddata_timeout));
        }

        @Override
        public void onConnectFail(AlivcLivePusher pusher) {
            //连接失败通知
//            showDialog(getString(R.string.connect_fail));
        }

        @Override
        public String onPushURLAuthenticationOverdue(AlivcLivePusher pusher) {
//            showDialog("流即将过期，请更换url");
            return getAuthString(mAuthTime);
        }

        @Override
        public void onSendMessage(AlivcLivePusher pusher) {
//            showToast(getString(R.string.send_message));
        }
    };

    private AlivcLivePushBGMListener mPushBGMListener = new AlivcLivePushBGMListener() {
        @Override
        public void onStarted() {

        }

        @Override
        public void onStoped() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onResumed() {

        }

        @Override
        public void onProgress(final long progress, final long duration) {

        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onDownloadTimeout() {

        }

        @Override
        public void onOpenFailed() {
            //流无效通知，在这里提示流不可访问
//            showDialog(getString(R.string.bgm_open_failed));
        }
    };

    @Override
    public void onDestroy() {
        stopPcm();
        stopYUV();
        super.onDestroy();
        if (mExecutorService != null && !mExecutorService.isShutdown()) {
            mExecutorService.shutdown();
        }


    }

    private void showToast(final String text) {
        if (getActivity() == null || text == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    private void showToastShort(final String text) {
        if (getActivity() == null || text == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    private void showDialog(final String message) {
        if (getActivity() == null || message == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle(getString(R.string.dialog_title));
                    dialog.setMessage(message);
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialog.show();
                }
            }
        });
    }

    private void showNetWorkDialog(final String message) {
        if (getActivity() == null || message == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle(getString(R.string.dialog_title));
                    dialog.setMessage(message);
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.setNeutralButton(getString(R.string.reconnect), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                mAlivcLivePusher.reconnectPushAsync(null);
                            } catch (IllegalStateException e) {

                            }
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    @Override
    public void run() {
        if (mAlivcLivePusher != null) {
            try {
                isPushing = mAlivcLivePusher.isNetworkPushing();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            AlivcLivePushError error = mAlivcLivePusher.getLastError();
            if (!error.equals(AlivcLivePushError.ALIVC_COMMON_RETURN_SUCCESS)) {
                // 推流错误码  false
//                mIsPushing.setText(String.valueOf(isPushing) + ", error code : " + error.getCode());
            } else {
                // true
//                mIsPushing.setText(String.valueOf(isPushing));
            }
        }
        mHandler.postDelayed(this, REFRESH_INTERVAL);

    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.post(this);
        if (null != mHiPraiseAnimationView)
            mHiPraiseAnimationView.start(); //添加点赞动画之前要先开始启动绘制
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(this);
    }

    public void isNetWork() {


//        没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
        switch (NetWorkUtils.getAPNType(Globals.context)) {
            case 0:
                break;
            case 4:
            case 3:
            case 2:
                if (!getActivity().isFinishing()) {
                    dialog = new MyDialog(getActivity(), getString(R.string.traffic_data), "");
                    dialog.show();
                    dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    });
                    dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //网络请求
                            initData();
                            //初始化IM
                            initIM();
                            dialog.dismiss();

                        }
                    });
                }
                break;
            case 1:
                //网络请求
                initData();
                //初始化IM
                initIM();
                break;
        }

    }

    public interface BeautyListener {
        void onBeautySwitch(boolean beauty);

        void onBeautySettingChange(BeautyParams params);
    }

    private BeautyListener mBeautyListener = new BeautyListener() {
        @Override
        public void onBeautySwitch(boolean beauty) {
            if (mBeautyButton != null) {
                mBeautyButton.setSelected(beauty);
            }
        }

        @Override
        public void onBeautySettingChange(BeautyParams params) {
            if (mAlivcLivePusher != null) {
                mAlivcLivePusher.setBeautyWhite(params.beautyWhite);
                mAlivcLivePusher.setBeautyBuffing(params.beautyBuffing);
                mAlivcLivePusher.setBeautyCheekPink(params.beautyCheekPink);
                mAlivcLivePusher.setBeautyRuddy(params.beautyRuddy);
                mAlivcLivePusher.setBeautySlimFace(params.beautySlimFace);
                mAlivcLivePusher.setBeautyShortenFace(params.beautyShortenFace);
                mAlivcLivePusher.setBeautyBigEye(params.beautyBigEye);
            }
        }

    };

    private String getMD5(String string) {

        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    private String getUri(String url) {
        String result = "";
        String temp = url.substring(7);
        if (temp != null && !temp.isEmpty()) {
            result = temp.substring(temp.indexOf("/"));
        }
        return result;
    }

    private String getAuthString(String time) {
        if (!time.isEmpty() && !mPrivacyKey.isEmpty()) {
            long tempTime = (System.currentTimeMillis() + Integer.valueOf(time)) / 1000;
            String tempprivacyKey = String.format(mMd5String, getUri(mPushUrl), tempTime, 0, 0, mPrivacyKey);
            String auth = String.format(mAuthString, tempTime, 0, 0, getMD5(tempprivacyKey));
            mTempUrl = mPushUrl + auth;
        } else {
            mTempUrl = mPushUrl;
        }
        return mTempUrl;
    }


    private void stopYUV() {
        videoThreadOn = false;
        videoThreadOn2 = false;
    }

    private void stopPcm() {
        audioThreadOn = false;
    }


    private void startPCM(final Context context) {
        new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private AtomicInteger atoInteger = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("LivePushActivity-readPCM-Thread" + atoInteger.getAndIncrement());
                return t;
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                audioThreadOn = true;
                byte[] pcm;
                int allSended = 0;
                int sizePerSecond = 44100 * 2;
                InputStream myInput = null;
                OutputStream myOutput = null;
                boolean reUse = false;
                long startPts = System.nanoTime() / 1000;
                try {
                    File f = new File("/sdcard/alivc_resource/441.pcm");
                    myInput = new FileInputStream(f);
                    byte[] buffer = new byte[2048];
                    int length = myInput.read(buffer, 0, 2048);
                    while (length > 0 && audioThreadOn) {
                        long pts = System.nanoTime() / 1000;
                        mAlivcLivePusher.inputStreamAudioData(buffer, length, pts);
                        allSended += length;
                        if ((allSended * 1000000L / sizePerSecond - 50000) > (pts - startPts)) {
                            try {
                                Thread.sleep(45);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        length = myInput.read(buffer);
                        if (length < 2048) {
                            myInput.close();
                            myInput = new FileInputStream(f);
                            length = myInput.read(buffer);
                        }
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    myInput.close();
                    audioThreadOn = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface DynamicListern {
        void onAddDynamic();

        void onRemoveDynamic();
    }

    DialogVisibleListener dialogVisibleListener = new DialogVisibleListener() {
        @Override
        public void isDialogVisible(boolean isVisible) {
            //美颜 度 1 2 3 4 5
            showBottomMenu(!isVisible);

        }
    };

    public void showBottomMenu(boolean isShow) {

        if (isShow) {

            if (mBottomMenu != null && mBottomMenu.getVisibility() != View.VISIBLE) {

                AnimUitls.startAppearAnimY(mBottomMenu);
                mBottomMenu.setVisibility(View.GONE);
            }
        } else {

            if (mBottomMenu != null && mBottomMenu.getVisibility() == View.VISIBLE) {

                AnimUitls.startDisappearAnimY(mBottomMenu);
                mBottomMenu.setVisibility(View.GONE);
            }
        }

    }

    ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX onNewMessagesBean;
    private AliPlayPersonSum aliPlayPersonSum; ///听课次数更新、用户列表更新

    //IM 收到 的信息
    @Override
    public boolean onNewMessages(List<TIMMessage> list) {


        for (TIMMessage message : list) {

            for (int i = 0; i < message.getElementCount(); i++) {
                TIMElem element = message.getElement(i);
                TIMElemType elemType = element.getType();
                if (elemType == TIMElemType.Custom) {
                    byte[] userData = ((TIMCustomElem) element).getData();
                    if (userData == null || userData.length == 0) {
                        return true;
                    }

                    String data = new String(userData);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(data);
                        if (jsonObject.has("crid")) {
                            if (jsonObject.getInt("crid") == Integer.valueOf(crid)) {
                                if (jsonObject.has("type")) {
                                    switch (String.valueOf(jsonObject.get("type"))) {
                                        case "1":
                                            break;
                                        //听课次数更新、用户列表更新
                                        case "2":

                                            aliPlayPersonSum = GsonUtils.json2bean(data, AliPlayPersonSum.class);

                                            if (null != aliPlayPersonSum.getContent().getData() &&
                                                    aliPlayPersonSum.getContent().getData().size() >= 1
                                                    && null != aliPlayPersonSum.getContent().getData().get(0).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(0).getHeadimg(), imgPersonal1);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData() &&
                                                    aliPlayPersonSum.getContent().getData().size() >= 2 &&
                                                    null != aliPlayPersonSum.getContent().getData().get(1).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(1).getHeadimg(), imgPersonal2);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData() &&
                                                    aliPlayPersonSum.getContent().getData().size() >= 3
                                                    && null != aliPlayPersonSum.getContent().getData().get(2).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(2).getHeadimg(), imgPersonal3);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData() &&
                                                    aliPlayPersonSum.getContent().getData().size() >= 4
                                                    && null != aliPlayPersonSum.getContent().getData().get(3).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(3).getHeadimg(), imgPersonal4);
                                            }
                                            listenPersonSum.setText(Globals.context.getString(R.string.listen_class_sum)
                                                    + aliPlayPersonSum.getContent().getSeeNum());

                                            break;
                                        //点赞
                                        case "3":
                                            JSONObject zanJson = jsonObject.getJSONObject("content");
                                            // 判断点击数 >3 的话
                                            if (zanJson.getInt("zanNum") - mZanNum > 3) {
                                                for (int ii = 0; ii < 5; ii++) {
                                                    addPraiseWithCallback();
                                                }
                                            } else {
                                                addPraiseWithCallback();
                                            }

                                            tvZanNum.setText(zanJson.getInt("zanNum") + "");
                                            mZanNum = zanJson.getInt("zanNum");

                                            break;
                                        //用户发送普通消息
                                        case "4":

                                            JSONObject contentJson = jsonObject.getJSONObject("content");

                                            if (!String.valueOf(contentJson.getInt("umiid")).equals(Globals.USER_UMIID)) {
                                                onNewMessagesBean = new ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX();
                                                onNewMessagesBean.setId(contentJson.getInt("id"));
                                                onNewMessagesBean.setType(contentJson.getInt("type"));
                                                onNewMessagesBean.setUmiid(contentJson.getInt("umiid"));
                                                onNewMessagesBean.setRole(contentJson.getInt("role"));
                                                onNewMessagesBean.setName(contentJson.getString("name"));
                                                onNewMessagesBean.setContent(contentJson.getString("content"));
                                                onNewMessagesBean.setSendtime(contentJson.getInt("sendtime"));
                                                hostorylist.add(0, onNewMessagesBean);
                                                mAdapter.notifyDataSetChanged();
                                            }


                                            break;
                                        case "5":
                                            break;
                                        case "6":
                                            break;
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
        return false;

    }


    @Override
    public void analysisData(String requestCode, Object bean, int id) {
        switch (requestCode) {
            // 进入直播开始回调
            case "BEGIN_LIVE_URL":
                aliBeginBean = (AliBeginBean) bean;
                if (null != aliBeginBean.getAppendData().getBaseInfo().getPullUrl() ||
                        !aliBeginBean.getAppendData().getBaseInfo().getPullUrl().equals("")) {
                    //设置推流的url
                    mPushUrl = aliBeginBean.getAppendData().getBaseInfo().getPushUrl();
                    mTempUrl = mPushUrl;
                }

                //初始化布局

                if (null != aliBeginBean) {

                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() && aliBeginBean.getAppendData().getUserInfo().getData().size() >= 1
                            && null != aliBeginBean.getAppendData().getUserInfo().getData().get(0).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(0).getHeadimg(), imgPersonal1);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() && aliBeginBean.getAppendData().getUserInfo().getData().size() >= 2 && null != aliBeginBean.getAppendData().getUserInfo().getData().get(1).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(1).getHeadimg(), imgPersonal2);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() && aliBeginBean.getAppendData().getUserInfo().getData().size() >= 3 && null != aliBeginBean.getAppendData().getUserInfo().getData().get(2).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(2).getHeadimg(), imgPersonal3);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() && aliBeginBean.getAppendData().getUserInfo().getData().size() >= 4 && null != aliBeginBean.getAppendData().getUserInfo().getData().get(3).getHeadimg())
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(3).getHeadimg(), imgPersonal4);

                    //设置听课人数
                    listenPersonSum.setText(Globals.context.getString(R.string.listen_class_sum)
                            + aliBeginBean.getAppendData().getUserInfo().getSeeNum());
                    mZanNum = aliBeginBean.getAppendData().getChatInfo().getZanNum();
                    tvZanNum.setText(
                            aliBeginBean.getAppendData().getChatInfo().getZanNum() + "");
                    // 聊天记录
                    List<AliBeginBean.AppendDataBean.ChatInfoBean.DataBeanX> data1 = aliBeginBean.getAppendData().getChatInfo().getData();
                    ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX dataBeanX;
                    for (int i = 0; i < data1.size(); i++) {
                        dataBeanX = new ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX();
                        dataBeanX.setContent(data1.get(i).getContent());
                        dataBeanX.setName(data1.get(i).getName());
                        dataBeanX.setId(data1.get(i).getId());
                        dataBeanX.setRole(data1.get(i).getRole());
                        dataBeanX.setSendtime(data1.get(i).getSendtime());
                        dataBeanX.setType(data1.get(i).getType());
                        dataBeanX.setUmiid(data1.get(i).getUmiid());
                        hostorylist.add(dataBeanX);
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "START_END_PUSH":

                if (isMyPushing == false) { // 如果关闭推流  直接finish
                    isEnd = true;
                    if (null != getActivity() && !getActivity().isFinishing()) {
                        getActivity().setResult(0);
                        getActivity().finish();
                    }
                }
                break;
            case "AUTHENTICATION_BEGIN_LIVE_URL":
                aliBeginBean = (AliBeginBean) bean;
                if (null != aliBeginBean.getAppendData().getBaseInfo().getPullUrl() ||
                        !aliBeginBean.getAppendData().getBaseInfo().getPullUrl().equals("")) {
                    //设置推流的url
                    mPushUrl = aliBeginBean.getAppendData().getBaseInfo().getPushUrl();
                    mTempUrl = mPushUrl;
                }

                break;
        }
    }

    @Override
    public void errorhandle(String requestCode, Call call, Exception e, int id) {

    }

    /**
     * 添加具有回调的点赞动画
     */
    private void addPraiseWithCallback() {
        final IPraise hiPraiseWithCallback = new HiPraiseWithCallback(getHeartBitmap(),
                new OnDrawCallback() {
                    @Override
                    public void onFinish() {

                    }
                });
        mHiPraiseAnimationView.addPraise(hiPraiseWithCallback);
    }

    SoftReference<Bitmap> bitmapRef;

    private Bitmap getHeartBitmap() {
        final int id = HEARDS[new Random().nextInt(HEARDS.length)];
        bitmapRef = mBitmapCacheArray.get(id);
        Bitmap retBitmap = null;
        if (null != bitmapRef) {
            retBitmap = bitmapRef.get();
        }
        if (null == retBitmap) {
            retBitmap = BitmapFactory.decodeResource(Globals.context.getResources(),
                    id);
            mBitmapCacheArray.put(id, new SoftReference<>(retBitmap));
        }
        return retBitmap;
    }

    @Override
    public void onStop() {
        super.onStop();
        mHiPraiseAnimationView.stop(); //停止绘制点赞动画
    }


    //离开直播间的操作
    public void exitMothod() {
        if (!isEnd) // 关流回调改值  判断是否关流
            if (isMyPushing) { //正在推流
                if (!getActivity().isFinishing()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isMAsync = false; //设为false ,开始按钮使用
                            dialog = new MyDialog(getActivity(), "是否结束直播", "");
                            dialog.show();

                            dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.setOk(getString(R.string.end_live), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isMyPushing = false; //关推流
                                    // stop SDK
                                    if (mAlivcLivePusher.isPushing())
                                        mAlivcLivePusher.stopPush();
                                    stopPcm();
                                    mOperaButton.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mOperaButton.setText(getString(R.string.pause_button));
                                            mOperaButton.setSelected(false);
                                        }
                                    });
                                    if (mStateListener != null) {
                                        mStateListener.updatePause(false);
                                    }
                                    // 退流开始的后台连接
                                    liveParams.put("type", "2");
                                    aliPayerModel.startOrEndPush(getActivity(), LivePushFragment.this,
                                            "START_END_PUSH", liveParams, true,
                                            true, false, null);
                                }
                            });

                        }
                    });

                }

            } else {//已关闭推流
                getActivity().finish();
            }
    }

    //SDK 错误提醒
    public void sdkError() {
        if (!getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog = new MyDialog(getActivity(), getString(R.string.live_network_error), "");
                    dialog.show();

                    dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setOk(getString(R.string.end_live), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isMyPushing = false; //关推流
                            // stop SDK
                            if (mAlivcLivePusher.isPushing())
                                mAlivcLivePusher.stopPush();
                            stopPcm();
                            mOperaButton.post(new Runnable() {
                                @Override
                                public void run() {
                                    mOperaButton.setText(getString(R.string.pause_button));
                                    mOperaButton.setSelected(false);
                                }
                            });
                            if (mStateListener != null) {
                                mStateListener.updatePause(false);
                            }
                            // 退流开始的后台连接
                            liveParams.put("type", "2");
                            aliPayerModel.startOrEndPush(getActivity(), LivePushFragment.this,
                                    "START_END_PUSH", liveParams, true,
                                    true, false, null);
                        }
                    });

                }
            });

        }
    }

    //父布局点击隐藏显示
    private void setWingetVisibility(boolean b) {

        if (!b) {
            mTopBar.setVisibility(View.GONE);
            llSeeParent.setVisibility(View.GONE);
            llPush.setVisibility(View.GONE);
            llLvParent.setVisibility(View.GONE);
            rlZanNum.setVisibility(View.GONE);
            mHiPraiseAnimationView.setVisibility(View.GONE);
        } else {
            mTopBar.setVisibility(View.VISIBLE);
            llSeeParent.setVisibility(View.VISIBLE);
            llPush.setVisibility(View.VISIBLE);
            llLvParent.setVisibility(View.VISIBLE);
            rlZanNum.setVisibility(View.VISIBLE);
            mHiPraiseAnimationView.setVisibility(View.VISIBLE);
        }
    }

}
