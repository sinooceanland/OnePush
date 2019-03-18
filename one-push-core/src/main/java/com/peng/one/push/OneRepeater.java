package com.peng.one.push;

import android.content.Context;
import android.os.Parcelable;

import com.peng.one.push.entity.OnePushCommand;
import com.peng.one.push.entity.OnePushMsg;
import com.peng.one.push.log.OneLog;
import com.peng.one.push.receiver.OnePushAction;
import com.peng.one.push.receiver.TransmitDataManager;

import java.util.Map;

/**
 * The message the repeater
 * Push platform message to forward a third party
 * Created by pyt on 2017/5/10.
 */

public class OneRepeater {

    private static final String TAG = "OneRepeater";

    /**
     * Repeater instructions operating results
     * @param context
     *
     * @param type
     * @see OnePush#TYPE_ADD_TAG
     * @see OnePush#TYPE_DEL_TAG
     * @see OnePush#TYPE_AND_OR_DEL_TAG
     * @see OnePush#TYPE_REGISTER
     * @see OnePush#TYPE_UNREGISTER
     * @see OnePush#TYPE_BIND_ALIAS
     * @see OnePush#TYPE_UNBIND_ALIAS
     *
     * @param resultCode
     * @see OnePush#RESULT_ERROR
     * @see OnePush#RESULT_OK
     *
     * @param extraMsg 额外信息
     * @param error 错误信息
     */
    public static void transmitCommandResult(Context context, int type, int resultCode,String token
            , String extraMsg, String error){
        transmit(context, OnePushAction.RECEIVE_COMMAND_RESULT
                , new OnePushCommand(type, resultCode, token, extraMsg, error));
    }

    /**
     * Repeater passthrough message
     * @param context
     * @param title
     * @param message
     */
    public static void transmitMessage(Context context, String title
            , String message, Map<String,String> keyValue){
        transmit(context, OnePushAction.RECEIVE_MESSAGE
                , new OnePushMsg(0, title, null, message, null,keyValue));
    }

    /**
     * Repeater the notification bar click event
     * @param context
     * @param notifyId
     * @param title
     * @param content
     * @param extraMsg
     */
    public static void transmitNotificationClick(Context context,int notifyId,String title
            ,String content,String extraMsg,Map<String,String> keyValue){
        transmit(context, OnePushAction.RECEIVE_NOTIFICATION_CLICK
                , new OnePushMsg(notifyId, title, content, null, extraMsg,keyValue));
    }

    /**
     * Repeater notice
     *
     * @param context
     * @param notifyId
     * @param title
     * @param content
     * @param extraMsg
     */
    public static void transmitNotification(Context context, int notifyId, String title
            , String content, String extraMsg, Map<String, String> keyValue) {
        transmit(context, OnePushAction.RECEIVE_NOTIFICATION
                , new OnePushMsg(notifyId, title, content, null, extraMsg, keyValue));
    }

    /**
     * The main method to repeater information
     * @param context
     * @param action
     * @param data
     */
    private static void transmit(Context context, String action, Parcelable data) {
        TransmitDataManager.sendPushData(context, action, data);
    }


}
