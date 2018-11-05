package com.jiaoyf.www.saas.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

//通话事件
public class PhoneCallReceiver extends BroadcastReceiver {
    private int lastCallState  = TelephonyManager.CALL_STATE_IDLE;
    private boolean isIncoming = false;
    private static String contactNum;

    @Override
    public void onReceive(Context context, Intent intent){
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            //打电话
            contactNum = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
            Log.d("PhoneCallReceiver1:", "打电话" + contactNum);
        }else{
            //接电话
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            int stateChange = 0;

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                //空闲状态
                stateChange =TelephonyManager.CALL_STATE_IDLE;
                if (isIncoming){
                    Log.d("PhoneCallReceiver2:", "接电话" + contactNum);
                }else {
                    Log.d("PhoneCallReceiver3:", "打电话" + contactNum);
                }
            }else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                //摘机状态
                stateChange = TelephonyManager.CALL_STATE_OFFHOOK;
                if (lastCallState != TelephonyManager.CALL_STATE_RINGING){
                    //如果最近的状态不是来电响铃的话，意味着本次通话是去电
                    isIncoming =false;
                    Log.d("PhoneCallReceiver4:", "打电话" + contactNum);
                }else {
                    //否则本次通话是来电
                    isIncoming = true;
                    Log.d("PhoneCallReceiver5:", "接电话" + contactNum);
                }
            }else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                //来电响铃状态
                stateChange = TelephonyManager.CALL_STATE_RINGING;
                lastCallState = stateChange;
                Log.d("PhoneCallReceiver6:", "接电话" + contactNum);
            }
        }

    }
}
