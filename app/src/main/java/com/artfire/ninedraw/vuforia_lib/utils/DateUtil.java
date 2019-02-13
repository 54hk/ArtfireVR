package com.artfire.ninedraw.vuforia_lib.utils;
/************************************ 时间日期相关封装 * **************/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者:李少波
 * 邮箱:lishaobo@seengene.com
 * 日期:16/7/14
 */

public class DateUtil {
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;


    /**
     * Date的GetHour过时，用这个Util相应方法替代
     */
    //得到小时
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    //得到分钟
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    //得到秒钟
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    //设置小时
    public static Date setHour(int hour, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    //设置分钟
    public static Date setMinute(int minute, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
    public static String intOne2TwoStr(int time) {
        String tempNum = time + "";
        int length = 2;
        while (tempNum.length() < length) {
            tempNum = "0" + tempNum;
        }
        return  tempNum;
    }
    public static String formatDateByStr(Date date, String str){
        //eg:"yyyy-MM-dd HH:mm:ss" 24小时制
        //eg:"yyyy-MM-dd hh:mm:ss" 12小时制
        //eg:"hh:mm a" 12小时制 am pm格式
        SimpleDateFormat format = new SimpleDateFormat(str);
        return format.format(date);
    }
    //得到周几
    public static String getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String today = null;
        if (day == 2) {
            today = "周一";
        } else if (day == 3) {
            today = "周二";
        } else if (day == 4) {
            today = "周三";
        } else if (day == 5) {
            today = "周四";
        } else if (day == 6) {
            today = "周五";
        } else if (day == 7) {
            today = "周六";
        } else if (day == 1) {
            today = "周日";
        }
        return today;
    }


    // a integer to xx:xx:xx
    public static String secToTime(int secondcount) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (secondcount <= 0)
            return "00:00:00";
        else {
            minute = secondcount / 60;
            if (minute < 60) {
                second = secondcount % 60;
                timeStr = "00:"+unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = secondcount - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    /**
     * 距离今天多久
     * @param date
     * @return
     *
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_MINUTE)
            return "现在";
        /*else if (ago <= ONE_MINUTE)
            return ago  + "秒前";*/
        else if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_DAY * 3)
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
                    + "日";
        }

    }
    /**
     * 距离截止日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR)
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        else if (remain <= ONE_DAY)
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }

    }

}
