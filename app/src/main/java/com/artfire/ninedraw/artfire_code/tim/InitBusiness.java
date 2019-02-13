package com.artfire.ninedraw.artfire_code.tim;


import android.content.Context;
import android.util.Log;

import com.artfire.ninedraw.artfire_code.base.Globals;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 初始化
 * 包括imsdk等
 */

public class InitBusiness {

    private static final String TAG = InitBusiness.class.getSimpleName();

    private InitBusiness() {
    }

    public static void start(Context context) {
        initImsdk(context, 0);
    }

    public static void start(Context context, int logLevel) {
        initImsdk(context, logLevel);
    }


    /**
     * 初始化imsdk
     */

    private static void initImsdk(Context context, int logLevel) {
        TIMSdkConfig config = new TIMSdkConfig(Globals.SDK_APPID)
                .enableCrashReport(Globals.IS_0PEN_BUGLY_CRASH);
        //初始化imsdk
        TIMManager.getInstance().init(context, config);
        Log.d(TAG, "initIMsdk");
    }


    /**
     * 用户状态变更
     */

    public static void setUserListener() {
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                //被踢下线时回调

               /* Globals.context.startActivity(new Intent(Globals.context, HomeActivity2.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                SharedPreferencesUtil.removeLoginState(Globals.context);
                final MyDialog dialog = new MyDialog(HomeActivity2.homeContext, Globals.context.getString(R.string.log_back_in_2), "");
                dialog.setOne();
                dialog.show();
                dialog.setOk(Globals.context.getString(R.string.log_back_in), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Globals.context.startActivity(new Intent(Globals.context, LoginByPhoneActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        dialog.dismiss();
                    }
                });*/

            }

            @Override
            public void onUserSigExpired() {
                //票据过期时回调

              /*  Globals.context.startActivity(new Intent(Globals.context, HomeActivity2.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                SharedPreferencesUtil.removeLoginState(Globals.context);
                final MyDialog dialog = new MyDialog(HomeActivity2.homeContext, Globals.context.getString(R.string.online_time_long), "");
                dialog.setOne();
                dialog.show();
                dialog.setOk(Globals.context.getString(R.string.log_back_in), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Globals.context.startActivity(new Intent(Globals.context, LoginByPhoneActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        dialog.dismiss();
                    }
                });*/

            }
        });

        TIMManager.getInstance().setUserConfig(userConfig);
    }


    /**
     * 新消息通知
     */

    public static void addMessageListener() {
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {//消息监听器
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {//收到新消息
                for (int i = 0; i < list.size(); i++) {
                    TIMMessage msg = list.get(i);
                    if (msg.getConversation().getType().toString().equals("C2C")) {
                        //获取当前元素的类型
                        TIMElem elem = msg.getElement(0);
                        TIMElemType elemType = elem.getType();
                        if (elemType == TIMElemType.Custom) {
                            TIMCustomElem custom = (TIMCustomElem) elem;
                            try {
                                String s1 = new String(custom.getData(), "utf-8");
                                //TODO 新消息通知
//                                EventBus.getDefault().post(new MessageEvent(s1));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                return false;
            }
        });

    }

}

