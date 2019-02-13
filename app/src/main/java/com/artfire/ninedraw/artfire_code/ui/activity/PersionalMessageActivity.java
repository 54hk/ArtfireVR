package com.artfire.ninedraw.artfire_code.ui.activity;

import android.os.Bundle;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;

/**
 * 个人信息
 */
public class PersionalMessageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_persional_message;
    }

    @Override
    public String setTitle() {
        return getString(R.string.string_personal_message);
    }

    @Override
    public void wingetListener() {

    }
}
