package com.apptitive.beautytips.helper;

import android.content.Context;

import com.apptitive.beautytips.model.ContentMenu;
import com.apptitive.beautytips.model.DbContent;
import com.apptitive.beautytips.model.Region;
import com.apptitive.beautytips.model.TimeTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 5/27/2014.
 */
public class DbManager {

    static private DbManager instance;

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DbManager(ctx);
        }
    }

    static public DbManager getInstance() {
        return instance;
    }

    private DbHelper helper;

    private DbManager(Context ctx) {
        helper = new DbHelper(ctx);
    }

    private DbHelper getHelper() {
        return helper;
    }

    public List<Region> getAllRegions() {
        List<Region> regionList = null;
        try {
            regionList = getHelper().getRegionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public String[] getAllRegionNames() {
        ArrayList<String> regionNames = new ArrayList<String>();
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            regionNames.add(region.getName());
        }
        return regionNames.toArray(new String[regionNames.size()]);
    }

    public Region getRegionWithName(String name) {
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            if (region.getName().equals(name))
                return region;
        }
        return new Region("1", "Dhaka", "", true, 0, 0);
    }

    public Region getDonorWithId(String regionId) {
        Region region = null;
        try {
            region = getHelper().getRegionDao().queryForId(regionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    public void addRegion(Region region) {
        try {
            getHelper().getRegionDao().create(region);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRegion(Region region) {
        try {
            getHelper().getRegionDao().update(region);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<TimeTable> getAllTimeTables() {
        List<TimeTable> timeTableList = null;
        try {
            timeTableList = getHelper().getTimeTableDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTableList;
    }

    public TimeTable getTimeTableWithId(String timeTableId) {
        TimeTable timeTable = null;
        try {
            timeTable = getHelper().getTimeTableDao().queryForId(timeTableId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTable;
    }

    public void addTimeTable(TimeTable timeTable) {
        try {
            getHelper().getTimeTableDao().create(timeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTimeTable(TimeTable timeTable) {
        try {
            getHelper().getTimeTableDao().update(timeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String[] getAllBanglaRegionNames() {
        ArrayList<String> regionNames = new ArrayList<String>();
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            regionNames.add(region.getNameInBangla());
        }
        return regionNames.toArray(new String[regionNames.size()]);
    }


    public List<DbContent> getAllDbContent() {
        List<DbContent> topicsList = null;
        try {
            topicsList = getHelper().getTopicsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicsList;
    }


    public void addDbContent(DbContent topics) {
        try {
            getHelper().getTopicsDao().create(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDbContent(List<DbContent> topics) {
        try {
            for (DbContent t : topics) {
                getHelper().getTopicsDao().create(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDbContent(DbContent topics) {
        try {
            getHelper().getTopicsDao().delete(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DbContent> getDbContentForMenu(String menuId) {
        List<DbContent> tList = new ArrayList<DbContent>();
        try {
            tList = getHelper().getTopicsDao().queryForEq("menuId", menuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tList;
    }

    public void updateDbContent(DbContent topics) {
        try {
            getHelper().getTopicsDao().update(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ContentMenu> getAllMenus() {
        List<ContentMenu> menuList = null;
        try {
            menuList = getHelper().getMenuDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }


    public void addMenu(ContentMenu menu) {
        try {
            getHelper().getMenuDao().create(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMenu(List<ContentMenu> menus) {
        try {
            for (ContentMenu m : menus) {
                getHelper().getMenuDao().create(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(ContentMenu menu) {
        try {
            getHelper().getMenuDao().delete(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenu(ContentMenu menu) {
        try {
            getHelper().getMenuDao().update(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


