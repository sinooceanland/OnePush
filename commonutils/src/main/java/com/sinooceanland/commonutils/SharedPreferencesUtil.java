package com.sinooceanland.commonutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>文件描述：<p>
 * <p>作者：mike<p>
 * <p>创建时间：2018/7/20<p>
 * <p>更改时间：2018/7/20<p>
 */
public class SharedPreferencesUtil {

    /**
     * 默认配置文件名称
     */
    private final static String DEFAULT_FILE_NAME = "defaultPreferences";

    private final static int DEFAULT_MODE = Context.MODE_PRIVATE;

    /**
     * 设定boolean值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     * @param mode {@link Context#getSharedPreferences} param mode
     */
    public synchronized static void putBoolean(Context context,String fileName,String key,boolean value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 设定boolean值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     */
    public synchronized static void putBoolean(Context context,String fileName,String key,boolean value){
        putBoolean(context,fileName,key,value,DEFAULT_MODE);
    }

    /**
     * 设定boolean值 读取默认配置文件
     * @param context
     * @param key
     * @param value
     */
    public synchronized static void putBoolean(Context context,String key,boolean value){
        putBoolean(context,DEFAULT_FILE_NAME,key,value,DEFAULT_MODE);
    }

    /**
     * 获取boolean 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @param mode {@link Context#getSharedPreferences} param mode
     * @return boolean
     */
    public static boolean getBoolean(Context context,String fileName,String key,boolean defaultValue,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        return sharedPreferences.getBoolean(key,defaultValue);
    }

    /**
     * 获取boolean 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBoolean(Context context,String fileName,String key,boolean defaultValue){
       return getBoolean(context,fileName,key,defaultValue,DEFAULT_MODE);
    }
    /**
     *  获取boolean 值
     * @param context
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        return getBoolean(context,DEFAULT_FILE_NAME,key,defaultValue,DEFAULT_MODE);
    }

    /**
     * 设定String值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     * @param mode {@link Context#getSharedPreferences} param mode
     */
    public synchronized static void putString(Context context,String fileName,String key,String value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 设定String值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     */
    public synchronized static void putString(Context context,String fileName,String key,String value){
        putString(context,fileName,key,value,DEFAULT_MODE);
    }
    /**
     * 设定String值 读取默认配置文件
     * @param context
     * @param key
     * @param value
     */
    public synchronized static void putString(Context context,String key,String value){
        putString(context,DEFAULT_FILE_NAME,key,value,DEFAULT_MODE);
    }

    /**
     * 获取boolean 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return String
     */
    public static String getString(Context context,String fileName,String key,String defaultValue,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        return sharedPreferences.getString(key,defaultValue);
    }

    /**
     *  获取String 值
     * @param context
     * @param key
     * @param defaultValue
     * @return String
     */
    public static String getString(Context context,String key,String defaultValue){
        return getString(context,DEFAULT_FILE_NAME,key,defaultValue,DEFAULT_MODE);
    }

    /**
     * 获取String 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context,String fileName,String key,String defaultValue){
        return getString(context,fileName,key,defaultValue,DEFAULT_MODE);
    }

    /**
     * 设定 Long 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     * @param mode {@link Context#getSharedPreferences} param mode
     */
    public synchronized static void putLong(Context context,String fileName,String key,long value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    /**
     * 设定 Long 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     */
    public synchronized static void putLong(Context context,String fileName,String key,long value){
        putLong(context,fileName,key,value,DEFAULT_MODE);
    }

    /**
     * 设定 Long 值 读取默认配置文件
     * @param context
     * @param key
     * @param value
     */
    public synchronized static void putLong(Context context,String key,long value){
        putLong(context,DEFAULT_FILE_NAME,key,value,DEFAULT_MODE);
    }

    /**
     * 获取 Long 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @param mode  {@link Context#getSharedPreferences} param mode
     * @return long
     */
    public static long getLong(Context context,String fileName,String key,long defaultValue,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        return sharedPreferences.getLong(key,defaultValue);
    }

    /**
     * 获取 Long 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return long
     */
    public static long getLong(Context context,String fileName,String key,long defaultValue){
        return getLong(context,fileName,key,defaultValue,DEFAULT_MODE);
    }

    /**
     *  获取 Long 值
     * @param context
     * @param key
     * @param defaultValue
     * @return long
     */
    public static long getLong(Context context,String key,long defaultValue){
        return getLong(context,DEFAULT_FILE_NAME,key,defaultValue,DEFAULT_MODE);
    }



    /**
     * 设定 Int 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     * @param mode {@link Context#getSharedPreferences} param mode
     */
    public synchronized static void putInt(Context context,String fileName,String key,int value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 设定 Int 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     */
    public synchronized static void putInt(Context context,String fileName,String key,int value){
        putInt(context,fileName,key,value,DEFAULT_MODE);
    }

    /**
     * 设定 Int 值 读取默认配置文件
     * @param context
     * @param key
     * @param value
     */
    public synchronized static void putInt(Context context,String key,int value){
        putInt(context,DEFAULT_FILE_NAME,key,value,DEFAULT_MODE);
    }

    /**
     * 获取 Int 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @param  mode {@link Context#getSharedPreferences} param mode
     * @return int
     */
    public static int getInt(Context context,String fileName,String key,int defaultValue,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        return sharedPreferences.getInt(key,defaultValue);
    }

    /**
     * 获取 Int 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return int
     */
    public static int getInt(Context context,String fileName,String key,int defaultValue){
        return getInt(context,fileName,key,defaultValue,DEFAULT_MODE);
    }

    /**
     *  获取 Int 值
     * @param context
     * @param key
     * @param defaultValue
     * @return int
     */
    public static int getInt(Context context,String key,int defaultValue){
        return getInt(context,DEFAULT_FILE_NAME,key,defaultValue,DEFAULT_MODE);
    }

    /**
     * 设定 Float 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     * @param mode  {@link Context#getSharedPreferences} param mode
     */
    public synchronized static void putFloat(Context context,String fileName,String key,float value,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putFloat(key,value);
        editor.commit();
    }

    /**
     * 设定 Float 值
     * @param context
     * @param fileName  {@link Context#getSharedPreferences} param name
     * @param key
     * @param value
     */
    public synchronized static void putFloat(Context context,String fileName,String key,float value){
        putFloat(context,fileName,key,value,DEFAULT_MODE);
    }

    /**
     * 设定 Float 值 读取默认配置文件
     * @param context
     * @param key
     * @param value
     */
    public synchronized static void putFloat(Context context,String key,float value){
        putFloat(context,DEFAULT_FILE_NAME,key,value,DEFAULT_MODE);
    }

    /**
     * 获取 Float 值
     * @param context
     * @param fileName {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @param mode {@link Context#getSharedPreferences} param mode
     * @return float
     */
    public static float getFloat(Context context,String fileName,String key,float defaultValue,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
        return sharedPreferences.getFloat(key,defaultValue);
    }

    /**
     * 获取 Float 值
     * @param context
     * @param fileName  {@link Context#getSharedPreferences} param name
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(Context context,String fileName,String key,float defaultValue){
        return getFloat(context,fileName,key,defaultValue,DEFAULT_MODE);
    }

    /**
     *  获取 Float 值
     * @param context
     * @param key
     * @param defaultValue
     * @return float
     */
    public static float getFloat(Context context,String key,float defaultValue){
        return getFloat(context,DEFAULT_FILE_NAME,key,defaultValue,DEFAULT_MODE);
    }


    /**
     * 清除所有配置
     * @param context
     * @param fileName
     * @param mode
     */
    public static void clear(Context context,String fileName,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,mode);
        sharedPreferences.edit().clear().commit();
    }

    /**
     * 清除fileName的所有配置
     * @param context
     * @param fileName
     */
    public static void clear(Context context,String fileName){
        clear(context,fileName,DEFAULT_MODE);
    }

    /**
     * 清除默认的配置文件所有配置
     * @param context
     */
    public static void clear(Context context){
        clear(context,DEFAULT_FILE_NAME,DEFAULT_MODE);
    }

}
