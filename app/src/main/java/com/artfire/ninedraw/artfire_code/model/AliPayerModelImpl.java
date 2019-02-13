package com.artfire.ninedraw.artfire_code.model;

import android.app.Activity;

import com.artfire.ninedraw.artfire_code.Bean.ALiHostoryBean;
import com.artfire.ninedraw.artfire_code.Bean.AliBeginBean;
import com.artfire.ninedraw.artfire_code.network.APIProtocol;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.network.MyOkHttpUtils;
import com.artfire.ninedraw.artfire_code.utils.GsonUtils;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by 54hk on 2018/8/12.
 */

public class AliPayerModelImpl implements AliPayerModel {
    @Override
    public void liveHostory(final Activity activity, final String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                ALiHostoryBean alivePlayBean = GsonUtils.json2bean(response,
                        ALiHostoryBean.class);
                ((ApiRequestCallback) activity).analysisData(requesCode, alivePlayBean, id);
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
                ((ApiRequestCallback) activity).errorhandle(requesCode, call, e, id);
            }


        }.getRequest(activity, APIProtocol.LIVE_HOSTORY_URL,
                params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }

    @Override
    public void beginLiveRequest(final Activity activity, final ApiRequestCallback callback, final String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                AliBeginBean aliBeginBean = GsonUtils.json2bean(response, AliBeginBean.class);
                if (null == callback) {
                    ((ApiRequestCallback) activity).analysisData(requesCode, aliBeginBean, id);
                } else {
                    callback.analysisData(requesCode, aliBeginBean, id);
                }

            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
                if (null == callback) {
                    ((ApiRequestCallback) activity).errorhandle(requesCode, call, e, id);
                } else
                    callback.errorhandle(requesCode, call, e, id);
            }


        }.getRequest(activity, APIProtocol.BEGIN_LIVE_URL,
                params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }

    @Override
    public void goodOrMessage(final Activity activity, final String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
                ((ApiRequestCallback) activity).errorhandle(requesCode, call, e, id);
            }


        }.getRequest(activity, APIProtocol.GOOD_OR_SEND_MESSAGE,
                params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }

    @Override
    public void getTimSig(Activity activity, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
//        new MyOkHttpUtils() {
//            @Override
//            public <T> void analysisData(String response, int id) {
//                SigBean bean = GsonUtils.json2bean(response, SigBean.class);
//                LoginBusiness.loginIm(Globals.USER_UMIID, bean.getAppendData().getSig(), new TIMCallBack() {
//                    @Override
//                    public void onError(int i, String s) {
//
//                    }
//
//                    @Override
//                    public void onSuccess() {
//
//                    }
//                });
//            }
//
//            @Override
//            public <T> void errorhandle(Call call, Exception e, int id) {
//
//            }
//        }.getRequest(activity, APIProtocol.GET_IM_SIG, params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }

    @Override
    public void startOrEndPush(final Activity activity, final ApiRequestCallback callback, final String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                AliBeginBean aliBeginBean = GsonUtils.json2bean(response, AliBeginBean.class);
                if (null == callback) {

                } else {
                    callback.analysisData(requesCode, aliBeginBean, id);
                }

            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
                if (null == callback) {

                } else
                    callback.errorhandle(requesCode, call, e, id);
            }


        }.getRequest(activity, APIProtocol.START_END_PUSH,
                params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }
    @Override
    public void reward(final Activity activity, final String requesCode, String params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                ((ApiRequestCallback) activity).analysisData(requesCode, response, id);
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
                ((ApiRequestCallback) activity).errorhandle(requesCode, call, e, id);
            }
        }.postJson(activity, APIProtocol.COURSE_BUY_ORDER, params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }
    @Override
    public void focusUser(final Activity activity, final String requesCode, String params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils(){

            @Override
            public <T> void analysisData(String response, int id) {
                ((ApiRequestCallback)activity).analysisData(requesCode, response, id);
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
            }
        }.postJson(activity, APIProtocol.FOCUS_USER, params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }
}
