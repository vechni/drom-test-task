package com.drom.dromtesttask.data.exeptions;

import android.content.Context;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.R;

import java.io.IOException;

public class NoConnectivityException
        extends IOException
{
    @NonNull private final Context context;

    public NoConnectivityException( @NonNull final Context context ){
        this.context = context;
    }

    @Override
    public String getMessage(){
        return context.getString(R.string.message_not_network_connection);
    }
}
