package com.artfire.ninedraw.artfire_code.model;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.artfire.ninedraw.artfire_code.Bean.UserDataBean;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.network.APIProtocol;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.network.MyOkHttpUtils;
import com.artfire.ninedraw.artfire_code.utils.GsonUtils;
import com.artfire.ninedraw.artfire_code.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.Call;

import static com.artfire.ninedraw.artfire_code.base.Globals.userDataBean;

/**
 * Created by 54hk on 2019/2/13.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void loginGetSmsCode(final Activity activity, final ApiRequestCallback callback, final String requesCode, JSONObject params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                if (requesCode.equals("GET_CODE")) {  //验证码
                    Log.e("TTT", "发送成功");
                } else if (requesCode.equals("POST_USER_ENTRY_URL")) {  //登陆
                    userDataBean = GsonUtils.json2bean(response, UserDataBean.class);
                    Globals.USER_UMIID = userDataBean.getAppendData().getO1().getUmiid() + "";
                    Globals.USER_UTOKEN = userDataBean.getAppendData().getO1().getUtoken();
                    Globals.USER_UMID = userDataBean.getAppendData().getO1().getUmid();
                    Globals.USER_UMALIAS = userDataBean.getAppendData().getO1().getUmalias();
                    Globals.USER_UMSMALLPIC = userDataBean.getAppendData().getO1().getUmsmallpic();
                    Globals.USER_TIMUSERSIG = userDataBean.getAppendData().getO1().getTimusersig();
                    Globals.USER_UMFAVORNUM = userDataBean.getAppendData().getO1().getUmfavornum();
                    Globals.USER_UMLARGEPIC = userDataBean.getAppendData().getO1().getUmlargepic();
                    Globals.USER_HASAUTHORITY = userDataBean.getAppendData().getO1().getHasauthority();
                    if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumcr1())) {
                        Globals.PAYNUMCR1 = userDataBean.getAppendData().getO1().getPaynumcr1();
                    }
                    if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumcrs())) {
                        Globals.PAYNUMCRS = userDataBean.getAppendData().getO1().getPaynumcrs();
                    }
                    if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumsc())) {
                        Globals.PAYNUMSC = userDataBean.getAppendData().getO1().getPaynumsc();
                    }
                    if (userDataBean.getAppendData().getO1() != null && !TextUtils.isEmpty(userDataBean.getAppendData().getO1().getUmsex())) {
                        Globals.USER_SEX = userDataBean.getAppendData().getO1().getUmsex();
                    }
                    if (userDataBean.getAppendData().getO2() != null && !TextUtils.isEmpty(userDataBean.getAppendData().getO2().getHeadimgurl())) {
                        Globals.USER_WX_ICON = userDataBean.getAppendData().getO2().getHeadimgurl();
                    }
//                if (userDataBean.getAppendData().getO1() != null) {
//                    Globals.USER_O1 = GsonUtils.bean2Json(userDataBean.getAppendData().getO1()).toString();
//                    WebLocUtils.setUserInfo(Globals.USER_O1);
//                }

                    SharedPreferencesUtil.setLoginState(activity);

                    callback.analysisData(requesCode, response, id);
                }
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
            }
        }.postJson(activity, APIProtocol.POST_USER_ENTRY_URL, params, true, true, false, null);
    }

    @Override
    public void registerAccount(Activity activity, final ApiRequestCallback callback, final String requesCode, String url, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                callback.analysisData(requesCode, response, id);
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
            }
        }.getRequest(activity, url, params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }

    @Override
    public void loginByPhone(final Activity activity, String requesCode, Map<String, String> params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                userDataBean = GsonUtils.json2bean(response, UserDataBean.class);
                Globals.USER_UMIID = userDataBean.getAppendData().getO1().getUmiid() + "";
                Globals.USER_UTOKEN = userDataBean.getAppendData().getO1().getUtoken();
                Globals.USER_UMID = userDataBean.getAppendData().getO1().getUmid();
                Globals.USER_UMALIAS = userDataBean.getAppendData().getO1().getUmalias();
               Globals.USER_UMSMALLPIC = userDataBean.getAppendData().getO1().getUmsmallpic();
                Globals.USER_TIMUSERSIG = userDataBean.getAppendData().getO1().getTimusersig();
                Globals.USER_UMFAVORNUM = userDataBean.getAppendData().getO1().getUmfavornum();
                Globals.USER_UMLARGEPIC = userDataBean.getAppendData().getO1().getUmlargepic();
                Globals.USER_HASAUTHORITY = userDataBean.getAppendData().getO1().getHasauthority();
                if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumcr1())) {
                    Globals.PAYNUMCR1 = userDataBean.getAppendData().getO1().getPaynumcr1();
                }
                if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumcrs())) {
                    Globals.PAYNUMCRS = userDataBean.getAppendData().getO1().getPaynumcrs();
                }
                if (!TextUtils.isEmpty(userDataBean.getAppendData().getO1().getPaynumsc())) {
                    Globals.PAYNUMSC = userDataBean.getAppendData().getO1().getPaynumsc();
                }
                if (userDataBean.getAppendData().getO1() != null && !TextUtils.isEmpty(userDataBean.getAppendData().getO1().getUmsex())) {
                    Globals.USER_SEX = userDataBean.getAppendData().getO1().getUmsex();
                }
                if (userDataBean.getAppendData().getO2() != null && !TextUtils.isEmpty(userDataBean.getAppendData().getO2().getHeadimgurl())) {
                    Globals.USER_WX_ICON = userDataBean.getAppendData().getO2().getHeadimgurl();
                }
//                if (userDataBean.getAppendData().getO1() != null) {
//                    Globals.USER_O1 = GsonUtils.bean2Json(userDataBean.getAppendData().getO1()).toString();
//                    WebLocUtils.setUserInfo(Globals.USER_O1);
//                }

                SharedPreferencesUtil.setLoginState(activity);

                activity.finish();
//                activity.startActivity(new Intent(activity, HomeActivity3.class));
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {
            }
        }.getRequest(activity, APIProtocol.GET_LOGIN_BY_CODE, params, true, true, false, null);
    }

    @Override
    public void changePassWord(final Activity activity, String url, ApiRequestCallback callback, final String requesCode, Map params, Boolean isShowLoading, Boolean isCloseLoading, Boolean isShowNeterror, Object isPullView) {
        new MyOkHttpUtils() {
            @Override
            public <T> void analysisData(String response, int id) {
                ((ApiRequestCallback) activity).analysisData(requesCode, response, id);
            }

            @Override
            public <T> void errorhandle(Call call, Exception e, int id) {

            }
        }.getRequest(activity, url, params, isShowLoading, isCloseLoading, isShowNeterror, isPullView);
    }
}
