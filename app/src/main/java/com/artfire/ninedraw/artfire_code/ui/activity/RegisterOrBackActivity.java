package com.artfire.ninedraw.artfire_code.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.model.LoginModelImpl;
import com.artfire.ninedraw.artfire_code.network.APIProtocol;
import com.artfire.ninedraw.artfire_code.network.ApiRequestCallback;
import com.artfire.ninedraw.artfire_code.utils.CountDownTimerUtils;
import com.artfire.ninedraw.artfire_code.utils.GoldToastUtil;
import com.artfire.ninedraw.artfire_code.utils.StatusBarUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 找回密码 和 注册账号
 */
public class RegisterOrBackActivity extends BaseActivity implements ApiRequestCallback {


    @BindView(R.id.tv_what)
    TextView tvWhat;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.apply_code_on_login_by_phone)
    TextView applyCodeOnLoginByPhone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.login_on_login_by_phone)
    TextView loginOnLoginByPhone;
    @BindView(R.id.ll_input_parent)
    LinearLayout llInputParent;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;
    @BindView(R.id.et_verification)
    EditText etVerification;
    @BindView(R.id.et_password)
    EditText etPassword;
    private Context mContext;
    private String isWhat;
    private boolean isRegister;

    LoginModelImpl loginModel;
    CountDownTimerUtils mCountDownTimerUtils;//倒计时工具
    private String password;
    private String vcode;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = RegisterOrBackActivity.this;
        isShowTitle(false);
        initStatusBar();
        initView();
    }

    private void initView() {
        loginModel = new LoginModelImpl();
        mCountDownTimerUtils = new CountDownTimerUtils(applyCodeOnLoginByPhone, 60000, 1000);
        isRegister = getIntent().getExtras().getBoolean("isRegister");
        if (isRegister) { //这是注册
            tvWhat.setText(getString(R.string.string_register_account));
        } else { //找回密码
            tvWhat.setText(getString(R.string.string_find_back_password));
        }

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_register_or_back;
    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public void wingetListener() {

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

    @OnClick({R.id.tv_area, R.id.apply_code_on_login_by_phone, R.id.img_back, R.id.login_on_login_by_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back: //返回
                finish();
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

                    loginModel.loginGetSmsCode(this, this, "GET_CODE", root, true, true, false, null);
                } else {
                    GoldToastUtil.showTopGoldToast(this, getString(R.string.string_phone_not_null));
                }
                break;
            //选择手机号的区号
            case R.id.tv_area:
                startActivityForResult(new Intent(mContext, AreaCodeActivity.class), 105);
                break;
            case R.id.login_on_login_by_phone: //提交
                phone = phoneNumber.getText().toString().trim();
                vcode = etVerification.getText().toString().trim();
                password = etAgainPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
                    GoldToastUtil.showTopGoldToast(this, "手机号码不能为空");
                    return;
                }
                if (phoneNumber.getText().toString().length() != 11 || !phoneNumber.getText().toString().substring(0, 1).equals("1")) {
                    GoldToastUtil.showTopGoldToast(this, "请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    GoldToastUtil.showTopGoldToast(this, "新密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etAgainPassword.getText().toString())) {
                    GoldToastUtil.showTopGoldToast(this, "确认密码不能空");
                    return;
                }
                if (TextUtils.isEmpty(etVerification.getText().toString())) {
                    GoldToastUtil.showTopGoldToast(this, "验证码不能为空");
                    return;
                }
                if (!etAgainPassword.getText().toString().equals(etPassword.getText().toString())) {
                    GoldToastUtil.showTopGoldToast(this, "请确保两次密码输入一致");
                    return;
                }
                if (etPassword.getText().toString().length() < 6) {
                    GoldToastUtil.showTopGoldToast(this, "请输入大于或等于6位密码");
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("phone", phone);
                map.put("vcode", vcode);
                map.put("passwd", etAgainPassword.getText().toString().trim());
                map.put("areacode", tvArea.getText().toString().replace(getString(R.string.add_area), ""));
//                map.put("devtoken","0");

                if (isRegister) { //注册
                    loginModel.registerAccount(this, this, "GET_REGISTER_URL_PER", APIProtocol.GET_REGISTER_URL_PER, map, true, true, false, null);
                } else { //修改
                    loginModel.changePassWord(this, APIProtocol.RESET_PASSWORD_URL_PER, this, "RESET_PASSWORD_URL_PER", map, true, true, false, null);
                }

                break;
        }
    }

    @Override
    public void analysisData(String requestCode, Object bean, int id) {
        if (requestCode.equals("GET_CODE")) {
            GoldToastUtil.showGoldToast(this, "  验证码已发送，请注意查收  ");
        } else if (requestCode.equals("GET_REGISTER_URL_PER")) {
            JSONObject root = new JSONObject();
            try {
                root.put("method", "loginx");
                JSONObject params = new JSONObject();
                params.put("uname", phone);
                params.put("passwd", password);
                params.put("type", "0");
                params.put("client", Globals.CLIENT);
                params.put("version", Globals.VERSION);
                root.put("params", params);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            loginModel.loginGetSmsCode(this, this, "POST_USER_ENTRY_URL", root, true, true, false, null);
        } else if (requestCode.equals("POST_USER_ENTRY_URL")) {
//            startActivity(new Intent(this, HomeActivity2.class));
            GoldToastUtil.showGoldToast(Globals.context, "  欢迎来到艺伙  ");
            finish();

        } else if (requestCode.equals("RESET_PASSWORD_URL_PER")) {
            GoldToastUtil.showTopGoldToast(this, "修改成功");
            finish();
        }
    }

    @Override
    public void errorhandle(String requestCode, Call call, Exception e, int id) {

    }
}
