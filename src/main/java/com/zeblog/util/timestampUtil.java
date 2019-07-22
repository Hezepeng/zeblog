package com.zeblog.util;


/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-22 12:10
 */
public class timestampUtil {
    public static String getSecondTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String getMillisTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getSomeHourLaterTimestamp(int hour) {
        return String.valueOf(System.currentTimeMillis() + 1000 * 60 * 60 * hour);
    }
}
