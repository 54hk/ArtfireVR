package com.artfire.ninedraw.vuforia_lib.utils;
/************************************ SharedPreference操作相关封装 * **************/
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * 作者:李少波
 * 邮箱:lishaobo@com.seengene.arguide.seengene.com
 * 日期:16/7/11
 */
public class SharedPreferenceUtil {
    private static String name ="config";

    public static void putString(Context context, String str, String value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(str,value);
        editor.commit();
    }
    public static String getString(Context context, String str, String defaultStr){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
       return  sp.getString(str,defaultStr);
    }
    public static void putBoolean(Context context, String str, boolean bool){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(str,bool);
        editor.commit();
    }
    public static boolean getBoolean(Context context, String str, boolean defaultBool){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
       return  sp.getBoolean(str,defaultBool);
    }
    public static void putInt(Context context, String str, int value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(str,value);
        editor.commit();
    }
    public static int getInt(Context context, String str, int defaultInt){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return  sp.getInt(str,defaultInt);
    }

    public static void putLong(Context context, String str, long value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(str,value);
        editor.commit();
    }
    public static long getLong(Context context, String str, long defaultLong){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return  sp.getLong(str,defaultLong);
    }

    public static void putStringSet(Context context, String str,Set<String> values){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(str,values);
        editor.commit();
    }
    public static Set<String> getStringSet(Context context, String str, Set<String> defaultStrs){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return  sp.getStringSet(str,defaultStrs);
    }

    public static void putFloat(Context context, String str,float value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(str,value);
        editor.commit();
    }
    public static float getFloat(Context context, String str, float defaultLong){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return  sp.getFloat(str,defaultLong);
    }
}
