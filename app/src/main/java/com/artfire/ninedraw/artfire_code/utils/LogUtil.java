package com.artfire.ninedraw.artfire_code.utils;

import android.util.Log;

import com.artfire.ninedraw.artfire_code.base.Globals;


public class LogUtil {

	public static void e(String tag, String msg ){
		if (Globals.HAVE_LOG) {
			Log.e("MYYIHUO"+tag, "------"+msg);
		}
	}



                  
}
