package com.tesseract.mylibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyLib {

    public static MyLib INSTANCE;
    private ArrayList<MyListData> listOfApp;

    public static MyLib getInstance() {
        if (INSTANCE == null) {
            return new MyLib();
        }
        return INSTANCE;
    }

    public List<MyListData> getListOfInstalledApps(Context context) {
        listOfApp = new ArrayList<>();
        List<PackageInfo> packageInfos = context.getApplicationContext().getPackageManager().getInstalledPackages(0);
        for (int packageInfo = 0; packageInfo < packageInfos.size(); packageInfo++) {
            PackageInfo info = packageInfos.get(packageInfo);
            if ((!isGetPackage(info))) {
                MyListData myListData = new MyListData();
                String appName = info.applicationInfo.loadLabel(context.getPackageManager()).toString();
                String versionCode = String.valueOf(info.versionCode);
                String versionName = String.valueOf(info.versionName);
                Drawable drawable = info.applicationInfo.loadIcon(context.getPackageManager());
                String packageName = info.applicationInfo.packageName;
                myListData.setName(appName);
                myListData.setVersionCode(versionCode);
                myListData.setVersionName(versionName);
                myListData.setPackages(packageName);
                myListData.setIcon(drawable);
                listOfApp.add(myListData);
            }
            Collections.sort(listOfApp, new MyListData());
        }
        return listOfApp;
    }

    private boolean isGetPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}
