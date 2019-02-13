package com.artfire.ninedraw.artfire_code.model;

import android.app.Activity;

import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;

import java.util.Map;

/**
 * Created by 54hk on 2018/8/12.  直播
 */

public interface AliPayerModel {
    // 获取直播聊天的历史记录
    void liveHostory(Activity activity, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    //刚开始进入直播页面的请求
    void beginLiveRequest(Activity activity, ApiRequestCallback callback, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    //点赞  发送消息
    void goodOrMessage(Activity activity, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    //IM登录
    void getTimSig(Activity activity, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    //开播/结束直播接口
    void startOrEndPush(Activity activity, ApiRequestCallback callback, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    public void reward(final Activity activity, final String requesCode, String params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

    public void focusUser(final Activity activity, final String requesCode, String params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);

}
