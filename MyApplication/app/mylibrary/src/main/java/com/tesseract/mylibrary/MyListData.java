package com.tesseract.mylibrary;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

public class MyListData implements Comparator<MyListData> {
    private String name;
    private String versionCode;
    private String versionName;
    private String activityName;
    private Drawable icon;

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    private String packages;

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackages() {
        return packages;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public int compare(MyListData o1, MyListData o2) {
        return o1.name.compareTo(o2.name);
    }
}
