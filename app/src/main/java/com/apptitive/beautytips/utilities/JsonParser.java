package com.apptitive.beautytips.utilities;

import com.apptitive.beautytips.model.ContentMenu;
import com.apptitive.beautytips.model.DbContent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sharif on 7/19/2014.
 */
public class JsonParser {


    public static List<ContentMenu> getParsedContentMenus(String menuJsonString) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(menuJsonString, ContentMenu[].class));
    }

    public static List<DbContent> getParsedDbContents(JSONArray result) {
        List<DbContent> dbContents = new ArrayList<DbContent>();
        for (int i = 0; i < result.length(); i++) {
            try {
                JSONObject jsonObject = (JSONObject) result.get(i);
                DbContent dbContent = new DbContent();
                dbContent.setActionId((Integer) jsonObject.get("actionId"));
                dbContent.setContentId(jsonObject.get("contentId").toString());
                dbContent.setMenuId(jsonObject.get("menuId").toString());
                dbContent.setHeader(jsonObject.get("header").toString());
                dbContent.setShortDescription(jsonObject.get("shortDescription").toString());
                dbContent.setDetails(jsonObject.get("details").toString());
                dbContent.setViewType(jsonObject.get("viewType").toString());
                dbContent.setActionType(jsonObject.get("actionType").toString());
                dbContents.add(dbContent);
            } catch (JSONException e) {
                LogUtil.LOGE(e.getMessage());
                e.printStackTrace();
            }
        }

        return dbContents;
    }
}
