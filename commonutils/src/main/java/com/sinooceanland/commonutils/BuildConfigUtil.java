package com.sinooceanland.commonutils;

import android.content.Context;

import java.lang.reflect.Field;

public class BuildConfigUtil {
    /**
     * 获取主Module的BuildConfig值
     * @param context
     * @param key 字段名
     * @return
     */
    public static String GetValue(Context context, String key){
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(key);
            return field.get(null).toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
