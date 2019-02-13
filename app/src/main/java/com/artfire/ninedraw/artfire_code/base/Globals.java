package com.artfire.ninedraw.artfire_code.base;

import android.content.Context;

import com.artfire.ninedraw.artfire_code.Bean.UserDataBean;

/**
 * Created by 54hk on 2019/1/29.
 */

public class Globals {
    public static final Boolean IS_0PEN_BUGLY_CRASH = false;   //是否开启BUGLY
    public static boolean HAVE_LOG = true;  //打印日志
    public static String TAG = "YIHUO_TAG";
    public static Context context;
    public static String CLIENT = "111";     //客户端
    public static String SERVICE = "100";     //服务器端
    public static String VERSION = "4.1";      //版本号
    public static String OSS_HEAD_KEY = "Referer";    //OSS请求头的key
    public static String OSS_HEAD_VALUE = "http://www.artfire.com.cn";    //OSS请求头的value

    public static String ALL_STUDENT_PAGE_SIZE = "50";  //全部学员每页请求数

    public static String LIVE_SECURITY_CHAIN = "http://artfire.android.com";//直播回放防盗链

    public static UserDataBean userDataBean;
    public static final int SDK_APPID = 1400017106; //sdk appid 由腾讯分配
    public static String TEACHER_PAGE_SIZE = "300";  //直播历史记录 300
    public static String USER_UMIID = "95897";//用户id
    public static String USER_UTOKEN = "1eb581a11fffefcbbc39d0d3bca2bcd1";//临时签名
    public static String USER_UMID = "";//艺伙号
    public static String USER_UMALIAS = "";//用户昵称
    public static String USER_UMSMALLPIC = "";//艺伙用户小头像
    public static String USER_TIMUSERSIG = "";//头像云用户签名
    public static String USER_HASAUTHORITY = "0";//1至少一节课有教师或管理员权限
    public static String USER_UMLARGEPIC = "";//用户头像原图
    public static String USER_UMFAVORNUM = "";//点赞次数
    public static String USER_O1 = "";//登录返回用户信息 o1
    public static String USER_WX_ICON = "";//微信头像
    public static String USER_SEX = "2";//性别  0女  1男  2保密
    public static String PAYNUMCRS = "";//用户购买系列课数量
    public static String PAYNUMSC = "";//用户购买专栏数量
    public static String PAYNUMCR1 = "";//用户购买公开课数量


    //没用到

    public static String USER_JOB = "";//职业
    public static String IS_OPEN_WALLET = "";//是否显示钱包
    public static String USER_ISTEACHER = "";//是否是老师
}
