package com.artfire.ninedraw.artfire_code.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.artfire.ninedraw.R;


public class AlertUtil {
    private static AlertUtil alertUtil;
    private AlertDialog dialog;

    public AlertUtil() {}

    public synchronized static AlertUtil getInstance() {
        if(alertUtil == null) {
            alertUtil = new AlertUtil();
        }
        return alertUtil;
    }


    public void alert(final Context context) {
        closeDialog(context);
        dialog = new AlertDialog.Builder(context).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getRepeatCount() == 0) {
                    if(context != null) {
                        ((Activity)context).finish();
                    }
                }
                return false;
            }
        });
        try {
            if (context != null && dialog != null) {
                dialog.show();
                dialog.getWindow().setContentView(R.layout.loading);
            }
        } catch (Exception e) {
        }
    }


    public void closeDialog(Context context) {
        try {
            if (dialog != null && context != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


}
