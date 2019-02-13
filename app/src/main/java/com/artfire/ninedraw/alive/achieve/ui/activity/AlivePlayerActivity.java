package com.artfire.ninedraw.alive.achieve.ui.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aliyun.vod.common.utils.ToastUtil;
import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.artfire.ninedraw.R;
import com.artfire.ninedraw.alive.achieve.utils.NetWorkUtils;
import com.artfire.ninedraw.artfire_code.Bean.ALiHostoryBean;
import com.artfire.ninedraw.artfire_code.Bean.AliBeginBean;
import com.artfire.ninedraw.artfire_code.Bean.AliPlayPersonSum;
import com.artfire.ninedraw.artfire_code.Bean.BoutiqueDetailRewardBean;
import com.artfire.ninedraw.artfire_code.adapter.AlivePlayAdapter;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.model.AliPayerModel;
import com.artfire.ninedraw.artfire_code.model.AliPayerModelImpl;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.utils.AppUtil;
import com.artfire.ninedraw.artfire_code.utils.GlideUtil;
import com.artfire.ninedraw.artfire_code.utils.GoldToastUtil;
import com.artfire.ninedraw.artfire_code.utils.GsonUtils;
import com.artfire.ninedraw.artfire_code.utils.LogUtil;
import com.artfire.ninedraw.artfire_code.utils.MyDialog;
import com.artfire.ninedraw.artfire_code.utils.MyListView;
import com.artfire.ninedraw.artfire_code.utils.MyPullToRefreshScrollView;
import com.artfire.ninedraw.artfire_code.utils.RewardView;
import com.artfire.ninedraw.artfire_code.utils.TimeUtil;
import com.artfire.ninedraw.heartanim.HiPraiseAnimationView;
import com.artfire.ninedraw.heartanim.HiPraiseWithCallback;
import com.artfire.ninedraw.heartanim.IPraise;
import com.artfire.ninedraw.heartanim.OnDrawCallback;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.aliyun.vodplayer.media.IAliyunVodPlayer.PlayerState.Completed;
import static com.aliyun.vodplayer.media.IAliyunVodPlayer.PlayerState.Idle;
import static com.aliyun.vodplayer.media.IAliyunVodPlayer.PlayerState.Stopped;


/**
 * 直播 观看界面
 */
public class AlivePlayerActivity extends BaseActivity implements
        View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener, TIMMessageListener, ApiRequestCallback, RewardView.OnRewardItemClickListener {

    @BindView(R.id.weixin_chat)
    LinearLayout weixinChat;
    @BindView(R.id.img_personal_1)
    ImageView imgPersonal1;
    @BindView(R.id.img_personal_2)
    ImageView imgPersonal2;
    @BindView(R.id.img_personal_3)
    ImageView imgPersonal3;
    @BindView(R.id.img_personal_4)
    ImageView imgPersonal4;
    @BindView(R.id.see_person)
    LinearLayout seePerson;
    @BindView(R.id.listen_person_sum)
    TextView listenPersonSum;
    @BindView(R.id.m_surface_view)
    SurfaceView mSurfaceView;
    @BindView(R.id.img_play)
    ImageView imgPlay;
    @BindView(R.id.tv_is_focus)
    TextView tvIsFocus;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_chat_num)
    TextView tvChatNum;
    @BindView(R.id.liver_name)
    TextView liverName;
    @BindView(R.id.et_send_message)
    LinearLayout etSendMessage;
    @BindView(R.id.rl_top_data)
    RelativeLayout rlTopData;
    @BindView(R.id.img_orientation)
    ImageView imgOrientation;
    @BindView(R.id.liver_img)
    ImageView liverImg;
    @BindView(R.id.message_lv)
    MyListView pullToLvSl;
    @BindView(R.id.pull_to_sl)
    MyPullToRefreshScrollView pullToSl;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.progress_layout)
    LinearLayout progressLayout;
    @BindView(R.id.tv_current_position)
    TextView tvCurrentPosition;
    @BindView(R.id.seekbar_progress)
    SeekBar seekbarProgress;
    @BindView(R.id.tv_total_duration)
    TextView tvTotalDuration;
    @BindView(R.id.tv_quality)
    TextView tvQuality;
    @BindView(R.id.ll_parent)
    RelativeLayout llParent;
    @BindView(R.id.gesture_iv_player_volume)
    ImageView gestureIvPlayerVolume;
    @BindView(R.id.geture_tv_volume_percentage)
    TextView getureTvVolumePercentage;
    @BindView(R.id.gesture_volume_layout)
    RelativeLayout gestureVolumeLayout;
    @BindView(R.id.gesture_iv_player_bright)
    ImageView gestureIvPlayerBright;
    @BindView(R.id.geture_tv_bright_percentage)
    TextView getureTvBrightPercentage;
    @BindView(R.id.gesture_bright_layout)
    RelativeLayout gestureBrightLayout;
    @BindView(R.id.gesture_iv_progress)
    ImageView gestureIvProgress;
    @BindView(R.id.geture_tv_progress_time)
    TextView getureTvProgressTime;
    @BindView(R.id.gesture_progress_layout)
    RelativeLayout gestureProgressLayout;
    @BindView(R.id.ll_liver_start)
    LinearLayout llLiverStart;
    @BindView(R.id.praise_animation)
    HiPraiseAnimationView mHiPraiseAnimationView;
    @BindView(R.id.rl_zan_num)
    RelativeLayout rlZanNum;
    @BindView(R.id.tv_zan_num)
    TextView tvZanNum;
    @BindView(R.id.loading_pbar)
    ProgressBar loadingPbar;
    @BindView(R.id.et_send_message_sv)
    ScrollView etSendMessageSv;
    @BindView(R.id.surface_bg)
    ImageView surfaceBg;
    private List<String> mQualities = new ArrayList<>();  //质量集合枚举
    private List<String> qualityListE = new ArrayList<>();   //质量集合英文
    private List<String> qualityList;   //质量集合中文
    private String url; //传过来播放的URL
    private AliyunVodPlayer aliyunVodPlayer;
    private GestureDetector gestureDetector; // 手势监听类
    private int curPosition, duration; //视频播放时间,视频播放的总时长
    //用来记录前后台切换时的状态，以供恢复。
    private IAliyunVodPlayer.PlayerState mPlayerState;
    public boolean mAutoPlay = true;  //是否自动播放
    private boolean firstScroll = false;// 每次触摸屏幕后，第一次scroll的标志
    private int GESTURE_FLAG = 0;// 1,调节进度，2，调节音量,3.调节亮度
    private static final int GESTURE_MODIFY_PROGRESS = 1; //进度
    private static final int GESTURE_MODIFY_VOLUME = 2;   //音量
    private static final int GESTURE_MODIFY_BRIGHT = 3;    //亮度
    private int screenWidth; // 屏幕宽度
    private int screenHeight; // 屏幕高度
    private static final float STEP_PROGRESS = 2f;// 设定进度滑动时的步长，避免每次滑动都改变，导致改变过快
    private static final float STEP_VOLUME = 2f;// 协调音量滑动时的步长，避免每次滑动都改变，导致改变过快
    private AudioManager audiomanager; // 音量管理器
    private int maxVolume, currentVolume;
    private float currentLight; // 当前屏幕的亮度
    private Context mContext;
    private Boolean isPortrait = true; //判断横屏 竖屏
    private AlivePlayAdapter mAdapter; //聊天记录adapter
    private AliPayerModel homeModel;  //获取IM sign
    private AliPayerModel aliPayerModel;
    private HashMap<String, String> sigParams; //IM sign参数
    private HashMap<String, String> liveParams;//接口参数
    private ArrayList<ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX> hostorylist; //聊天记录的集合
    //点赞心
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
    int zanSum = 0;
    private SparseArray<SoftReference<Bitmap>> mBitmapCacheArray = new SparseArray<>();
    private SurfaceHolder surfaceHolder; // surfaceView Holder
    private MyDialog dialog;
    private Boolean isOrientation = false; //直播显示
    // 改变进度
    private Handler progressUpdateTimer = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            showVideoProgressInfo();
        }
    };
    public static final int REQ_CODE_PUSH = 0x1112; // 跳转LivePushAcitivty  请求码
    // SeekBar监听
    SeekBar.OnSeekBarChangeListener seekBarListerner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (aliyunVodPlayer != null) {
                aliyunVodPlayer.seekTo(seekBar.getProgress());
            }
        }
    };
    private AliBeginBean aliBeginBean;
    private InputMethodManager imm;
    private boolean netWork;
    private String crid; // 课件id
    private Boolean isrlRootListener = true;// SurfaceView父布局是否有点击事件
    private Boolean isLiveing = false; //是否直播内
    // 打赏
    @BindView(R.id.img_reward_class)
    ImageView imgRewardClass;
    private RewardView rewardView;
    private BoutiqueDetailRewardBean.AppendDataBean.O2Bean o2Bean;
    private BoutiqueDetailRewardBean boutiqueDetailRewardBean;
    private String orderNum;
    private Handler handlers;

    public static int WINGET = 001;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progressLayout.getVisibility() == View.VISIBLE)
                setWingetVisibility(false);
        }
    };// 模拟倒计时


    @Override
    public int setLayoutId() {
        return R.layout.activity_alive_player;
    }

    @Override
    public String setTitle() {
        return getString(R.string.welcome_live);
    }

//    private IWXAPI msgApi;

    @Override
    public void wingetListener() {


        imgPlay.setOnClickListener(this);
        rlRoot.setOnClickListener(this);
        //给主布局设置监听
        rlRoot.setLongClickable(true);
        llParent.setOnClickListener(this);
        rlRoot.setOnTouchListener(this);
        seekbarProgress.setOnSeekBarChangeListener(seekBarListerner);
        send.setOnClickListener(this);
        etMessage.setOnClickListener(this);
        tvIsFocus.setOnClickListener(this);
        llLiverStart.setOnClickListener(this);
        imgOrientation.setOnClickListener(this);
        rlZanNum.setOnClickListener(this);
        liverImg.setOnClickListener(this);
        imgRewardClass.setOnClickListener(this);
        mHiPraiseAnimationView.setOnClickListener(this);
        pullToSl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                handler.postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        pullToSl.onRefreshComplete();
                        mAdapter.notifyDataSetChanged();

                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //下拉刷新
                if (hostorylist.size() > 0) {
                    if (null != aliBeginBean)
                        liveParams.put("infoid", hostorylist.get(hostorylist.size() - 1).getId() + "");
                    aliPayerModel.liveHostory((Activity) mContext, "LIVE_HOSTORY_URL",
                            liveParams, false,
                            false, false, pullToSl);
                } else {
                    pullToSl.onRefreshComplete();
                }


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        // 软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //课程id
        crid = getIntent().getStringExtra("crid");
        crid = "2679";
        mAutoPlay = getIntent().getBooleanExtra("mAutoPlay", true);
        mContext = AlivePlayerActivity.this;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initSurface();
        //判断有没有网络
        isNetWork();
        //微信支付
        initWeiXinPay();
        //初始化屏幕信息
        initScreenInfo();
        // 这是手势监听  播放进度
        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setIsLongpressEnabled(true);
        //获取当前声音的信息
        initVoiceState();
        //获取亮度的信息
        initLightState();
        //初始化 聊天记录ListView
        initHostoryListView();
        AppUtil.keepScreenLongLight((Activity) mContext, true);
    }


    private void initView() {
        if (isOneSelf()) {
            rlName.setVisibility(View.GONE); // 老师本人资料
            llLiverStart.setVisibility(View.VISIBLE); //开始直播
            imgPlay.setVisibility(View.GONE);
        } else {
            rlName.setVisibility(View.VISIBLE);
            imgPlay.setVisibility(View.VISIBLE);
            llLiverStart.setVisibility(View.GONE); //开始直播
        }

    }

    //初始化网络请求
    private void initRequest() {

        aliPayerModel = new AliPayerModelImpl();
        liveParams = new HashMap<>();
        liveParams.put("umiid", Globals.USER_UMIID);
        liveParams.put("utoken", Globals.USER_UTOKEN);
        liveParams.put("crid", crid); //1343   3
        liveParams.put("length", Globals.ALL_STUDENT_PAGE_SIZE);
        aliPayerModel.beginLiveRequest((Activity) mContext, null, "BEGIN_LIVE_URL", liveParams, true,
                true, false, null);

    }

    @Override
    public void analysisData(String requestCode, Object bean, int id) {
        switch (requestCode) {
            // 进入直播开始回调
            case "BEGIN_LIVE_URL":

                aliBeginBean = (AliBeginBean) bean;

                //初始化布局
                initView();
                if (null != aliBeginBean) {
                    // 时间判断  回放  正在直播
                    if (null != aliBeginBean
                            && System.currentTimeMillis() <= aliBeginBean.getAppendData().getBaseInfo().getEndTime()
                            ) {
                        // 如果是预告或者是直播 就隐藏进度条
                        imgPlay.setVisibility(View.GONE);
                        progressLayout.setVisibility(View.GONE);
                        surfaceBg.setVisibility(View.VISIBLE);
                        if (isOneSelf()) {
                            llLiverStart.setVisibility(View.VISIBLE);
                            imgOrientation.setVisibility(View.GONE);//老师进去没有全屏
                            isrlRootListener = false;
                        } else {
                            llLiverStart.setVisibility(View.GONE);
                            if (null != aliBeginBean.getAppendData().getBaseInfo().getPullUrl() &&
                                    !aliBeginBean.getAppendData().getBaseInfo().getPullUrl().equals("")) {
                                url = aliBeginBean.getAppendData().getBaseInfo().getPullUrl();
                                // url赋值  初始化 SurfaceView
                                preparePlay();
                            }
                            //直播时，状态是回放
                            if (aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 2) {
                                imgPlay.setVisibility(View.VISIBLE);
                                progressLayout.setVisibility(View.VISIBLE);
                            } else if (aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 0) {
                                imgPlay.setVisibility(View.VISIBLE);
                            }
                            isrlRootListener = true;
                        }

                        isLiveing = true;
                        //回放隐藏   开始直播按钮
                    } else {
                        if (null != aliBeginBean.getAppendData().getBaseInfo().getPullUrl() &&
                                !aliBeginBean.getAppendData().getBaseInfo().getPullUrl().equals("")) {
                            url = aliBeginBean.getAppendData().getBaseInfo().getPullUrl();
                            // url赋值  初始化 SurfaceView
                            preparePlay();
                        }
                        llLiverStart.setVisibility(View.GONE);
                        imgPlay.setVisibility(View.VISIBLE);
                        progressLayout.setVisibility(View.VISIBLE);
                    }
                    tvChatNum.setText(aliBeginBean.getAppendData().getBaseInfo().getAnnouncement());
                    // 设置surfaceView 的背景图
                    if (null != aliBeginBean.getAppendData().getBaseInfo().getHeadImg()) {
                        surfaceBg.setVisibility(View.VISIBLE);
                        GlideUtil.loadImage(aliBeginBean.getAppendData()
                                .getBaseInfo().getHeadImg(), surfaceBg);
                    }

                    //设置标题
//                    getTitleText().setText(aliBeginBean.getAppendData().getBaseInfo().getCrName());
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() &&
                            aliBeginBean.getAppendData().getUserInfo().getData().size() >= 1
                            && null != aliBeginBean.getAppendData().getUserInfo().getData().get(0).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(0).getHeadimg(), imgPersonal1);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() &&
                            aliBeginBean.getAppendData().getUserInfo().getData().size() >= 2 &&
                            null != aliBeginBean.getAppendData().getUserInfo().getData().get(1).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(1).getHeadimg(), imgPersonal2);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() && aliBeginBean.getAppendData().getUserInfo().getData().size() >= 3 && null != aliBeginBean.getAppendData().getUserInfo().getData().get(2).getHeadimg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(2).getHeadimg(), imgPersonal3);
                    }
                    if (null != aliBeginBean.getAppendData().getUserInfo().getData() &&
                            aliBeginBean.getAppendData().getUserInfo().getData().size() >= 4 &&
                            null != aliBeginBean.getAppendData().getUserInfo().getData().get(3).getHeadimg())
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getUserInfo()
                                .getData().get(3).getHeadimg(), imgPersonal4);
                    if (null != aliBeginBean.getAppendData().getBaseInfo().getTeacher().getHeadImg()) {
                        GlideUtil.loadRoundPortraits(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getHeadImg(),
                                liverImg);
                    }

                    if (null != aliBeginBean.getAppendData().getBaseInfo().getTeacher().getName()) {

                        liverName.setText(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getName());
                    }
                    if (aliBeginBean.getAppendData().getBaseInfo().getTeacher().getIsFocus() == 1) {
                        tvIsFocus.setText(getString(R.string.focus));
                    } else
                        tvIsFocus.setText(getString(R.string.not_focus));
                    //设置听课人数
                    listenPersonSum.setText(getString(R.string.listen_class_sum)
                            + aliBeginBean.getAppendData().getUserInfo().getSeeNum());
                    zanSum = aliBeginBean.getAppendData().getChatInfo().getZanNum();
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
            //直播聊天记录
            case "LIVE_HOSTORY_URL":
                ALiHostoryBean hostryBean
                        = (ALiHostoryBean) bean;
                if (null != hostryBean.getAppendData().getChatInfo().getData()) {
                    List<ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX> data
                            = hostryBean.getAppendData().getChatInfo().getData();
                    hostorylist.addAll(data);
                }

                mAdapter.notifyDataSetChanged();
                break;
            //关注
            case "FOCUS_USER":
                if (null != aliBeginBean)
                    if (aliBeginBean.getAppendData()
                            .getBaseInfo().getTeacher().getIsFocus() == 1) {
                        aliBeginBean.getAppendData()
                                .getBaseInfo().getTeacher().setIsFocus(0);
                        tvIsFocus.setText(getString(R.string.not_focus));
                    } else {
                        aliBeginBean.getAppendData()
                                .getBaseInfo().getTeacher().setIsFocus(1);
                        tvIsFocus.setText(getString(R.string.focus));
                    }
                break;
            //发送信息或点赞
            case "GOOD_OR_SEND_MESSAGE":

                break;
            //支付
            case "COURSE_BUY_ORDER":
//                boutiqueDetailRewardBean = GsonUtils.json2bean(bean.toString(), BoutiqueDetailRewardBean.class);
//                o2Bean = boutiqueDetailRewardBean.getAppendData().getO2();
//                orderNum = o2Bean.getOrderid();
//                Message msg = new Message();
//                msg.what = AppMessageValues.REWARD_PURCHASE_WEIXIN_CREATE_ORDER_SUCC;
//                handlers.sendMessage(msg);
                break;
            case "AFFIRM_ORDER":
                if (null != rewardView)
                    rewardView.close();
                ToastUtil.showToast(mContext, getString(R.string.pay_success));
                break;

        }
    }

    @Override
    public void errorhandle(String requestCode, Call call, Exception e, int id) {

    }

    //判断IM是否登录
    private void initIM() {
        if (!TextUtils.isEmpty(Globals.USER_UTOKEN)) {
            homeModel = new AliPayerModelImpl();
            if (!TIMManager.getInstance().getLoginUser().equals(Globals.USER_UMIID)) {
                sigParams = new HashMap<>();
                sigParams.put("umiid", Globals.USER_UMIID);
                sigParams.put("utoken", Globals.USER_UTOKEN);
                homeModel.getTimSig(this, "GET_IM_SIG", sigParams,
                        false, false, false, null);
            }
        }
        TIMManager.getInstance().addMessageListener((TIMMessageListener) mContext);
    }

    //初始化listView
    private void initHostoryListView() {
        pullToSl.setMode(PullToRefreshBase.Mode.BOTH);
        hostorylist = new ArrayList<>();
        mAdapter = new AlivePlayAdapter(mContext, 2, hostorylist);
        pullToLvSl.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //onStop中记录下来的状态，在这里恢复使用
        savePlayerState();
        if (null != mHiPraiseAnimationView)
            mHiPraiseAnimationView.stop(); //停止绘制点赞动画
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (null != aliyunVodPlayer) {
            IAliyunVodPlayer.PlayerState playerState = aliyunVodPlayer.getPlayerState();
            if (null != playerState && playerState == IAliyunVodPlayer.PlayerState.Paused) {
                isShow = true;
            }
        }
        if (null != mHiPraiseAnimationView)
            mHiPraiseAnimationView.start(); //添加点赞动画之前要先开始启动绘制


    }


    @Override
    protected void onDestroy() {
        AppUtil.keepScreenLongLight((Activity) mContext, false);
        aliyunVodPlayer.stop();
        aliyunVodPlayer.release();
        stopUpdateTimer();
//        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
        handler = null;

        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 老师主页
            case R.id.liver_img:
//                JoinUserDataUtil.startUserDataActivity(mContext
//                        , String.valueOf(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getUmiid()));
                break;
            case R.id.img_play:    //播放按钮
                //当回放的时候  提示视频正在
                if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 2) {
                    if (null != aliBeginBean.getAppendData().getBaseInfo().getPullUrl() &&
                            aliBeginBean.getAppendData().getBaseInfo().getPullUrl().equals("")) {
                        dialog = new MyDialog(this, aliBeginBean.getAppendData().getBaseInfo().getNotice(), "");
                        dialog.setOne();
                        dialog.show();
                        dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                            }
                        });
                        return;
                    }

                }

                if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getStartTime() - System.currentTimeMillis() < 0
                        && System.currentTimeMillis() <= aliBeginBean.getAppendData().getBaseInfo()
                        .getEndTime()) {
                    if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 0) {
                        //未开始直播
                        GoldToastUtil.showTopGoldToast(mContext, getString(R.string.teacher_living));
                    } else {
                        imgPlayMethod();
                    }

                } else {
                    //不再直播时间
                    if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getStartTime() - System.currentTimeMillis() > 0) {
                        if (!AlivePlayerActivity.this.isFinishing()) {
                            dialog = new MyDialog(this, getString(R.string.no_live_time)
                                    + TimeUtil.getStrTime(aliBeginBean
                                    .getAppendData().getBaseInfo().getStartTime() + "", "MM月dd日HH时mm分"), "");
                            dialog.setOne();
                            dialog.show();
                            dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                        }
                        return;
                    } else {
                        imgPlayMethod();
                    }

                }


                break;
            // 总布局监听  按钮隐藏显示
            case R.id.rl_root:
                if (isrlRootListener)
                    if (progressLayout.getVisibility() == View.VISIBLE) {
                        setWingetVisibility(false);
                    } else {
                        setWingetVisibility(true);
                        handler.sendEmptyMessageDelayed(WINGET, 2000);
                    }
                break;
            case R.id.et_message:

                break;
            // 发送消息
            case R.id.send:

                //滑动到最上方

                if (etMessage.getText().toString().trim() != null
                        && !etMessage.getText().toString().trim().equals("")) {
                    onNewMessagesBean = new ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX();
                    onNewMessagesBean.setUmiid(Integer.valueOf(Globals.USER_UMIID));
                    if (null != aliBeginBean)
                        onNewMessagesBean.setRole(aliBeginBean.getAppendData().getBaseInfo().getRole());
                    onNewMessagesBean.setName(Globals.USER_UMALIAS);
                    onNewMessagesBean.setContent(etMessage.getText().toString().trim());
                    onNewMessagesBean.setId(Integer.valueOf(crid));
                    hostorylist.add(0, onNewMessagesBean);
                    mAdapter.notifyDataSetChanged();
                    goodOrSendMessgae("2");
                    etMessage.setText("");
                }

                // 隐藏系统默认的软键盘
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘

                ScrollView refreshableView = pullToSl.getRefreshableView();
                refreshableView.smoothScrollTo(0, 0);

//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
//                        .hideSoftInputFromWindow(this.getCurrentFocus()
//                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            //切换屏幕
            case R.id.img_orientation:
                if (!isPortrait) { //横屏
                    rlName.setVisibility(View.VISIBLE);
                    pullToSl.setVisibility(View.VISIBLE);
                    rlTopData.setVisibility(View.VISIBLE);
                    etSendMessageSv.setVisibility(View.VISIBLE);
                    imgRewardClass.setVisibility(View.VISIBLE);
                    rlZanNum.setVisibility(View.VISIBLE);
//                    isShowTitle(true);
                    //恢复跑马灯效果
                    tvChatNum.setSelected(true);
                    pullToSl.setSelected(true);
                    ViewGroup.LayoutParams lp = rlRoot.getLayoutParams();
                    lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    lp.height = (int) (202 * this.getResources().getDisplayMetrics().density + 0.5f);
                    rlRoot.setLayoutParams(lp);
                    // 切换成竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isPortrait = true;
                    imgOrientation.setImageResource(R.mipmap.horizontal_screen);
                } else { //竖屏
                    isPortrait = false;
                    rlName.setVisibility(View.GONE);
                    pullToSl.setVisibility(View.GONE);
                    imgRewardClass.setVisibility(View.GONE);
                    rlTopData.setVisibility(View.GONE);
                    etSendMessageSv.setVisibility(View.GONE);
                    rlZanNum.setVisibility(View.GONE);
//                    isShowTitle(false);
                    ViewGroup.LayoutParams lp = rlRoot.getLayoutParams();
                    lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
                    lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    rlRoot.setLayoutParams(lp);
                    // 切换成横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    imgOrientation.setImageResource(R.mipmap.verticalscreen);
                }

                break;
            //关注
            case R.id.tv_is_focus:
                focusUser();
                break;
            // 开始主播
            case R.id.ll_liver_start:
                // 当开始的时间
                if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getStartTime() - System.currentTimeMillis() < 1000 * 60 * 10
                        && System.currentTimeMillis() <= aliBeginBean.getAppendData().getBaseInfo().getEndTime()) {

                    //startActivity(new Intent(mContext, PushConfigActivity.class));
                    if (AppUtil.isLogin()) {
                        if (NetWorkUtils.getAPNType(Globals.context) != 0) {

                            LivePushActivity.startActivity(this, crid);
//                            //如果是老师的话 finish
//                            if (String.valueOf(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getUmiid()).equals(Globals.USER_UMIID)) {
//                                finish();
//                            }
                        } else {
                            GoldToastUtil.showGoldToast(this, getString(R.string.no_network));
                        }

                    } else
                        GoldToastUtil.showGoldToast(this, getString(R.string.plase_login));


                } else {
                    //不再直播时间nei
                    if (null != aliBeginBean && aliBeginBean.getAppendData().getBaseInfo().getStartTime() - System.currentTimeMillis() > 1000 * 60 * 10) {
                        if (!AlivePlayerActivity.this.isFinishing()) {
                            dialog = new MyDialog(this, getString(R.string.no_live_time)
                                    + TimeUtil.getStrTime(aliBeginBean
                                    .getAppendData().getBaseInfo().getStartTime() - (1000 * 60 * 10) + "", "MM月dd日HH时mm分"), "");
                            dialog.setOne();
                            dialog.show();
                            dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                        }
                        //直播时间以过去   无用
                    } else if (null != aliBeginBean && System.currentTimeMillis() >= aliBeginBean.getAppendData().getBaseInfo().getEndTime()) {
                       /* if (!AlivePlayerActivity.this.isFinishing()) {
                            dialog = new MyDialog(this, getString(R.string.live_time_going), "");
                            dialog.show();
                            dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                        }*/
                    }

                }

                break;
            //点赞动画
            case R.id.rl_zan_num:
                zanSum++;
                tvZanNum.setText(zanSum + "");
                goodOrSendMessgae("1");
                addPraiseWithCallback();
                break;
            //父布局
            case R.id.ll_parent:
                break;
            case R.id.img_reward_class:
                rewardView = new RewardView(this, llParent, aliBeginBean.getAppendData().getBaseInfo().getTeacher().getName()
                        , String.valueOf(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getUmiid()),
                        aliBeginBean.getAppendData().getBaseInfo().getTeacher().getHeadImg());
                rewardView.show();
                rewardView.setOnRewardItemClickListener(this);
                break;
        }
    }


    //    type 1 点赞  2 发送消息
    private void goodOrSendMessgae(String type) {
        liveParams.put("type", type);
        liveParams.put("msg", etMessage.getText().toString().trim());
        aliPayerModel.goodOrMessage(this, "GOOD_OR_SEND_MESSAGE",
                liveParams, false,
                false, false, null);
    }


    private void savePlayerState() {
        if (null != aliyunVodPlayer) {

            aliyunVodPlayer.pause();
            imgPlay.setImageResource(R.mipmap.vod_play_start);

        }
    }

    //初始化屏幕信息
    private void initScreenInfo() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    // 初始化屏幕亮度状态
    private void initLightState() {
        ContentResolver resolver = getContentResolver();
        try {
            currentLight = Settings.System.getFloat(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    // 初始化屏幕亮度状态
    private void initVoiceState() {
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 获取系统最大音量
        currentVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
    }


    //准备视频
    private void preparePlay() {
//        url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            AliyunLocalSource.AliyunLocalSourceBuilder asb
                    = new AliyunLocalSource.AliyunLocalSourceBuilder();
            asb.setSource(url);
            aliyunVodPlayer.prepareAsync(asb.build());
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        firstScroll = true;// 设定是触摸屏幕后第一次scroll的标志
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    int c;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        float startX = e1.getX();
        float startY = e1.getY();
        float endX = e2.getX();
        float endY = e2.getY();
        if (firstScroll) { // 以触摸屏幕后第一次滑动为标准，避免在屏幕上操作切换混乱
            // 横向的距离变化大则调整进度，纵向的变化大则调整音量
            if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                if (!isLiveing) {
                    gestureProgressLayout.setVisibility(View.VISIBLE);
                } else {
                    gestureProgressLayout.setVisibility(View.GONE);
                }

                gestureVolumeLayout.setVisibility(View.GONE);
                gestureBrightLayout.setVisibility(View.GONE);
                GESTURE_FLAG = GESTURE_MODIFY_PROGRESS;
            } else {
                if (startX > screenWidth / 2) {// 音量
                    gestureVolumeLayout.setVisibility(View.VISIBLE);
                    gestureBrightLayout.setVisibility(View.GONE);
                    gestureProgressLayout.setVisibility(View.GONE);

                    GESTURE_FLAG = GESTURE_MODIFY_VOLUME;
                } else {// 亮度
                    gestureBrightLayout.setVisibility(View.VISIBLE);
                    gestureVolumeLayout.setVisibility(View.GONE);
                    gestureProgressLayout.setVisibility(View.GONE);
                    GESTURE_FLAG = GESTURE_MODIFY_BRIGHT;
                }
            }
        }
        // 如果每次触摸屏幕后第一次scroll是调节进度，那之后的scroll事件都处理音量进度，直到离开屏幕执行下一次操作
        if (GESTURE_FLAG == GESTURE_MODIFY_PROGRESS) {
            // 判断是不是回放
            if (!isLiveing) {
                // distanceX=lastScrollPositionX-currentScrollPositionX，因此为正时是快进
                if (Math.abs(distanceX) > Math.abs(distanceY)) {   // 横向移动大于纵向移动
                    if (distanceX >= AppUtil.dp2px(this, STEP_PROGRESS)) { // 快退，用步长控制改变速度，可微调
                        gestureIvProgress.setImageResource(R.mipmap.souhu_player_backward);
                        //curPosition = (int) (curPosition - curPosition * (startX - endX) / screenHeight);
                        c = (int) (curPosition - duration * 0.25 * (startX - endX) / screenWidth);
                    } else if (distanceX <= -AppUtil.dp2px(this, STEP_PROGRESS)) { // 快进
                        gestureIvProgress.setImageResource(R.mipmap.souhu_player_forward);
                        //curPosition = (int) (curPosition + (duration - curPosition) * (endX - startX) / screenHeight);
                        c = (int) (curPosition + duration * 0.25 * (endX - startX) / screenWidth);
                    }
                    if (c < 0) {
                        c = 0;
                    }
                    if (c > duration) {
                        c = duration;
                    }
                    seekbarProgress.setProgress(c);
                    aliyunVodPlayer.seekTo(c);
                    getureTvProgressTime.setText(TimeUtil.getTimeStr(c / 1000) +
                            "/" + TimeUtil.getTimeStr(duration / 1000));
                }
            }


        }
        // 如果每次触摸屏幕后第一次scroll是调节音量，那之后的scroll事件都处理音量调节，直到离开屏幕执行下一次操作
        else if (GESTURE_FLAG == GESTURE_MODIFY_VOLUME) {
            currentVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
            if (Math.abs(distanceY) > Math.abs(distanceX)) {// 纵向移动大于横向移动
                if (distanceY >= AppUtil.dp2px(this, STEP_VOLUME)) {// 音量调大,注意横屏时的坐标体系,尽管左上角是原点，但横向向上滑动时distanceY为正
                    if (currentVolume < maxVolume) {// 为避免调节过快，distanceY应大于一个设定值
                        currentVolume++;
                    }
                    gestureIvPlayerVolume.setImageResource(R.mipmap.souhu_player_volume);
                } else if (distanceY <= -AppUtil.dp2px(this, STEP_VOLUME)) {// 音量调小
                    if (currentVolume > 0) {
                        currentVolume--;
                        if (currentVolume == 0) {// 静音，设定静音独有的图片
                            gestureIvPlayerVolume.setImageResource(R.mipmap.souhu_player_silence);
                        }
                    }
                }
                int percentage = (currentVolume * 100) / maxVolume;
                getureTvVolumePercentage.setText(percentage + "%");
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        }
        // 如果每次触摸屏幕后第一次scroll是调节亮度，那之后的scroll事件都处理亮度调节，直到离开屏幕执行下一次操作
        else if (GESTURE_FLAG == GESTURE_MODIFY_BRIGHT) {
            gestureIvPlayerBright.setImageResource(R.mipmap.souhu_player_bright);
            if (startY - endY > 20) {
                currentLight = (currentLight + (255 - currentLight)
                        * (startY - endY) / screenHeight);
                getureTvBrightPercentage.setText(Math.round(currentLight * 100 / 255) + "%");
                WindowManager.LayoutParams params = getWindow()
                        .getAttributes();
                params.screenBrightness = currentLight / 255f;
                getWindow().setAttributes(params);
            } else if (endY - startY > 20) {
                currentLight = (int) (currentLight - currentLight
                        * (endY - startY) / screenHeight);
                getureTvBrightPercentage.setText(Math.round(currentLight * 100 / 255) + "%");
                WindowManager.LayoutParams params = getWindow()
                        .getAttributes();
                params.screenBrightness = currentLight / 255f;
                getWindow().setAttributes(params);
            }
        }
        firstScroll = false;// 第一次scroll执行完成，修改标志

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    float DownX, DownY, moveX, moveY;
    long currentMS;
    boolean isMove;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                c = curPosition;
                isMove = false;
                DownX = event.getX();//float DownX
                DownY = event.getY();//float DownY
                moveX = 0;
                moveY = 0;
                currentMS = System.currentTimeMillis();//long currentMS     获取系统时间
                break;
            case MotionEvent.ACTION_MOVE:
                moveX += Math.abs(event.getX() - DownX);//X轴距离
                moveY += Math.abs(event.getY() - DownY);//y轴距离
                DownX = event.getX();
                DownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                GESTURE_FLAG = 0;// 手指离开屏幕后，重置调节音量或进度的标志
                gestureVolumeLayout.setVisibility(View.GONE);
                gestureBrightLayout.setVisibility(View.GONE);
                gestureProgressLayout.setVisibility(View.GONE);
                long moveTime = System.currentTimeMillis() - currentMS;//移动时间
                //判断是否继续传递信号
                if ((moveX > 20 || moveY > 20)) {
                    isMove = true;
                }
                break;
        }
        if (isMove) {
            return true;
        }
        return gestureDetector.onTouchEvent(event);
        //不再执行后面的事件，在这句前可写要执行的触摸相关代码。点击事件是发生在触摸弹起后

    }

    private ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX onNewMessagesBean;
    private AliPlayPersonSum aliPlayPersonSum; ///听课次数更新、用户列表更新

    //IM 收到消息
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

                                            if (null != aliPlayPersonSum.getContent().getData()
                                                    && aliPlayPersonSum.getContent().getData().size() >= 1
                                                    && null != aliPlayPersonSum.getContent().getData().get(0).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(0).getHeadimg(), imgPersonal1);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData()
                                                    && aliPlayPersonSum.getContent().getData().size() >= 2 && null != aliPlayPersonSum.getContent().getData().get(1).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(1).getHeadimg(), imgPersonal2);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData()
                                                    && aliPlayPersonSum.getContent().getData().size() >= 3 && null != aliPlayPersonSum.getContent().getData().get(2).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(2).getHeadimg(), imgPersonal3);
                                            }
                                            if (null != aliPlayPersonSum.getContent().getData()
                                                    && aliPlayPersonSum.getContent().getData().size() >= 4
                                                    && null != aliPlayPersonSum.getContent().getData().get(3).getHeadimg()) {
                                                GlideUtil.loadRoundPortraits(aliPlayPersonSum.getContent()
                                                        .getData().get(3).getHeadimg(), imgPersonal4);
                                            }
                                            listenPersonSum.setText(getString(R.string.listen_class_sum)
                                                    + aliPlayPersonSum.getContent().getSeeNum());

                                            break;
                                        //点赞
                                        case "3":
                                            JSONObject zanJson = jsonObject.getJSONObject("content");
                                            if (zanJson.getInt("zanNum") > zanSum) {
                                                zanSum = zanJson.getInt("zanNum");
                                                tvZanNum.setText(zanJson.getInt("zanNum") + "");
                                            }
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
                                        //开始直播通知
                                        case "5":  // 还得改进

                                            JSONObject startJson = jsonObject.getJSONObject("content");
                                            if (startJson.has("pullUrl")) {
                                                url = startJson.getString("pullUrl");
                                            }

                                            // 没有url是开始直播
                                            if (null != aliyunVodPlayer) {
                                                mPlayerState = aliyunVodPlayer.getPlayerState();
                                                // 如果处于直播状态 ， 用户等待直播
                                                if (mPlayerState == Idle || mPlayerState == Stopped ||
                                                        mPlayerState == Completed) {
                                                    mAutoPlay = true;
                                                    preparePlay();
                                                    imgPlay.setImageResource(R.mipmap.vod_play_stop);
                                                    // 如果是播放状态
                                                } else if (mPlayerState == IAliyunVodPlayer.PlayerState.Started
                                                        || mPlayerState == IAliyunVodPlayer.PlayerState.Paused) {
//                                            aliyunVodPlayer.pause();
//                                            imgPlay.setImageResource(R.mipmap.vod_play_start);
//                                            if (loadingPbar.getVisibility() == View.VISIBLE)
//                                                loadingPbar.setVisibility(View.GONE);
//                                            if (surfaceBg.getVisibility() == View.VISIBLE)
//                                                surfaceBg.setVisibility(View.GONE);

                                                    aliyunVodPlayer.reset();
                                                    initSurface();
                                                    preparePlay();
                                                    aliyunVodPlayer.replay();
                                                    isrlRootListener = false;
                                                    progressLayout.setVisibility(View.GONE);
                                                    imgPlay.setVisibility(View.GONE);
                                                    imgOrientation.setVisibility(View.VISIBLE);

                                                    if (!aliyunVodPlayer.isPlaying()) {
                                                        loadingPbar.setVisibility(View.VISIBLE);
                                                        imgPlay.setImageResource(R.mipmap.vod_play_stop);
                                                    } else {
                                                        loadingPbar.setVisibility(View.GONE);
                                                        surfaceBg.setVisibility(View.GONE);

                                                        imgPlay.setImageResource(R.mipmap.vod_play_start);
                                                    }

                                                } else {
                                                    preparePlay();
                                                    aliyunVodPlayer.start();
                                                    showVideoProgressInfo();
                                                    setWingetVisibility(false);
                                                    if (!aliyunVodPlayer.isPlaying()) {
                                                        loadingPbar.setVisibility(View.VISIBLE);
                                                        imgPlay.setImageResource(R.mipmap.vod_play_stop);
                                                    } else {
                                                        loadingPbar.setVisibility(View.GONE);
                                                        surfaceBg.setVisibility(View.GONE);

                                                        imgPlay.setImageResource(R.mipmap.vod_play_start);
                                                    }
                                                }
                                            } else {
                                                initVideo();
                                                if (null != surfaceHolder)
                                                    aliyunVodPlayer.setDisplay(surfaceHolder);
                                                preparePlay();
                                            }
                                            //取消surfaceVIew父布局 的监听
                                            rlRoot.setOnClickListener(null);
                                            isOrientation = true;
                                            imgOrientation.setVisibility(View.VISIBLE);
                                            break;
                                        //老师结束直播通知
                                        case "6":
                                            //不是讲师  弹出
                                            if (aliBeginBean.getAppendData().getBaseInfo().getRole() != 3) {
                                                JSONObject endJson = jsonObject.getJSONObject("content");
                                                if (endJson.has("notice")) {
                                                    if (!AlivePlayerActivity.this.isFinishing()) {
                                                        dialog = new MyDialog(this, endJson.getString("notice"), "");
                                                        dialog.setOne();
                                                        dialog.show();
                                                        dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                dialog.dismiss();
                                                                finish();
                                                            }
                                                        });
                                                    }
                                                }


                                            }

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

    /**
     * 关注
     */

    private void focusUser() {
        if (AppUtil.isLogin()) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("umiid", Globals.USER_UMIID);
                jsonObject.put("client", Globals.CLIENT);
                jsonObject.put("utoken", Globals.USER_UTOKEN);
                if (null != aliBeginBean)
                    jsonObject.put("targetumiid", aliBeginBean.getAppendData()
                            .getBaseInfo().getTeacher().getUmiid());
                if (null != aliBeginBean)
                    if (aliBeginBean.getAppendData().getBaseInfo().getTeacher().getIsFocus() == 1) {
                        jsonObject.put("type", "1");
                    } else {
                        jsonObject.put("type", "0");
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            homeModel.focusUser(this, "FOCUS_USER", jsonObject.toString(), true, true, false, null);
        } else {
            GoldToastUtil.showGoldToast(this, getString(R.string.plase_login));
        }
    }

    boolean isShow = false; //当跳转返回防止黑频

    //初始化SurfaceView
    private void initSurface() {

        mSurfaceView.setZOrderOnTop(false);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (!isShow) {
                    holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
                    holder.setKeepScreenOn(true);
                    surfaceHolder = holder;
                    initVideo();
                } else {

                    aliyunVodPlayer.resume();
                    startUpdateTimer();
                    imgPlay.setImageResource(R.mipmap.vod_play_stop);
                }

                aliyunVodPlayer.setDisplay(holder);
//                preparePlay();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                aliyunVodPlayer.surfaceChanged();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {


            }
        });

    }

    // 初始化  aliyunVodPlayer 信息
    private void initVideo() {
        aliyunVodPlayer = new AliyunVodPlayer(this);
        aliyunVodPlayer.setReferer(Globals.LIVE_SECURITY_CHAIN);
        aliyunVodPlayer.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                //准备完成触发
                if (mAutoPlay) {
                    aliyunVodPlayer.start();
                    showVideoProgressInfo();
                    setWingetVisibility(false);
                    imgPlay.setImageResource(R.mipmap.vod_play_stop);
                }
                mAutoPlay = false;
                LogUtil.e("playsss", "onPrepared");
            }
        });
        aliyunVodPlayer.setOnFirstFrameStartListener(new IAliyunVodPlayer.OnFirstFrameStartListener() {
            @Override
            public void onFirstFrameStart() {
                if (loadingPbar.getVisibility() == View.VISIBLE)
                    loadingPbar.setVisibility(View.GONE);
                if (surfaceBg.getVisibility() == View.VISIBLE)
                    surfaceBg.setVisibility(View.GONE);
                if (isShow) {
                    isShow = false;
                }
                //首帧显示触发
                LogUtil.e("playsss", "onFirstFrameStart");
            }
        });
        aliyunVodPlayer.setOnErrorListener(new IAliyunVodPlayer.OnErrorListener() {
            @Override
            public void onError(int arg0, int arg1, String msg) {
                //出错时处理，查看接口文档中的错误码和错误消息
                //打点
                LogUtil.e("playsss", "setOnErrorListener");
            }
        });
        aliyunVodPlayer.setOnLoadingListener(new IAliyunVodPlayer.OnLoadingListener() {
            @Override
            public void onLoadStart() {
                LogUtil.e("playsss", "onLoadStart");
                loadingPbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadEnd() {
                LogUtil.e("playsss", "onLoadEnd");
                loadingPbar.setVisibility(View.GONE);
                if (surfaceBg.getVisibility() == View.VISIBLE)
                    surfaceBg.setVisibility(View.GONE);

            }

            @Override
            public void onLoadProgress(int i) {

            }
        });
        aliyunVodPlayer.setOnBufferingUpdateListener(new IAliyunVodPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(int i) {
                LogUtil.e("playsss", "onBufferingUpdate" + i);
            }
        });
        aliyunVodPlayer.setOnCompletionListener(new IAliyunVodPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                //播放正常完成时触发
                imgPlay.setImageResource(R.mipmap.vod_play_start);
                stopUpdateTimer();
                LogUtil.e("playsss", "onCOmpletion");
            }
        });
        aliyunVodPlayer.setOnSeekCompleteListener(new IAliyunVodPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete() {
                //seek完成时触发
                LogUtil.e("playsss", "onSeekComplete");
            }
        });
        aliyunVodPlayer.setOnStoppedListner(new IAliyunVodPlayer.OnStoppedListener() {
            @Override
            public void onStopped() {
                //使用stop功能时触发
                LogUtil.e("playsss", "onStopped");
            }
        });
        aliyunVodPlayer.setOnChangeQualityListener(new IAliyunVodPlayer.OnChangeQualityListener() {
            @Override
            public void onChangeQualitySuccess(String finalQuality) {
                //视频清晰度切换成功后触发

            }

            @Override
            public void onChangeQualityFail(int code, String msg) {
                //视频清晰度切换失败时触发
            }
        });
    }

    // 进度条的改变
    private void showVideoProgressInfo() {
        if ((aliyunVodPlayer.getPlayerState().equals(AliyunVodPlayer.PlayerState.Started)
                || aliyunVodPlayer.getPlayerState().equals(AliyunVodPlayer.PlayerState.Replay) ||
                aliyunVodPlayer.getPlayerState().equals(Completed))) {
            curPosition = (int) aliyunVodPlayer.getCurrentPosition();
            tvCurrentPosition.setText(TimeUtil.getTimeStr((int) (Math.ceil((double) curPosition / 1000))));
            duration = (int) aliyunVodPlayer.getDuration();
            tvTotalDuration.setText(TimeUtil.getTimeStr((int) Math.ceil(duration / 1000)));
            int bufferPosition = aliyunVodPlayer.getBufferingPosition();
            seekbarProgress.setMax(duration);
            seekbarProgress.setSecondaryProgress(bufferPosition);
            if (duration - curPosition < 1000) {
                seekbarProgress.setProgress(duration);
            } else {
                seekbarProgress.setProgress(curPosition);
            }
        }

        startUpdateTimer();
    }


    private void startUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
        progressUpdateTimer.sendEmptyMessageDelayed(0, 1000);
    }

    private void stopUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
    }

    //设置所有控件的显示隐藏
    private void setWingetVisibility(boolean b) {
        if (aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 2) {
            if (!b) {
                progressLayout.setVisibility(View.GONE);
                imgOrientation.setVisibility(View.GONE);
                imgPlay.setVisibility(View.GONE);
            } else {
                progressLayout.setVisibility(View.VISIBLE);
                imgOrientation.setVisibility(View.VISIBLE);
                imgPlay.setVisibility(View.VISIBLE);
            }
        } else {
            if (!isrlRootListener) {
                imgOrientation.setVisibility(View.GONE);
            }
        }
        // 回放下的直播
        if (isLiveing && aliBeginBean.getAppendData().getBaseInfo().getLiveStatus() == 2) {
            if (!b) {
                progressLayout.setVisibility(View.GONE);
                imgOrientation.setVisibility(View.GONE);
                imgPlay.setVisibility(View.GONE);
            } else {
                progressLayout.setVisibility(View.VISIBLE);
                imgOrientation.setVisibility(View.VISIBLE);
                imgPlay.setVisibility(View.VISIBLE);
            }
        }
        if (isOrientation) {
            imgOrientation.setVisibility(View.VISIBLE);
        }
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

    private Bitmap getHeartBitmap() {
        final int id = HEARDS[new Random().nextInt(HEARDS.length)];
        SoftReference<Bitmap> bitmapRef = mBitmapCacheArray.get(id);
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

    //判断是不是老师本人
    public boolean isOneSelf() {
        if (null != aliBeginBean)
            if (String.valueOf(aliBeginBean.getAppendData().getBaseInfo().getTeacher().getUmiid())
                    .equals(Globals.USER_UMIID)) {
                return true;
            }
        return false;
    }

    public void isNetWork() {
//        没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
        switch (NetWorkUtils.getAPNType(Globals.context)) {
            case 0:
                break;
            case 4:
            case 3:
            case 2:
                if (!AlivePlayerActivity.this.isFinishing()) {
                    dialog = new MyDialog(this, getString(R.string.traffic_data), "");
                    dialog.show();
                    dialog.setCanel(getString(R.string.cencel), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    dialog.setOk(getString(R.string.confirm), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //判断IM是否登录
                            initIM();
                            //初始化网络请求
                            initRequest();
                            dialog.dismiss();

                        }
                    });
                }
                break;
            case 1:
                //判断IM是否登录
                initIM();
                //初始化网络请求
                initRequest();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PUSH && resultCode == 0) {
            finish();
        }
    }

    // 播放端  暂停和播放  按钮的监听
    private void imgPlayMethod() {
        if (null != aliyunVodPlayer) {
            mPlayerState = aliyunVodPlayer.getPlayerState();
            if (mPlayerState == Idle || mPlayerState == Stopped ||
                    mPlayerState == Completed) {
                mAutoPlay = true;

                preparePlay();
                imgPlay.setImageResource(R.mipmap.vod_play_stop);
            } else if (mPlayerState == IAliyunVodPlayer.PlayerState.Started) {
                aliyunVodPlayer.pause();
                imgPlay.setImageResource(R.mipmap.vod_play_start);
                if (loadingPbar.getVisibility() == View.VISIBLE)
                    loadingPbar.setVisibility(View.GONE);
                if (surfaceBg.getVisibility() == View.VISIBLE)
                    surfaceBg.setVisibility(View.GONE);

            } else if (mPlayerState == IAliyunVodPlayer.PlayerState.Paused) {
                aliyunVodPlayer.resume();
                setWingetVisibility(false);
                imgPlay.setImageResource(R.mipmap.vod_play_stop);
                if (loadingPbar.getVisibility() == View.VISIBLE)
                    loadingPbar.setVisibility(View.GONE);
                if (surfaceBg.getVisibility() == View.VISIBLE)
                    surfaceBg.setVisibility(View.GONE);

            } else {

//                preparePlay();
//                aliyunVodPlayer.resume();
//                aliyunVodPlayer.start();
                aliyunVodPlayer.reset();
                initSurface();
                preparePlay();
                aliyunVodPlayer.replay();
                showVideoProgressInfo();
                setWingetVisibility(false);
                if (!aliyunVodPlayer.isPlaying()) {
                    loadingPbar.setVisibility(View.VISIBLE);
                    imgPlay.setImageResource(R.mipmap.vod_play_stop);
                } else {
                    loadingPbar.setVisibility(View.GONE);
                    imgPlay.setImageResource(R.mipmap.vod_play_start);
                    surfaceBg.setVisibility(View.GONE);

                }
            }
        } else {
            initVideo();
            if (null != surfaceHolder)
                aliyunVodPlayer.setDisplay(surfaceHolder);
            preparePlay();
        }
    }

    @Override
    public void OnRewardItemClick(String num) {

        //确认打赏回调
        JSONObject jsonRoot = new JSONObject();
        try {
            jsonRoot.put("umiid", Globals.USER_UMIID);
            jsonRoot.put("utoken", Globals.USER_UTOKEN);
            jsonRoot.put("client", Globals.CLIENT);
            jsonRoot.put("paytype", "2");
            jsonRoot.put("ordertype", "1");
            jsonRoot.put("receiveumiid", aliBeginBean.getAppendData().getBaseInfo().getTeacher().getUmiid());
            jsonRoot.put("sumprice", num);
            jsonRoot.put("crid", crid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        homeModel.reward(this, "COURSE_BUY_ORDER", jsonRoot.toString(), true, true, false, null);
    }

    //微信支付
    private void initWeiXinPay() {
//        msgApi = WXAPIFactory.createWXAPI(this, null);
//        if (msgApi.registerApp(Globals.WEIXIN_APPID)) {
//            Log.i("CoursePayAct", "向微信APP注册成功");
//        }
//        initHandler();
//        EventBus.getDefault().register(this);
    }

    private void initHandler() {

       /* handlers = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == AppMessageValues.REWARD_PURCHASE_WEIXIN_CREATE_ORDER_SUCC) {
                    LogUtil.e("CoursePayAct", "调用微信APP发起支付");
                    PayReq request = new PayReq();
                    request.appId = Globals.WEIXIN_APPID;
                    request.partnerId = o2Bean.getMchid();
                    request.prepayId = o2Bean.getPrepayid();
                    request.packageValue = "Sign=WXPay";
                    request.nonceStr = o2Bean.getNoncestr();
                    request.timeStamp = o2Bean.getTimestamp();
                    request.sign = o2Bean.getSign();
                    request.extData = "class";
                    boolean reqSucc = msgApi.sendReq(request);
                    if (reqSucc) {
                        Log.i("CoursePayAct", "调用true");
                    } else {
                        Log.i("CoursePayAct", "调用fail");
                    }
                }
            }
        };*/

    }

   /* @Subscribe
    public void onEventMainThread(MessageEvent event) {

        if (event.getmMsg().equals("class")) {  //支付成功,核对账单
            OrderUtil.AffirmOrder((Activity) mContext, orderNum);
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!isPortrait) { //横屏 转换 竖屏
                rlName.setVisibility(View.VISIBLE);
                pullToSl.setVisibility(View.VISIBLE);
                rlTopData.setVisibility(View.VISIBLE);
                etSendMessageSv.setVisibility(View.VISIBLE);
                imgRewardClass.setVisibility(View.VISIBLE);
                rlZanNum.setVisibility(View.VISIBLE);
//                isShowTitle(true);
                //恢复跑马灯效果
                tvChatNum.setSelected(true);
                pullToSl.setSelected(true);
                ViewGroup.LayoutParams lp = rlRoot.getLayoutParams();
                lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                lp.height = (int) (202 * this.getResources().getDisplayMetrics().density + 0.5f);
                rlRoot.setLayoutParams(lp);
                // 切换成竖屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isPortrait = true;
                imgOrientation.setImageResource(R.mipmap.horizontal_screen);

            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
