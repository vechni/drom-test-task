package com.drom.dromtesttask.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class NetworkUtils
{
    public static final String URL_BACKEND = "https://api.github.com/";

    public static final int CONNECT_TIMEOUT = 10;
    public static final int WRITE_TIMEOUT = 10;
    public static final int READ_TIMEOUT = 10;
    public static final int WAIT_TIMEOUT = 10;

    public static final String URL_METHOD_AUTHORIZATION = "user";
    public static final String URL_METHOD_SEARCH_REPOSITORIES = "search/repositories";

    public static boolean isNetworkConnection( @NonNull final Context context ){
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
