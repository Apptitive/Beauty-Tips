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
public class ContentMenu {

    @DatabaseField
    private int actionId;

    @DatabaseField(id = true)
    private String menuId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String actionType;

    @DatabaseField
    private int patternId;

    @DatabaseField
    private int subPatternId;

    public ContentMenu() {
    }

    public ContentMenu(int actionId, String menuId, String title, String actionType, int patternId, int subPatternId) {
        this.actionId = actionId;
        this.menuId = menuId;
        this.title = title;
        this.actionType = actionType;
        this.patternId = patternId;
        this.subPatternId = subPatternId;
    }

    public static void updateDb(DbManager dbManager, List<ContentMenu> contentMenus) {
        for (ContentMenu contentMenu : contentMenus) {
            if (contentMenu.getActionType().equals(Constants.db_action.ADD)) {
                dbManager.addMenu(contentMenu);
            } else if (contentMenu.getActionType().equals(Constants.db_action.DELETE)) {
                dbManager.deleteMenu(contentMenu);
            } else if (contentMenu.getActionType().equals(Constants.db_action.UPDATE)) {
                dbManager.updateMenu(contentMenu);
            }
        }
    }

    public int getActionId() {
        return actionId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getTitle() {
        return title;
    }

    public String getActionType() {
        return actionType;
    }

    public int getPatternId() {
        return patternId;
    }

    public int getSubPatternId() {
        return subPatternId;
    }
}
