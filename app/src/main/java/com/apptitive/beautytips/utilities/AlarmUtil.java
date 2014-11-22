package com.apptitive.beautytips.utilities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.apptitive.beautytips.receiver.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by Sharif on 7/20/2014.
 */
public class AlarmUtil {

    public static void setUpAlarm(Context context, long repeatingDay) {
        if (repeatingDay == -1) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        Log.e("current", " system time in millies" + calendar.getTime().toString());
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * repeatingDay, alarmIntent);
     /*   alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY* repeatingDay, alarmIntent);*/
    }
}
