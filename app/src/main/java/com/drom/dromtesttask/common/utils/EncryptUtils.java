package com.drom.dromtesttask.common.utils;

import android.support.annotation.NonNull;
import android.util.Base64;

public class EncryptUtils
{
    @NonNull
    public static String encodeLoginData( @NonNull final String login, @NonNull final String password ){
        final String encode = Base64.encodeToString((login + ":" + password).getBytes(), Base64.DEFAULT).replace("\n", "");
        return "Basic " + encode;
    }
}
