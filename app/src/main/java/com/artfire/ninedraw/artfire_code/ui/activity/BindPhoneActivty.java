package com.artfire.ninedraw.artfire_code.ui.activity;


import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;

/**
 * 绑定手机号
 */
public class BindPhoneActivty extends BaseActivity {


    @Override
    public int setLayoutId() {
        return R.layout.activity_bind_phone_activty;
    }

    @Override
    public String setTitle() {
        return getString(R.string.string_bind_phone);
    }

    @Override
    public void wingetListener() {

    }
}
