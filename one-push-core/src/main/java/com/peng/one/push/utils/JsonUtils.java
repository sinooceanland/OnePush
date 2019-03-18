package com.peng.one.push.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Json 工具类
 * Created by pyt on 2017/7/14.
 */

public class JsonUtils {

    /**
     * 转换成Map
     * @param jsonObject
     * @return
     */
    public static HashMap<String, String> toMap(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Iterator<String> keys = jsonObject.keys();
        HashMap<String, String> map = new HashMap<>();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object o = jsonObject.get(next);
                map.put(next, String.valueOf(o));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * json转换map
     * @param json
     * @return
     */
    public static Map<String,String> json2Map(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            Map<String, String> map = new HashMap<>();
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = jsonObject.getString(key);
                map.put(key, value);
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
