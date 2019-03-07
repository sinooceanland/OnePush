package com.sinooceanland.commonutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：mike<p>
 * <p>创建时间：2018/8/16<p>
 * <p>更改时间：2018/8/16<p>
 */
public class MetaValueUtil {

    private static Map<String ,String> cacheMap = new HashMap<>();
    /**
     * 获取配置的参数
     *
     * @param context
     * @param key
     * @return
     */
    public static String GetMetaValue(Context context, String key) {
        if(cacheMap.containsKey(key)){
            return cacheMap.get(key);
        }else{
            ApplicationInfo appInfo = null;
            String metaValue = "";
            try {
                appInfo = context.getPackageManager()
                        .getApplicationInfo(context.getPackageName(),
                                PackageManager.GET_META_DATA);
                assert appInfo!=null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ( !appInfo.metaData.containsKey(key)) {
                throw new Error("meta-data " + key + " not found in AndroidManifest.xml");
            }
            metaValue = appInfo.metaData.getString(key);
            cacheMap.put(key,metaValue);
            return metaValue;
        }

    }
}
