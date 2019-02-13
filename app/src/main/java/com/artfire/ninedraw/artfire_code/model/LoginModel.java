package com.artfire.ninedraw.artfire_code.model;

import android.app.Activity;

import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by 54hk on 2019/2/13.  登陆
 */

public interface LoginModel {
    // 获取验证码
    void loginGetSmsCode(Activity activity, final ApiRequestCallback callback,String requesCode, JSONObject params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);
    // 注册
    void registerAccount(Activity activity, ApiRequestCallback callback, String requesCode, String url, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);
    //验证码登陆
    void loginByPhone(Activity activity, String requesCode,  Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);
    //修改密码
    void changePassWord(Activity activity, String url, ApiRequestCallback callback, String requesCode, Map params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView);
}
