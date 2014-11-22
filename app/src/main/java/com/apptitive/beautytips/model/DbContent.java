package com.apptitive.beautytips.model;

import com.apptitive.beautytips.helper.DbManager;
import com.apptitive.beautytips.utilities.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by rayhan on 7/13/2014.
 */
@DatabaseTable
public class DbContent {
    @DatabaseField
    private int actionId;

    @DatabaseField(id = true)
    private String contentId;

    @DatabaseField
    private String menuId;

    @DatabaseField
    private String header;

    @DatabaseField
    private String shortDescription;

    @DatabaseField
    private String details;

    @DatabaseField
    private String viewType;

    @DatabaseField
    private String actionType;

    public DbContent() {
    }

    public DbContent(int actionId, String contentId, String menuId, String header, String shortDescription, String details, String viewType, String actionType) {
        this.actionId = actionId;
        this.contentId = contentId;
        this.menuId = menuId;
        this.header = header;
        this.shortDescription = shortDescription;
        this.details = details;
        this.viewType = viewType;
        this.actionType = actionType;
    }

    public static void updateDb(DbManager dbManager, List<DbContent> dbContents) {
        for (DbContent dbContent : dbContents) {
            if (dbContent.getActionType().equals(Constants.db_action.ADD)) {
                dbManager.addDbContent(dbContent);
            } else if (dbContent.getActionType().equals(Constants.db_action.DELETE)) {
                dbManager.deleteDbContent(dbContent);
            } else if (dbContent.getActionType().equals(Constants.db_action.UPDATE)) {
                dbManager.updateDbContent(dbContent);
            }
        }
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
