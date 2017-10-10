package com.drom.dromtesttask.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;

public class AppUtils
{
    public static String getPackageNameApp( @NonNull final Context context ){
        return context.getPackageName();
    }

    public static int getVersionCodeApp( @NonNull final Context context ) throws NameNotFoundException{
        final String packageName = getPackageNameApp(context);

        final PackageManager manager = context.getPackageManager();
        final PackageInfo info = manager.getPackageInfo(packageName, 0);

        return info.versionCode;
    }

    public static String getVersionNameApp( @NonNull final Context context ) throws NameNotFoundException{
        final String packageName = getPackageNameApp(context);

        final PackageManager manager = context.getPackageManager();
        final PackageInfo info = manager.getPackageInfo(packageName, 0);

        return info.versionName;
    }
}
