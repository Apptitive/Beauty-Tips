package com.apptitive.beautytips.utilities;

/**
 * Created by Sharif on 5/28/2014.
 */
public final class Constants {
    public static final String DEFAULT_REGION = "Dhaka";
    public static final String PREF_KEY_SYNC_SETTINGS = "pref_key_sync_settings";
    public static final String PREF_KEY_LOCATION = "prep_key_location";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_HOUR_MINUTE_SECOND = "dd/MM/yyyy HH:mm:ss aa";
    public static final String DATE_FORMAT_12_HOUR = "dd-MM-yyyy hh:mm aa";
    public static final String IS_DB_CREATED = "key_db_creation";
    public static final String PREF_ALARM_DATE = "key_alarm_date";
    public static final String PREF_SWITCH_IFTAR = "pref_switch_iftar";
    public static final String PREF_SWITCH_SEHRI = "pref_switch_sehri";
    public static final int IFTAR_REQUEST_CODE = 1;
    public static final int SEHRI_REQUEST_CODE = 2;

    public static final String IFTAR_ROW_POSITION = "iftar_row_position";
    public static final String SEHRI_ROW_POSITION = "sehri_row_position";

    public static final String AUTHORITY = "com.apptitive.content_display.sync.StubProvider";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "apptitive.com";
    // The account name
    public static final String ACCOUNT_NAME = "dummyaccount";
    public static final String ACTION_RESPONSE = "com.apptitive.content_display.sync.POST_SYNC_RESPONSE";
    public static final String APP_FIRST_TIME_CREATED="app_first_time_created";

    public static final class detail {
        public static final int VIEW_TYPE_P = 0;
        public static final int VIEW_TYPE_UL = 1;
        public static final int VIEW_TYPE_H1 = 2;
        public static final int VIEW_TYPE_B = 3;
        public static final int VIEW_TYPE_I = 4;
        public static final int VIEW_TYPE_COUNT = VIEW_TYPE_I + 1;

        public static int findTagFor(String key) {
            if (key.equalsIgnoreCase(tag.PARAGRAPH))
                return VIEW_TYPE_P;
            else if (key.equalsIgnoreCase(tag.BOLD))
                return VIEW_TYPE_B;
            else if (key.equalsIgnoreCase(tag.UNORDERED_LIST_ITEM))
                return VIEW_TYPE_UL;
            else if (key.equalsIgnoreCase(tag.HEADER_1))
                return VIEW_TYPE_H1;
            else if (key.equalsIgnoreCase(tag.ITALIC))
                return VIEW_TYPE_I;
            return 0;
        }

        public final class tag {
            public static final String PARAGRAPH = "p";
            public static final String BOLD = "b";
            public static final String UNORDERED_LIST_ITEM = "li";
            public static final String HEADER_1 = "h1";
            public static final String ITALIC = "i";
        }
    }

    public final class menu {
        public static final String EXTRA_MENU_ID = "_menuId";
        public static final String EXTRA_MENU_TITLE = "_menuTitle";
    }

    public final class content {
        public static final String EXTRA_CONTENT = "_content";

        public final class view {
            public static final String TYPE_NATIVE = "native";
            public static final String TYPE_HTML = "html";
        }
    }

    public final class db_action {
        public static final String ADD = "add";
        public static final String DELETE = "delete";
        public static final String UPDATE = "update";
    }
}
