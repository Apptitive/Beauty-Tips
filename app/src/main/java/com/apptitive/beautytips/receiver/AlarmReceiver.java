package com.apptitive.beautytips.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apptitive.beautytips.services.SyncService;
import com.apptitive.beautytips.utilities.LogUtil;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.LOGE("inside alarm receiver");
        Intent syncIntent = new Intent(context, SyncService.class);
        context.startService(syncIntent);
    }
}
