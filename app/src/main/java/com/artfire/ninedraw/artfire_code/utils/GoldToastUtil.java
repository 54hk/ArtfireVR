package com.artfire.ninedraw.artfire_code.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.artfire.ninedraw.R;


/**
 * Created by Administrator on 2016/4/14.
 */
public class GoldToastUtil {

    public static void showGoldToast(Context context, String str) {
        if(TextUtils.isEmpty(str)) {
            return;
        }
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.gold_toast, null);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.TextViewInfo);
        tv.setText(str);
        toast.show();

    }

    public static void showTopGoldToast(Context context, String str) {
        if(TextUtils.isEmpty(str)) {
            return;
        }
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.gold_toast, null);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.TextViewInfo);
        tv.setText(str);
        toast.show();

    }


}
