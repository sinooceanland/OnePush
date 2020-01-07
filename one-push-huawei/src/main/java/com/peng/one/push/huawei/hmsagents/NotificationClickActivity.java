package com.peng.one.push.huawei.hmsagents;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.peng.one.push.OneRepeater;
import com.peng.one.push.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by pyt on 2017/5/15.
 */

public class NotificationClickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            Log.i(NotificationClickActivity.class.getSimpleName(), "URL：" + uri.toString());
            String title = uri.getQueryParameter("title");
            String content = uri.getQueryParameter("content");
//            String keyValue = uri.getQueryParameter("keyValue"); //因为华为通道两次转码的问题，使用新的keyValue1
            String keyValue1 = uri.getQueryParameter("keyValue1");
            try {
                Log.i(NotificationClickActivity.class.getSimpleName(), "keyValue：" + URLDecoder.decode(keyValue1, "utf-8"));
                OneRepeater.transmitNotificationClick(getApplicationContext(), -1, title, content, null, JsonUtils.toMap(new JSONObject(URLDecoder.decode(keyValue1, "utf-8"))));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        finish();
    }

}
