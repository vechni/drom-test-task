package com.drom.dromtesttask.data.rest;

import android.content.Context;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.common.utils.NetworkUtils;
import com.drom.dromtesttask.data.exeptions.NoConnectivityException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor
        implements Interceptor
{
    @NonNull private final Context context;

    public ConnectivityInterceptor( @NonNull final Context context ){
        this.context = context;
    }

    @Override
    public Response intercept( @NonNull final Chain chain ) throws IOException{
        if( !NetworkUtils.isNetworkConnection(context) ){
            throw new NoConnectivityException(context);
        }

        final Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
