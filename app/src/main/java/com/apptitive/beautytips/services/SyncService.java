package com.apptitive.beautytips.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.apptitive.beautytips.helper.DbManager;
import com.apptitive.beautytips.model.ContentMenu;
import com.apptitive.beautytips.model.DbContent;
import com.apptitive.beautytips.utilities.Config;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.HttpHelper;
import com.apptitive.beautytips.utilities.JsonParser;
import com.apptitive.beautytips.utilities.LogUtil;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

public class SyncService extends Service {

    private DbManager dbManager;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HttpHelper httpHelper = HttpHelper.getInstance(this);
        DbManager.init(this);
        dbManager = DbManager.getInstance();

        JsonArrayRequest menuRequest = new JsonArrayRequest(Config.getMenuUrl(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LogUtil.LOGE(response.toString());
                        ContentMenu.updateDb(dbManager, JsonParser.getParsedContentMenus(response.toString()));
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
                    String type = response.headers.get(HTTP.CONTENT_TYPE);
                    if (type == null) {
                        LogUtil.LOGE("content type was null");
                        type = TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    } else if (!type.contains("UTF-8")) {
                        LogUtil.LOGE("content type had UTF-8 missing");
                        type += ";" + TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    }
                } catch (Exception e) {
                    //print stacktrace e.g.
                }
                return super.parseNetworkResponse(response);
            }
        };
        httpHelper.addToRequestQueue(menuRequest);

        JsonArrayRequest topicRequest = new JsonArrayRequest(Config.getTopicUrl(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LogUtil.LOGE("topic url" + response.toString());
                        DbContent.updateDb(dbManager, JsonParser.getParsedDbContents(response));
                        Intent localIntent = new Intent(Constants.ACTION_RESPONSE);
                        LocalBroadcastManager.getInstance(SyncService.this).sendBroadcast(
                                localIntent);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        httpHelper.addToRequestQueue(topicRequest);
        LogUtil.LOGE("inside test service");
        return START_STICKY;
    }
}
