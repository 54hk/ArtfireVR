package com.artfire.ninedraw.artfire_code.network;

import android.app.Activity;
import android.os.Environment;
import android.text.TextUtils;

import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.utils.AlertUtil;
import com.artfire.ninedraw.artfire_code.utils.AppUtil;
import com.artfire.ninedraw.artfire_code.utils.GoldToastUtil;
import com.artfire.ninedraw.artfire_code.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public abstract class MyOkHttpUtils {
    private AlertUtil alertUtil = AlertUtil.getInstance();
    private JSONObject jsonB;
    private String resultType;
    private String msg;  //错误描述
    private Map<String, String> map = new HashMap<>();
    private MediaType mediaType;
    File sd = Environment.getExternalStorageDirectory();

    /**
     * get请求
     *
     * @param activity
     * @param url            请求路径
     * @param params         请求参数
     * @param isShowLoading  是否显示等待框
     * @param isCloseLoading 是否关闭等待框
     * @param isShowNetError 请求失败是否显示网络错误页面
     */
    public void getRequest(final Activity activity, final String url, Map<String, String> params
            , Boolean isShowLoading, final Boolean isCloseLoading, final Boolean isShowNetError, final Object isPullView) {
        LogUtil.e(Globals.TAG, url + ":" + params.toString());
        if (!AppUtil.networkCheck()) {
            GoldToastUtil.showGoldToast(activity, "网络错误");
//            if (isPullView instanceof PullToRefreshBase) {
//                ((PullToRefreshBase) isPullView).onRefreshComplete();
//            }
//            if (isPullView instanceof BaseQuickAdapter) {
//                ((BaseQuickAdapter) isPullView).loadMoreFail();
//            }

            if (isPullView instanceof SmartRefreshLayout) {
                if (((SmartRefreshLayout) isPullView).isLoading()) {
                    ((SmartRefreshLayout) isPullView).finishLoadmore();
                }
                if (((SmartRefreshLayout) isPullView).isRefreshing())
                    ((SmartRefreshLayout) isPullView).finishRefresh();
            }


            if (isShowNetError && isPullView == null) {
                ((BaseActivity) activity).showNetError();
            }
            return;
        }
        if (isShowLoading && isPullView == null) {
            alertUtil.alert(activity);
        }
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .addParams("client", Globals.CLIENT)
                .addParams("version", Globals.VERSION)
                .tag(activity)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(Globals.TAG, url + "==" + e.toString());

                        if (isShowNetError) {
                            ((BaseActivity) activity).showNetError();
                        }
                        if (isCloseLoading && null != alertUtil) {
                            alertUtil.closeDialog(activity);
                        }

//                        if (isPullView instanceof PullToRefreshBase) {
//                            ((PullToRefreshBase) isPullView).onRefreshComplete();
//                        } else if (isPullView instanceof BaseQuickAdapter) {
//                            ((BaseQuickAdapter) isPullView).loadMoreFail();
//                        } else
                            if (isPullView instanceof SmartRefreshLayout) {
                            if (((SmartRefreshLayout) isPullView).isLoading()) {
                                ((SmartRefreshLayout) isPullView).finishLoadmore();
                            }
                            if (((SmartRefreshLayout) isPullView).isRefreshing())
                                ((SmartRefreshLayout) isPullView).finishRefresh();
                        }
                        errorhandle(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (activity != null) {
                            if (null != activity)
                                ((BaseActivity) activity).closeNetError();
                            if (isCloseLoading && null != alertUtil) {
                                alertUtil.closeDialog(activity);
                            }
//                             if (isPullView instanceof BaseQuickAdapter) {
//                                ((BaseQuickAdapter) isPullView).loadMoreComplete();
//                            } else
                                if (isPullView instanceof SmartRefreshLayout) {
                                if (((SmartRefreshLayout) isPullView).isLoading()) {
                                    ((SmartRefreshLayout) isPullView).finishLoadmore();
                                }
                                if (((SmartRefreshLayout) isPullView).isRefreshing())
                                    ((SmartRefreshLayout) isPullView).finishRefresh();
                            }
                        }
                        if (!TextUtils.isEmpty(response)) {
                            LogUtil.e(Globals.TAG, response);
                            try {
                                jsonB = new JSONObject(response);
                                resultType = jsonB.getString("resultType");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (resultType.equals("1")) { //正确
                                analysisData(response, id);

                            } else { //错误
                                if (isPullView instanceof SmartRefreshLayout) {
                                    if (((SmartRefreshLayout) isPullView).isLoading()) {
                                        ((SmartRefreshLayout) isPullView).finishLoadmore();
                                    }
                                    if (((SmartRefreshLayout) isPullView).isRefreshing())
                                        ((SmartRefreshLayout) isPullView).finishRefresh();
                                }
                                defeated(response, id);
                                try {
                                    msg = jsonB.getString("logMessage");
                                    GoldToastUtil.showGoldToast(activity, msg);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }


    /**
     * post请求
     * loginGetSmsCode
     *
     * @param url            请求路径 @param activity
     * @param params         请求参数
     * @param isShowLoading  是否显示等待框
     * @param isCloseLoading 是否关闭等待框
     * @param isShowNetError 请求失败是否显示网络错误页面
     */
    public void postJson(final Activity activity, String url, Object params, Boolean isShowLoading, final Boolean isCloseLoading, final Boolean isShowNetError, final Object isPullView) {
        LogUtil.e(Globals.TAG, url + ":" + params.toString());
        if (!AppUtil.networkCheck()) {
            GoldToastUtil.showGoldToast(activity, "网络错误");
//            if (isPullView instanceof PullToRefreshBase) {
//                ((PullToRefreshBase) isPullView).onRefreshComplete();
//            }
            if (isShowNetError) {
                ((BaseActivity) activity).showNetError();
            }
            return;
        }
        if (isShowLoading) {
            alertUtil.alert(activity);
        }
        OkHttpUtils
                .postString()
                .url(url)
                .content(params.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(activity)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (isShowNetError) {
                            ((BaseActivity) activity).showNetError();
                        }
                        if (isCloseLoading && null != alertUtil) {
                            alertUtil.closeDialog(activity);
                        }
//                        if (isPullView instanceof PullToRefreshBase) {
//                            ((PullToRefreshBase) isPullView).onRefreshComplete();
//                        }
                        errorhandle(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (null != activity)
                            ((BaseActivity) activity).closeNetError();
                        if (isCloseLoading && null != alertUtil) {
                            alertUtil.closeDialog(activity);
                        }
//                        if (isPullView instanceof PullToRefreshBase) {
//                            ((PullToRefreshBase) isPullView).onRefreshComplete();
//                        }
                        if (!TextUtils.isEmpty(response)) {
                            LogUtil.e(Globals.TAG, response);
                            try {
                                jsonB = new JSONObject(response);
                                resultType = jsonB.getString("resultType");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (resultType.equals("1")) { //正确
                                analysisData(response, id);
                            } else { //错误
                                defeated(response, id);
                                if (resultType.equals("2")) {
                                    return;
                                }
                                try {
                                    msg = jsonB.getString("logMessage");
                                    GoldToastUtil.showGoldToast(activity, msg);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 下载文件
     *
     * @param url
     * @param path     保存路径
     * @param fileName 文件名字
     */
    public void downloadFile(String url, String path, String fileName, Activity activity) {
//        map.put(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE);
        OkHttpUtils//
                .get()//
                .url(url)//
//                .headers(map)
                .tag(activity)
                .build()//
                .execute(new FileCallBack(path, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        downloadErrorFile();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        downloadFile(response, id);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        downloadInProgress(progress, total);
                    }
                });
    }


    public abstract <T> void analysisData(String response, int id);

    public void defeated(String response, int id) {

    }

    public void downloadFile(File response, int id) {

    }

    public void downloadErrorFile() {

    }

    public void downloadInProgress(float progress, long total) {
    }

    public abstract <T> void errorhandle(Call call, Exception e, int id);


}
