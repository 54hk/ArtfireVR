package com.artfire.ninedraw.artfire_code.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.artfire.ninedraw.R;


/**
 * Created by kaiwei on 2015/10/30.
 */
public class MyDialog {
    Context context;
    Dialog dialog;
    TextView tv_con, tv_con2;
    Button btn_ok;
    Button btn_canel;
    View line;

    /**
     * @param context
     * @param con     设置对话框的内容
     */
    public MyDialog(final Context context, String con, String con2) {
        // TODO Auto-generated constructor stub
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ask, null);

        int screenWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        dialog = new Dialog(context, R.style.dialog2);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().getAttributes().width = (int) (screenWidth * 0.7f);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                } else {
                    return false; // 默认返回 false
                }
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //写返回的回调
            }
        });
        //在这可以设置dialog的一些属性；
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_canel = (Button) view.findViewById(R.id.btn_cancel);
        tv_con = (TextView) view.findViewById(R.id.tv_con);
        tv_con2 = (TextView) view.findViewById(R.id.tv_con2);
        line = view.findViewById(R.id.line);
//        tv_title.setText(title);
        if (TextUtils.isEmpty(con2)) {
            tv_con2.setVisibility(View.GONE);
        } else {
            tv_con2.setText(con2);
        }
        tv_con.setText(con);
    }


    public void show() {
        if (dialog != null) {
            dialog.show();
        }

    }

    //确定
    public void setOk(String s, final View.OnClickListener listener) {
        btn_ok.setText(s);
        btn_ok.setOnClickListener(listener);
    }

    //确定
    public void setOkgGone() {
        btn_ok.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    public void setOne() {
        btn_canel.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    //取消
    public void setCanel(String s, final View.OnClickListener listener) {
        btn_canel.setText(s);
        btn_canel.setOnClickListener(listener);
        // dismiss();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShow() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }



}