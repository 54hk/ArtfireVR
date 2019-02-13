package com.artfire.ninedraw.artfire_code.network;


/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class APIProtocol {
    public static String BASE_URL = "https://test.artfirecn.com/YihuoService/";
    //获取直播的聊天记录
    public static String LIVE_HOSTORY_URL = BASE_URL + "services/live/gethistorychatinfolist";
    // 进入直播首页请求的详情信息
    public static String BEGIN_LIVE_URL = BASE_URL + "services/live/getlivehomepage";
    //点赞和发送消息
    public static String GOOD_OR_SEND_MESSAGE = BASE_URL + "services/live/givelikeorsendmessage";
    //开始或停止直播推流
    public static String START_END_PUSH = BASE_URL + "services/live/beginlive";
    //获取IM  sig
    public static String GET_IM_SIG = BASE_URL + "services/user/gettimusersig?";

    // 艺课 申请预支付订单
    public static String COURSE_BUY_ORDER = BASE_URL + "services/commodity/createorder2.0";

    // 关注/取消关注
    public static String FOCUS_USER = BASE_URL + "services/user2/focususer3.1";
    // 用户信息入口  登录，验证码，绑定
    public static String POST_USER_ENTRY_URL = BASE_URL + "services/user/entry";
    //手机注册新用户
    public static String GET_REGISTER_URL_PER = BASE_URL + "services/user/register?";
    // 验证码登录1.5
    public static String GET_LOGIN_BY_CODE = BASE_URL + "services/user/loginbyauthcode1.5?";
    //修改 找回 密码
    public static String RESET_PASSWORD_URL_PER = BASE_URL + "services/user/resetpwd?";
}
