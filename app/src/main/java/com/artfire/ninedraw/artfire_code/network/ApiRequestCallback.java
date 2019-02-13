package com.artfire.ninedraw.artfire_code.network;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public interface ApiRequestCallback {
    void analysisData(String requestCode, Object bean, int id);
    void errorhandle(String requestCode, Call call, Exception e, int id);
}
