package com.peng.one.push.huawei.hmsagents;


import android.content.Context;
import android.os.Bundle;

import com.huawei.hms.support.api.push.PushReceiver;
import com.peng.one.push.OnePush;
import com.peng.one.push.OneRepeater;
import com.peng.one.push.cache.OnePushCache;
import com.peng.one.push.log.OneLog;
import com.peng.one.push.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Map;

import static com.peng.one.push.utils.JsonUtils.json2Map;

/**
 * Created by pyt on 2017/5/15.
 */

public class HuaweiPushReceiver extends PushReceiver {

    private static final String TAG = "HuaweiPushReceiver";

    @Override
    public void onToken(Context context, String token, Bundle bundle) {
        super.onToken(context, token);
        OneLog.i("huawei-hmsagents onToken() called with: context = [" + context + "], token = [" + token + "], bundle = [" + bundle + "]");
        //save token when you call unregister method
        OnePushCache.putToken(context, token);
        OneRepeater.transmitCommandResult(context, OnePush.TYPE_REGISTER, OnePush.RESULT_OK, token, null, null);
    }

    @Override
    public void onPushState(Context context, boolean b) {
        super.onPushState(context, b);
    }

    @Override
    public void onToken(Context context, String s) {
        super.onToken(context, s);
    }

    @Override
    public void onPushMsg(Context context, byte[] bytes, String s) {
        super.onPushMsg(context, bytes, s);
        OneLog.i("huawei-hmsagents onPushMsg() called with: context = [" + context + "], bytes = [" + bytes + "], s = [" + s + "]");
        String messageBody=new String(bytes, Charset.forName("UTF-8"));
        try {
            JSONObject jsonObject=new JSONObject(messageBody);
            String title;
            String message;
            Map<String,String> keyValue;
            if(jsonObject.has("title")&&jsonObject.has("content")){
                title= jsonObject.getString("title");
                message= jsonObject.getString("title");
                keyValue=JsonUtils.toMap(jsonObject.getJSONObject("extras"));
                OneRepeater.transmitMessage(context,title,message,keyValue);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneRepeater.transmitMessage(context,null,messageBody , null);
    }

    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
        super.onEvent(context, event, bundle);
        //官方建议使用自定义动作，因为此方法不稳定
        if (event == Event.NOTIFICATION_CLICK_BTN) {//通知栏中的按钮被点击

        } else if (event == Event.NOTIFICATION_OPENED) {//通知栏被打开（后台的发送通知必须包含键值对，否者该方法不会被调用）
            //将华为比较特别的keyValue的json方式进行转换(有点鸡肋)
            //注意：如果app被用户给清理掉，这个方法是不会被调用的，所以建议后台发送通知，以打开指令页面的方式，这样就可以有效的控制Click事件的处理

            //EMUI4.0 and EMUI5.0 is not use
//            OneLog.e("onEvent() called with: context = [" + context + "], event = [" + event + "], bundle = [" + bundle + "]");
//            if (bundle != null) {
//                try {
////                OneRepeater.transmitMessage(context, msg, null, null);
//                    OneRepeater.transmitNotificationClick(context, 0, null, null, bundle.getString("pushMsg"), null);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
