package com.apptitive.beautytips.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apptitive.beautytips.services.RingtoneService;

public class NotificationCancelReceiver extends BroadcastReceiver {
    public NotificationCancelReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.stopService(new Intent(context, RingtoneService.class));
    }
}
