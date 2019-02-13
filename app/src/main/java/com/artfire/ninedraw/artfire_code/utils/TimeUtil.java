package com.artfire.ninedraw.artfire_code.utils;

import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.Globals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class TimeUtil {
    /**
     * 设置每个阶段时间
     */
    private static final int seconds_of_1minute = 60;

    private static final int seconds_of_30minutes = 30 * 60;

    private static final int seconds_of_1hour = 60 * 60;

    private static final int seconds_of_1day = 24 * 60 * 60;

    private static final int seconds_of_2day = 24 * 60 * 60 * 2;

    private static final int seconds_of_15days = seconds_of_1day * 15;

    private static final int seconds_of_30days = seconds_of_1day * 30;

    private static final int seconds_of_6months = seconds_of_30days * 6;

    private static final int seconds_of_1year = seconds_of_30days * 12;


    public static Long getEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() + 86400000;
    }

    //获取星期
    public static String getWeek(long timeStamp) {
        int mydate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        mydate = cd.get(Calendar.DAY_OF_WEEK);
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = "周日";
        } else if (mydate == 2) {
            week = "周一";
        } else if (mydate == 3) {
            week = "周二";
        } else if (mydate == 4) {
            week = "周三";
        } else if (mydate == 5) {
            week = "周四";
        } else if (mydate == 6) {
            week = "周五";
        } else if (mydate == 7) {
            week = "周六";
        }
        return week;
    }

    /**
     * 格式化时间
     *
     * @param mTime
     * @return
     */
    public static String getTimeRange(String mTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long now = System.currentTimeMillis() / 1000;
        long publish = Long.parseLong(mTime) / 1000;
        if (now < publish) {  //未发布
            long lt = new Long(mTime);
            Date date = new Date(lt);
            return simpleDateFormat.format(date);
        } else {   //已经发布
            long diff = now - publish;
            if (diff < seconds_of_1minute) {
                return "刚刚";
            } else if (diff < seconds_of_30minutes) {
                return diff / seconds_of_1minute + "分钟前";
            } else if (diff < seconds_of_1hour) {
                return "半小时前";
            } else if (diff < seconds_of_1day) {
                return diff / seconds_of_1hour + "小时前";
            } else if (diff < seconds_of_2day) {
                return "昨天";
            } else if (diff >= seconds_of_2day) {
                long lt = new Long(mTime);
                Date date = new Date(lt);
                return simpleDateFormat.format(date);
            }
        }
        return "";
    }

    /**
     * 格式化时间  具体时间 首用IMNoticeFragment
     *
     * @param
     * @return
     */


    public static String getTimeRange2(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String Week = "";
        String mTime;
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(Long.valueOf(time));
        if (mCalendar.get(Calendar.AM_PM) == 0) { //上午
            mTime = "上午";
        } else {  //下午
            mTime = "下午";
        }
        int wek = mCalendar.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }


        long now = System.currentTimeMillis() / 1000;
        long publish = Long.parseLong(time) / 1000;
        if (now < publish) {  //未发布
            return "刚刚";
        } else {   //已经发布
            long diff = now - publish;
            if (diff < seconds_of_1minute) {
                return "刚刚";
            } else if (diff < seconds_of_30minutes) {
                return diff / seconds_of_1minute + "分钟前";
            } else if (diff < seconds_of_1hour) {
                return "半小时前";
            } else if (diff < seconds_of_1day) {   // 下午1:30”（12小时制）
                return mTime + mCalendar.get(Calendar.HOUR) + ":" + mCalendar.get(Calendar.SECOND);
            } else if (seconds_of_1day <= diff && diff < seconds_of_2day) { //昨天 下午1:30”  （12小时制）
                return "昨天 " + mTime + mCalendar.get(Calendar.HOUR) + ":" + mCalendar.get(Calendar.SECOND);
            } else if (diff >= seconds_of_2day && diff <= seconds_of_1day * 7) {
                return Week + " " + mTime + mCalendar.get(Calendar.HOUR) + ":" + mCalendar.get(Calendar.SECOND);
            } else {
                return format.format(Long.valueOf(time)) + " " + mTime + mCalendar.get(Calendar.HOUR) + ":" + mCalendar.get(Calendar.SECOND); //format.format(time) +
            }
        }
    }


    /**
     * 格式化时间
     *
     * @param mTime
     * @return
     */
    public static String getTimeRange(String mTime, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        long now = System.currentTimeMillis() / 1000;
        long publish = Long.parseLong(mTime) / 1000;
        if (now < publish) {  //未发布
            long lt = new Long(mTime);
            Date date = new Date(lt);
            return simpleDateFormat.format(date);
        } else {   //已经发布
            long diff = now - publish;
            if (diff < seconds_of_1minute) {
                return "刚刚";
            } else if (diff < seconds_of_30minutes) {
                return diff / seconds_of_1minute + "分钟前";
            } else if (diff < seconds_of_1hour) {
                return "半小时前";
            } else if (diff < seconds_of_1day) {
                return diff / seconds_of_1hour + "小时前";
            } else if (diff < seconds_of_2day) {
                return "昨天";
            } else if (diff >= seconds_of_2day) {
                long lt = new Long(mTime);
                Date date = new Date(lt);
                return simpleDateFormat.format(date);
            }
        }
        return "";
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }


    //星座生成 传进是日期格式为: yyyy-mm-dd
    public static void getConstellations(String birthday, TextView textView) {
        String[][] constellations = {{Globals.context.getString(R.string.mojie), Globals.context.getString(R.string.shuiping)},
                {Globals.context.getString(R.string.shuiping), Globals.context.getString(R.string.shuangyu)},
                {Globals.context.getString(R.string.shuangyu), Globals.context.getString(R.string.baiyang)},
                {Globals.context.getString(R.string.baiyang), Globals.context.getString(R.string.jinniu)},
                {Globals.context.getString(R.string.jinniu), Globals.context.getString(R.string.shuangzi)},
                {Globals.context.getString(R.string.shuangzi), Globals.context.getString(R.string.juxie)},
                {Globals.context.getString(R.string.juxie), Globals.context.getString(R.string.shizi)},
                {Globals.context.getString(R.string.shizi), Globals.context.getString(R.string.chunv)},
                {Globals.context.getString(R.string.chunv), Globals.context.getString(R.string.tianping)},
                {Globals.context.getString(R.string.tianping), Globals.context.getString(R.string.tianxie)},
                {Globals.context.getString(R.string.tianxie), Globals.context.getString(R.string.sheshou)},
                {Globals.context.getString(R.string.sheshou), Globals.context.getString(R.string.mojie)}};
        //星座分割时间
        int[] date = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
        String[] data = birthday.split("-");
        int day = date[Integer.parseInt(data[1]) - 1];
        String[] cl1 = constellations[Integer.parseInt(data[1]) - 1];
        if (Integer.parseInt(data[2]) >= day) {
            textView.setText(cl1[1]);
        } else {
            textView.setText(cl1[0]);
        }
    }


    //时间戳转字符串
    public static String getStrTime(String timeStamp, String timeType) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(timeType);
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second < 10) {
            return "00:00:0" + second;
        }
        if (second < 60) {
            return "00:00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "00:0" + minute + ":0" + second;
                }
                return "00:0" + minute + ":" + second;
            }
            if (second < 10) {
                return "00:" + minute + ":0" + second;
            }
            return "00:" + minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 将int型的时间转成##:##格式的时间
     */
    public static String getTimeStr(int timeInt) {
        int mi = 1 * 60;
        int hh = mi * 60;

        long hour = (timeInt) / hh;
        long minute = (timeInt - hour * hh) / mi;
        long second = timeInt - hour * hh - minute * mi;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return strMinute + ":" + strSecond;
        }
    }


    /**
     * 传过来的时间是否在过去的五天之内
     *
     * @param time
     * @return
     */
    public static boolean isYesterday(long time) {
        boolean isYesterday = false;
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(sdf.format(new Date()));
            if (time < date.getTime() && time > (date.getTime() - 5 * 24 * 60 * 60 * 1000)) {
                isYesterday = true;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isYesterday;
    }

    //07:07转为整数秒
    public static long getSecond(String time) {
        long s = 0;
        if (time.indexOf(":") != -1) {
            s = Integer.parseInt(time.substring(0, time.indexOf(":"))) * 60;    //分钟
            s += Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length()));    //秒
        }
        return s;
    }

    /**
     * 获取当前月份第一天的时间戳
     *
     * @return
     */
    public static long getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTimeInMillis());

        return calendar.getTimeInMillis();
    }


    // 将字符串转为时间戳
    public static long getTime(String user_time) {
        String re_time = null;
        long l = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return l;
    }

    //时间戳转换时间
    public static String timeMillisToTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        return d;
    }

    /*
        * 将时间戳转换为时间
        */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳 转化为 分和秒
     */
    public static String trunMS(long ms,String m,String s) {
        String result="";
        try {
            int ss = 1000;
            int mi = ss * 60;
            int hh = mi * 60;
            int dd = hh * 24;

            long day = ms / dd;
            long hour = (ms - day * dd) / hh;
            long minute = (ms - day * dd - hour * hh) / mi;
            long second = (ms - day * dd - hour * hh - minute * mi) / ss;
            long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

            String strDay = day < 10 ? "0" + day : "" + day; //天
            String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
            String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
            String strSecond = second < 10 ? "0" + second : "" + second;//秒
            String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
            strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

            result=(Integer.valueOf(strHour)*60+ Integer.valueOf(strMinute)) + m + strSecond + s;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
