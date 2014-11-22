package com.apptitive.beautytips.utilities;

import android.util.Log;

/**
 * Created by Sharif on 7/14/2014.
 */
public class LogUtil {

    private LogUtil() {
    }

    public static final String LOG_TAG = "-----tag: content display-----";

    public static void LOGD(String message) {
        Log.d(LOG_TAG, message);
    }

    public static void LOGD(String message, Throwable cause) {
        Log.d(LOG_TAG, message, cause);

    }

    public static void LOGV(String message) {
        Log.v(LOG_TAG, message);

    }

    public static void LOGV(String messaage, Throwable cause) {
        Log.v(LOG_TAG, messaage, cause);

    }

    public static void LOGI(String message) {
        Log.i(LOG_TAG, message);
    }

    public static void LOGI(String message, Throwable cause) {
        Log.i(LOG_TAG, message, cause);
    }

    public static void LOGW(String message) {
        Log.w(LOG_TAG, message);
    }

    public static void LOGW(String message, Throwable cause) {
        Log.w(LOG_TAG, message, cause);
    }

    public static void LOGE(String message) {
        Log.e(LOG_TAG, message);
    }

    public static void LOGE(String message, Throwable cause) {
        Log.e(LOG_TAG, message, cause);
    }
}
