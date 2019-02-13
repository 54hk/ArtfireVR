package com.artfire.ninedraw.artfire_code.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.artfire.ninedraw.artfire_code.base.Globals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuanbo.wang on 2015/12/2.
 */
public class AppUtil {
    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取设备imei
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();

    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }

    }


    /**
     * 判断网络状态
     *
     * @return
     */
    public static boolean networkCheck() {
        // WIFI处于连接
        boolean isWIFI = isWIFIConnectivity();
        // Mobile连接
        boolean isMobile = isMobileConnectivity(Globals.context);
        return isWIFI || isMobile;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isMobileConnectivity(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * WIFI是否处于连接
     *
     * @param
     * @return
     */
    public static boolean isWIFIConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) Globals.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 验证手机号：
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1(3[0-9]|4[0-9]|5[0-9]|7[0-9]|8[0-9])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 验证邮箱：
     */
    public static boolean isEmail(String strEmail) {
//		String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        String strPattern = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 身份证验证：
     */
    public static boolean isID(String s) {
        String strPattern = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 手机上安装几个应用市场
     *
     * @param context
     * @return
     */
    private int getMarket(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        int size = infos.size();
//        for (int i = 0; i < size; i++) {
//            ActivityInfo activityInfo = infos.get(i).activityInfo;
//            String packageName = activityInfo.packageName;
//            Log.i("==============", "packageName : " + packageName);
//        }
        return size;
    }


    public static String format2LenStr(int num) {

        return (num < 10) ? "0" + num : String.valueOf(num);
    }

    /**
     * 检测照相机权限
     *
     * @return
     */
    public static boolean isPermission() {
        PackageManager pm = Globals.context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA", "com.fufang.zhixuan"));
        return permission;
    }


    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    //版本名
    public static String getVersionName() {
        return getPackageInfo(Globals.context).versionName;
    }

    //版本号
    public static int getVersionCode() {
        return getPackageInfo(Globals.context).versionCode;
    }


    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }


    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public static void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    public static String FormatDouble(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    public static boolean isLogin() {
        //TODO 判断是不是登陆
//        if (TextUtils.isEmpty(Globals.USER_UMIID) || TextUtils.isEmpty(Globals.USER_UMIID)) {
//            return false;
//        } else
            return true;
    }

    //提示  登录
    public static void startLogin(final Activity activity) {
        final MyDialog dialog = new MyDialog(activity, "您还没有登录", "是否马上去登录?");
        dialog.show();
        dialog.setCanel("再看看", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOk("去登陆", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转登陆
//                activity.startActivityForResult(new Intent(activity, LoginByPhoneActivity.class), AppMessageValues.LOGIN_BY_PHONE);
                dialog.dismiss();
            }
        });
    }

    public static int px(float dipValue) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 是否使屏幕常亮
     *
     * @param activity
     */
    public static void keepScreenLongLight(Activity activity, boolean isOpenLight) {

        Window window = activity.getWindow();
        if (isOpenLight) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

    }

    /**
     * 赞数 和 评论数   1k 1w
     *
     * @param number
     * @return
     */
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static String numberToStr(double number) {
        if ((int) number <= 9999) {
            return (int) number + "";
        }
//        else if (number > 999 && number <= 9999) {
//            if (!df.format(number / 1000).substring(df.format(number / 1000).length() - 1
//                    , df.format(number / 1000).length()).equals("0"))
//                return df.format(number / 1000) + "k";
//            else
//                return (int)(number / 1000) + "k";
//        }
        else {
//            if (!df.format(number / 10000).substring(df.format(number / 10000).length() - 1
//                    , df.format(number / 10000).length()).equals("0"))
            return df.format(number / 10000) + "w";
//            else
//                return (int) (number / 10000) + "w";
        }

    }
}
