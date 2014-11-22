package com.apptitive.beautytips;

import android.support.v7.app.ActionBarActivity;


public class BaseActionBar extends ActionBarActivity {

    @Override
    protected void onStart() {
        super.onStart();
       // EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // EasyTracker.getInstance(this).activityStop(this);
    }
}
