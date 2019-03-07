package com.sinooceanland.commonutils;

import android.util.Log;

/**
 * <p>文件描述：<p>
 * <p>作者：mike<p>
 * <p>创建时间：2018/8/21<p>
 * <p>更改时间：2018/8/21<p>
 */


public class LogUtil {

    public static String DEFAULT_TAG = "TAG";

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.w(tag, msg);
    }

    public static void i(String msg){
        i(DEFAULT_TAG,msg);
    }

    public static void e(String msg){
        e(DEFAULT_TAG,msg);
    }

    public static void w(String msg){
        w(DEFAULT_TAG,msg);
    }

    public static void v(String msg){
        v(DEFAULT_TAG,msg);
    }

    public static void d(String msg){
        d(DEFAULT_TAG,msg);
    }
}
