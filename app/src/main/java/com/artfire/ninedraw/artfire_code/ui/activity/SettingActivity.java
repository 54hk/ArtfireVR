package com.artfire.ninedraw.artfire_code.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public String setTitle() {
        return getString(R.string.string_setting);
    }

    @Override
    public void wingetListener() {

    }
}
