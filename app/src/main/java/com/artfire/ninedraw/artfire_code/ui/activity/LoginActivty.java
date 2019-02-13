package com.artfire.ninedraw.artfire_code.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.model.LoginModelImpl;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.utils.CountDownTimerUtils;
import com.artfire.ninedraw.artfire_code.utils.GoldToastUtil;
import com.artfire.ninedraw.artfire_code.utils.StatusBarUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 登录
 */
public class LoginActivty extends BaseActivity implements ApiRequestCallback {


    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.apply_code_on_login_by_phone)
    TextView applyCodeOnLoginByPhone;
    @BindView(R.id.login_on_login_by_phone)
    TextView loginOnLoginByPhone;
    @BindView(R.id.ll_input_parent)
    LinearLayout llInputParent;
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.btn_weixin_login)
    TextView btnWeixinLogin;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private Context mContext;
    LoginModelImpl loginModel;
    CountDownTimerUtils mCountDownTimerUtils;//倒计时工具

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LoginActivty.this;
        isShowTitle(false);
        initStatusBar();
        initView();
    }

    private void initView() {
        loginModel = new LoginModelImpl();
        mCountDownTimerUtils = new CountDownTimerUtils(applyCodeOnLoginByPhone, 60000, 1000);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login_activty;
    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public void wingetListener() {

    }


    @OnClick({ R.id.apply_code_on_login_by_phone, R.id.tv_area, R.id.tv_register,R.id.login_on_login_by_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.tv_register:
                Bundle bundle  = new Bundle();
                bundle.putBoolean("isRegister" , true);
                startActivity(RegisterOrBackActivity.class,bundle);
                break;
            //选择手机号的区号
            case R.id.tv_area:
                startActivityForResult(new Intent(mContext, AreaCodeActivity.class), 105);
                break;
            //获取验证码
            case R.id.apply_code_on_login_by_phone:
                if (!TextUtils.isEmpty(phoneNumber.getText().toString())) {
                    mCountDownTimerUtils.start();
                    JSONObject root = new JSONObject();
                    try {
                        root.put("method", "applyCodex");
                        JSONObject params = new JSONObject();
                        params.put("type", "1");
                        params.put("phone", phoneNumber.getText().toString().trim());
                        params.put("areacode", tvArea.getText().toString().replace(getString(R.string.add_area), ""));
                        root.put("params", params);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    loginModel.loginGetSmsCode(this,this, "GET_CODE", root, true, true, false, null);
                } else {
                    GoldToastUtil.showTopGoldToast(this, getString(R.string.string_phone_not_null));
                }
                break;
                //验证码登陆
            case R.id.login_on_login_by_phone:
                String username = phoneNumber.getText().toString();
                String vcode = etCode.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    if (vcode.length() == 0) {
                        GoldToastUtil.showGoldToast(this, " 验证码不能为空 ");
                        return;
                    }
                    if (vcode.length() != 6) {
                        GoldToastUtil.showGoldToast(this, " 请输入正确的验证码 ");
                        return;
                    }
                    Map params = new HashMap();
                    params.put("phone", username);
                    params.put("code", vcode);
                    loginModel.loginByPhone(this, "LOGIN_BY_PHONE", params, true, true, false, null);
                } else {
                    GoldToastUtil.showGoldToast(this, getString(R.string.string_phone_not_null));
                }
                break;
        }
    }


    //设置状态栏
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /**
             * 说明：   白底黑字
             * 1. SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖。
             * 2. SYSTEM_UI_FLAG_LIGHT_STATUS_BAR：设置状态栏图标为黑色或者白色
             * 3. StatusBarUtil 工具类是修改状态栏的颜色为白色。
             */
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(this, R.color.bg);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 105) {   //区号
            if (resultCode == 100 && data != null) {
                tvArea.setText(data.getStringExtra("area"));
            }
        }
    }

    @Override
    public void analysisData(String requestCode, Object bean, int id) {

    }

    @Override
    public void errorhandle(String requestCode, Call call, Exception e, int id) {

    }
}
