package com.artfire.ninedraw.vuforia_lib.tools;

import android.util.Log;

/**
 * 作者:李少波
 * 邮箱:lishaobo@com.seengene.arguide.seengene.com
 * 日期:16/9/14
 */
public class LogUtil {
    public static boolean isDebug = true;

    public static void v(String tag, String msg) {
        if (isDebug) Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug) Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) Log.e(tag, msg);
    }
}
