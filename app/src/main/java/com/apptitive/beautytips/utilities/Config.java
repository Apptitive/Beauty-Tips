package com.apptitive.beautytips.utilities;

import android.content.Context;

/**
 * Created by Sharif on 7/14/2014.
 */
public class Config {
    private static final String MENU_URL = "http://apptitive.com/content_display/json/menu.json";
    private static final String TOPIC_URL = "http://apptitive.com/content_display/json/content.json";
    private static final String BASE_IMAGE_URL="http://apptitive.com/beauty_tips/media/images/";

    public static String getMenuUrl() {
        return MENU_URL;
    }
    public static String getTopicUrl(){
        return TOPIC_URL;
    }

    public static String getImageUrl(Context context) {
        return BASE_IMAGE_URL + DpUtil.getDeviceDensity(context)+"/";
    }
}
