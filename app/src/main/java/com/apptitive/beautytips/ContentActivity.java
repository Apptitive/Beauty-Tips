package com.apptitive.beautytips;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.apptitive.beautytips.utilities.Config;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.HttpHelper;
import com.apptitive.beautytips.utilities.Utilities;


public class ContentActivity extends BaseActionBar implements ContentFragment.ContentProvider {

    private String menuId, menuTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuId = extras.getString(Constants.menu.EXTRA_MENU_ID);
            menuTitle = extras.getString(Constants.menu.EXTRA_MENU_TITLE);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarHomeBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(menuTitle, this));

        ImageLoader imageLoader = HttpHelper.getInstance(this).getImageLoader();
        imageLoader.get(Config.getImageUrl(this) + menuId + "_ab_title.png", new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    actionBar.setIcon(new BitmapDrawable(getResources(), response.getBitmap()));
                }
            }
        });
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_content);
    }

    @Override
    public String getMenuId() {
        return menuId;
    }
}
