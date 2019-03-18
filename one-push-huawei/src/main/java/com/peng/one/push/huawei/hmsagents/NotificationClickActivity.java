package com.peng.one.push.huawei.hmsagents;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.peng.one.push.OneRepeater;
import com.peng.one.push.log.OneLog;
import com.peng.one.push.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by pyt on 2017/5/15.
 */

public class NotificationClickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            String title = uri.getQueryParameter("title");
            String content = uri.getQueryParameter("content");
            String keyValue = uri.getQueryParameter("keyValue");
            try {
                OneRepeater.transmitNotificationClick(getApplicationContext(), -1, title, content, null, JsonUtils.toMap(new JSONObject(keyValue)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        finish();
    }

}
