package com.artfire.ninedraw.artfire_code.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.artfire.ninedraw.artfire_code.base.Globals;
import com.artfire.ninedraw.artfire_code.model.AliPayerModel;
import com.artfire.ninedraw.artfire_code.model.AliPayerModelImpl;
import com.tencent.imsdk.TIMManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class SharedPreferencesUtil {

    public static void setLoginState(Context context) {
        if (!TextUtils.isEmpty(Globals.USER_UTOKEN)) {
            if (!TIMManager.getInstance().getLoginUser().equals(Globals.USER_UMIID)) {
                AliPayerModel homeModel = new AliPayerModelImpl();
                Map<String, String> sigParams = new HashMap<>();
                sigParams.put("umiid", Globals.USER_UMIID);
                sigParams.put("utoken", Globals.USER_UTOKEN);
                homeModel.getTimSig((Activity)context, "GET_IM_SIG", sigParams, false, false, false, null);
            }
        }
        SharedPreferences sharedPreferences = Globals.context.getSharedPreferences("artfire", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("utoken", Globals.USER_UTOKEN);
        editor.putString("umiid", Globals.USER_UMIID);
        editor.putString("umid", Globals.USER_UMID);
        editor.putString("umalias", Globals.USER_UMALIAS);
        editor.putString("umsmallpic", Globals.USER_UMSMALLPIC);
        editor.putString("timusersig", Globals.USER_TIMUSERSIG);
        editor.putString("isteacher", Globals.USER_ISTEACHER);
        editor.putString("hasauthority", Globals.USER_HASAUTHORITY);
        editor.putString("user_wx_icon", Globals.USER_WX_ICON);
        editor.putString("user_umfavornum", Globals.USER_UMFAVORNUM);
        editor.putString("user_umlargepic", Globals.USER_UMLARGEPIC);
        editor.putString("is_open_wallet", Globals.IS_OPEN_WALLET);
        editor.putString("user_sex", Globals.USER_SEX);
        editor.putString("user_job", Globals.USER_JOB);
        editor.putString("paynumcr1", Globals.PAYNUMCR1);
        editor.putString("paynumcrs", Globals.PAYNUMCRS);
        editor.putString("paynumsc", Globals.PAYNUMSC);

        editor.putString("o1", Globals.USER_O1);
        editor.commit();

    }


    public static void removeLoginState(Context context) {
        SharedPreferences sharedPreferences =  Globals.context.getSharedPreferences("artfire", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Globals.USER_UTOKEN = "";
        Globals.USER_UMIID = "";
        Globals.USER_UMID = "";
        Globals.USER_UMALIAS = "";
        Globals.USER_UMSMALLPIC = "";
        Globals.USER_TIMUSERSIG = "";
        Globals.USER_ISTEACHER = "";
        Globals.USER_HASAUTHORITY = "0";
        Globals.USER_WX_ICON = "";
        Globals.USER_UMFAVORNUM = "";
        Globals.USER_UMLARGEPIC = "";
        Globals.IS_OPEN_WALLET = "";
        Globals.USER_SEX = "";
        Globals.USER_JOB = "";
        Globals.PAYNUMCR1 = "";
        Globals.PAYNUMCRS = "";
        Globals.PAYNUMSC = "";
        Globals.USER_O1 = "";

    }

    public static void getLoginState(Context context) {
        SharedPreferences sharedPreferences =  Globals.context.getSharedPreferences("artfire", Context.MODE_MULTI_PROCESS);
        if (sharedPreferences != null
                && sharedPreferences.getString("utoken", null) != null
                && !sharedPreferences.getString("utoken", null).equals("")) {
            Log.i("SharedPreferencesU", "获取utoken" + sharedPreferences.getString("utoken", null));
            Globals.USER_UTOKEN = sharedPreferences.getString("utoken", null);
            Globals.USER_UMID = sharedPreferences.getString("umid", null);
            Globals.USER_UMIID =  sharedPreferences.getString("umiid", null);
            Globals.USER_UMALIAS  = sharedPreferences.getString("umalias", null);
            Globals.USER_UMSMALLPIC = sharedPreferences.getString("umsmallpic", null);
            Globals.USER_WX_ICON = sharedPreferences.getString("user_wx_icon", null);
            Globals.USER_HASAUTHORITY = sharedPreferences.getString("hasauthority", "0");
            Globals.USER_TIMUSERSIG = sharedPreferences.getString("timusersig", null);
            Globals.USER_ISTEACHER =  sharedPreferences.getString("isteacher", null);
            Globals.USER_UMFAVORNUM = sharedPreferences.getString("user_umfavornum", null);
            Globals.USER_UMLARGEPIC = sharedPreferences.getString("user_umlargepic", null);
            Globals.IS_OPEN_WALLET = sharedPreferences.getString("is_open_wallet", null);
            Globals.USER_SEX =sharedPreferences.getString("user_sex", null);
            Globals.USER_JOB = sharedPreferences.getString("user_job", null);

            Globals.PAYNUMCR1 = sharedPreferences.getString("paynumcr1", null);
            Globals.PAYNUMCRS = sharedPreferences.getString("paynumcrs", null);
            Globals.PAYNUMSC = sharedPreferences.getString("paynumsc", null);

            Globals.USER_O1 = sharedPreferences.getString("o1", null);



        /*    GrowingIO growingIO = GrowingIO.getInstance();
            growingIO.setUserId(Globals.USER_UMID);
            growingIO.setPeopleVariable("UserName", Globals.USER_UMALIAS);
            if(!TextUtils.isEmpty(Globals.USER_SEX)) {
                if(Globals.USER_SEX.equals("0")) {
                    growingIO.setPeopleVariable("Sex", "女");
                }else if(Globals.USER_SEX.equals("1")) {
                    growingIO.setPeopleVariable("Sex", "男");
                }else {
                    growingIO.setPeopleVariable("Sex", "保密");
                }
            }
            if(!TextUtils.isEmpty(Globals.USER_JOB)) {
                growingIO.setPeopleVariable("Career", Globals.USER_JOB);
            }else {
                growingIO.setPeopleVariable("Career", "暂无 ");
            }
            if(!TextUtils.isEmpty(Globals.PAYNUMCR1)) {
                growingIO.setPeopleVariable("buycount1", Globals.PAYNUMCR1);
            }else {
                growingIO.setPeopleVariable("buycount1", "0");
            }
            if(!TextUtils.isEmpty(Globals.PAYNUMCRS)) {
                growingIO.setPeopleVariable("buycount2", Globals.PAYNUMCRS);
            }else {
                growingIO.setPeopleVariable("buycount2", "0");
            }
            if(!TextUtils.isEmpty(Globals.PAYNUMSC)) {
                growingIO.setPeopleVariable("buycount3", Globals.PAYNUMSC);
            }else {
                growingIO.setPeopleVariable("buycount3", "0");
            }
*/
        } else {
            Log.i("SharedPreferencesU", "未获取utoken");
        }
    }

    public static void removeSharedPreferences(Context context) {
        SharedPreferences sharedPreferences =  Globals.context.getSharedPreferences("artfire", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param context
     * @param key
     * @param object
     */
    public static void setParam(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp =  Globals.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.commit();
    }


    /**
     * 删除key
     */
    public static void removeKey(Context context,String key){
        SharedPreferences sp =  Globals.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context , String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp =  Globals.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }
        return null;
    }

    /**
     * 存储Map集合
     * @param context 上下文
     * @param key 键
     * @param map 存储的集合
     * @param <K> 指定Map的键
     * @param <V> 指定Map的值
     */
    public static <K extends Serializable, V extends Serializable> void putMap(Context context, String key, Map<K, V> map){
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
        try {
            put( Globals.context, key, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <K extends Serializable, V extends Serializable> Map<K, V> getMap(Context context,
                                                                                    String key)
    {
        try {
            return (Map<K, V>) get( Globals.context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**存储对象*/
    public static void put(Context context, String key, Object obj)throws IOException {
        if (obj == null) {//判断对象是否为空
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream    oos  = null;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        setParam( Globals.context, key, objectStr);
    }

    /**获取对象*/
    public static Object get(Context context, String key)throws IOException, ClassNotFoundException{
        String wordBase64 = getParam(context, key, "").toString();
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException
            return null;
        }
        byte[]  objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        // 将byte数组转换成product对象
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }

}
