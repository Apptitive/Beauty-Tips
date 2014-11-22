package com.apptitive.beautytips;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;

import com.apptitive.beautytips.helper.DbManager;
import com.apptitive.beautytips.utilities.AboutUsDialog;
import com.apptitive.beautytips.utilities.AlarmUtil;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.PreferenceHelper;
import com.apptitive.beautytips.utilities.Utilities;

import static android.preference.Preference.OnPreferenceChangeListener;


public class SettingsActivity extends PreferenceActivity {
    private PreferenceCategory categoryLocation;
    private PreferenceCategory categoryAboutUs;
    private ListPreference preferenceLocation;
    private Preference preferenceAboutUs;
    private static String[] entries;
    private static boolean isPreferenceSelected;
    private static PreferenceHelper preferenceHelper;
    private static Context settingsActivity;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbManager.init(this);
        if (Build.VERSION.SDK_INT >= 11) {
            getActionBar().hide();
        }
        addPreferencesFromResource(com.apptitive.beautytips.R.xml.pref_general);
        setContentView(R.layout.activity_settings);
        entries = new String[]{"None", "Daily", "Weekly"};
        isPreferenceSelected = false;
        settingsActivity = this;
        preferenceHelper = new PreferenceHelper(this);
        setupSimplePreferencesScreen();

        LinearLayout settings = (LinearLayout) findViewById(com.apptitive.beautytips.R.id.settings_back);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void setupSimplePreferencesScreen() {
        ListPreference listPreference = (ListPreference) findPreference(getString(com.apptitive.beautytips.R.string.pref_key_location));
        if (listPreference != null) {
            listPreference.setEntries(entries);
            listPreference.setEntryValues(entries);
            listPreference.setDialogTitle(Utilities.getBanglaSpannableString(getString(com.apptitive.beautytips.R.string.sync_options), this));
        }
        bindPreferenceSummaryToValue(findPreference(getString(com.apptitive.beautytips.R.string.pref_key_location)));
        findViews();
        setBanglaTextToView();
    }

    private void findViews() {
        categoryLocation = (PreferenceCategory) findPreference(getString(com.apptitive.beautytips.R.string.pref_key_location_settings));
        categoryAboutUs = (PreferenceCategory) findPreference(getString(com.apptitive.beautytips.R.string.pref_key_about_us));
        preferenceLocation = (ListPreference) findPreference(getString(com.apptitive.beautytips.R.string.pref_key_location));
        preferenceAboutUs = (Preference) findPreference(getString(com.apptitive.beautytips.R.string.pref_key_preference_about_us));
    }

    private void setBanglaTextToView() {
        categoryLocation.setTitle(getString(com.apptitive.beautytips.R.string.sync_title));
        categoryAboutUs.setTitle(Utilities.getBanglaSpannableString(getString(com.apptitive.beautytips.R.string.title_about_us), this));
        preferenceLocation.setTitle(Utilities.getBanglaSpannableString(getString(com.apptitive.beautytips.R.string.sync_options), this));
        preferenceAboutUs.setSummary(Utilities.getBanglaSpannableString(getString(com.apptitive.beautytips.R.string.title_about_us), this));

        preferenceAboutUs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AboutUsDialog aboutUsDialog = new AboutUsDialog(SettingsActivity.this);
                aboutUsDialog.show();
                return true;
            }
        });
    }

    private static OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                preference.setSummary(entries[index]);
                if (isPreferenceSelected & preferenceHelper.getBoolean(Constants.PREF_KEY_SYNC_SETTINGS)) {
                    AlarmUtil.setUpAlarm(settingsActivity, getSelectedDays(index));
                }
                isPreferenceSelected = true;
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        private int getSelectedDays(int index) {
            if (index == 1)
                return 1;
            else if (index == 2)
                return 7;
            return -1;
        }
    };

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
    }


}
