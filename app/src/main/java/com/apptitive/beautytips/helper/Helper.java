package com.apptitive.beautytips.helper;

import android.view.Display;
import android.view.WindowManager;

/**
 * Created by rayhan on 6/2/2014.
 */
public class Helper {
    public static int getArrayIndex(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item))
                return i;
        }
        return 0;
    }

    public static boolean isPortraitMode(WindowManager windowManager) {
        Display display = windowManager.getDefaultDisplay();
        if (display.getWidth() <= display.getHeight())
            return true;
        return false;
    }
}
