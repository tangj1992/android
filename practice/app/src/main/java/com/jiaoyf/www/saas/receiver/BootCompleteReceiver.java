package com.jiaoyf.www.saas.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
//开机
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BootCompleteReceiver:","Boot Complete");
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show();
    }
}
