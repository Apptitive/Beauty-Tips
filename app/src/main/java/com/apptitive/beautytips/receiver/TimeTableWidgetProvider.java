package com.apptitive.beautytips.receiver;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.apptitive.beautytips.helper.DbManager;
import com.apptitive.beautytips.model.Region;
import com.apptitive.beautytips.model.TimeTable;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.DateTimeUtils;
import com.apptitive.beautytips.utilities.PreferenceHelper;
import com.apptitive.beautytips.utilities.Utilities;
import com.apptitive.beautytips.R;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Iftekhar on 7/2/2014.
 */
public class TimeTableWidgetProvider extends AppWidgetProvider {

    private PreferenceHelper preferenceHelper;
    private List<TimeTable> timeTables;
    private List<Region> regions;
    private Region region;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,
                TimeTableWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.layout_appwidget);
            remoteViews.setImageViewBitmap(R.id.imageView_name,
                    Utilities.getTypefaceBitmap(context, context.getString(R.string.widget_left_label), 20, false));
            remoteViews.setImageViewBitmap(R.id.imageView_time_seheri, Utilities.getTypefaceBitmap(context, context.getString(R.string.sehri_time), 12, false));
            remoteViews.setImageViewBitmap(R.id.imageView_time_iftar, Utilities.getTypefaceBitmap(context, context.getString(R.string.iftar), 12, false));

            DbManager.init(context);
            preferenceHelper = new PreferenceHelper(context);
            timeTables = DbManager.getInstance().getAllTimeTables();
            regions = DbManager.getInstance().getAllRegions();
            region = DateTimeUtils.getSelectedLocation(regions, preferenceHelper.getString(Constants.PREF_KEY_LOCATION, "Dhaka"));

            region = DateTimeUtils.getSelectedLocation(regions, preferenceHelper.getString(Constants.PREF_KEY_LOCATION, "Dhaka"));
            if (region != null) {
                try {
                    if (region.isPositive()) {
                        remoteViews.setImageViewBitmap(R.id.imageView_seheri_time, Utilities.getTypefaceBitmap(context, DateTimeUtils.getSehriIftarTime(region.getIntervalSehri(), timeTables, true, true), 35, true));
                        remoteViews.setImageViewBitmap(R.id.imageView_iftar_time, Utilities.getTypefaceBitmap(context, DateTimeUtils.getSehriIftarTime(region.getIntervalIfter(), timeTables, true, false), 35, true));
                    } else {
                        remoteViews.setImageViewBitmap(R.id.imageView_seheri_time, Utilities.getTypefaceBitmap(context, DateTimeUtils.getSehriIftarTime(-region.getIntervalSehri(), timeTables, true, true), 35, true));
                        remoteViews.setImageViewBitmap(R.id.imageView_iftar_time, Utilities.getTypefaceBitmap(context, DateTimeUtils.getSehriIftarTime(-region.getIntervalIfter(), timeTables, true, false), 35, true));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

/*            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_sehri_iftar_time, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);*/
        }
    }
}
