package com.apptitive.beautytips;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.apptitive.beautytips.helper.DbManager;
import com.apptitive.beautytips.helper.DisplayPattern;
import com.apptitive.beautytips.helper.Helper;
import com.apptitive.beautytips.model.ContentMenu;
import com.apptitive.beautytips.services.SyncService;
import com.apptitive.beautytips.utilities.Config;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.HttpHelper;
import com.apptitive.beautytips.utilities.LogUtil;
import com.apptitive.beautytips.utilities.PreferenceHelper;

import java.util.List;


public class StartActivity extends ActionBarActivity {

    private List<ContentMenu> contentMenuList;
    private LinearLayout llMain;
    private int currentMenu;
    private boolean isContentShowInOncreate = true;
    private PreferenceHelper preferenceHelper;
    private BroadcastReceiver myBroadCastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.LOGE("inside broadcast receiver");
            renderContentMenu();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();


        preferenceHelper = new PreferenceHelper(this);
        if (!isFirstTimeAppLaunched()) {
            IntentFilter mStatusIntentFilter = new IntentFilter(
                    Constants.ACTION_RESPONSE);
            LocalBroadcastManager.getInstance(this).registerReceiver(
                    myBroadCastReceiver, mStatusIntentFilter);

            Intent intent = new Intent(this, SyncService.class);
            startService(intent);
            preferenceHelper.setBoolean(Constants.APP_FIRST_TIME_CREATED, true);
            isContentShowInOncreate = false;
        }
    }

    private boolean isFirstTimeAppLaunched() {
        return preferenceHelper.getBoolean(Constants.APP_FIRST_TIME_CREATED);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DbManager.init(this);
        if (isContentShowInOncreate)
            renderContentMenu();
    }

    public void renderContentMenu() {
        llMain = (LinearLayout) findViewById(R.id.ll_main);
        contentMenuList = DbManager.getInstance().getAllMenus();

        for (currentMenu = 0; currentMenu < contentMenuList.size(); ) {
            int patternId = contentMenuList.get(currentMenu).getPatternId();

            if (patternId == 1) {
                View view = getViewForContentMenuPattern(R.layout.menu_pattern_1);
                populateContentMenuItem(view, R.id.sub_pattern_left_top, contentMenuList.get(currentMenu++), DisplayPattern.LeftToRight);
                populateContentMenuItem(view, R.id.sub_pattern_left_bottom, contentMenuList.get(currentMenu++), DisplayPattern.LeftToRight);
                populateContentMenuItem(view, R.id.sub_pattern_right, contentMenuList.get(currentMenu++), DisplayPattern.TopToBottom);
            } else if (patternId == 2) {
                View view = getViewForContentMenuPattern(R.layout.menu_pattern_2);
                populateContentMenuItem(view, R.id.sub_pattern_left, contentMenuList.get(currentMenu++), DisplayPattern.TopToBottom);
                populateContentMenuItem(view, R.id.sub_pattern_right_top, contentMenuList.get(currentMenu++), DisplayPattern.LeftToRight);
                populateContentMenuItem(view, R.id.sub_pattern_right_bottom, contentMenuList.get(currentMenu++), DisplayPattern.LeftToRight);
            } else if (patternId == 3) {
                View view = getViewForContentMenuPattern(R.layout.menu_pattern_3);
                populateContentMenuItem(view, R.id.sub_pattern_whole, contentMenuList.get(currentMenu++), DisplayPattern.Fill);
            }
        }
    }

    private View getViewForContentMenuPattern(int layoutId) {

        ViewStub viewStub = new ViewStub(this, layoutId);
        llMain.addView(viewStub);
        View view = viewStub.inflate();
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(lp);

        return view;
    }


    private void populateContentMenuItem(View view, int subPatternId, final ContentMenu menu, Enum displayPattern) {
        ViewStub stub = (ViewStub) view.findViewById(subPatternId);
        if (displayPattern.equals(DisplayPattern.LeftToRight)) {
            if (Helper.isPortraitMode(this.getWindowManager()))
                stub.setLayoutResource(R.layout.partial_view_left_right);
            else
                stub.setLayoutResource(R.layout.partial_view_top_to_bottom);
        } else if (displayPattern.equals(DisplayPattern.TopToBottom)) {
            stub.setLayoutResource(R.layout.partial_view_top_to_bottom);
        } else if (displayPattern.equals(DisplayPattern.Fill)) {
            stub.setLayoutResource(R.layout.partial_view_fill);
        }
        View v = stub.inflate();
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        textView.setText(menu.getTitle());
        ImageLoader imageLoader = HttpHelper.getInstance(this).getImageLoader();
        NetworkImageView imgNetWorkView = (NetworkImageView) v.findViewById(R.id.niv_icon);
        imgNetWorkView.setImageUrl(Config.getImageUrl(this) + menu.getMenuId() + ".9.png", imageLoader);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("View Click", menu.getTitle());
                Intent i = new Intent(getBaseContext(), ContentActivity.class);
                i.putExtra(Constants.menu.EXTRA_MENU_ID, menu.getMenuId());
                i.putExtra(Constants.menu.EXTRA_MENU_TITLE, menu.getTitle());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
