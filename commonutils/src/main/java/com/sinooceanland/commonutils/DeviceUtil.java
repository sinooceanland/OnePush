package com.sinooceanland.commonutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * <p>文件描述：<p>
 * <p>作者：mike<p>
 * <p>创建时间：2018/7/19<p>
 * <p>更改时间：2018/7/19<p>
 */
public class DeviceUtil {
    public static synchronized String getDeviceID(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String identity = preference.getString("identity", null);
        if (identity == null) {
            identity = java.util.UUID.randomUUID().toString();
            preference.edit().putString("identity", identity).commit();
        }
        return identity;
    }
}