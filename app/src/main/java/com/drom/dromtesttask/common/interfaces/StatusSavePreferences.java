package com.drom.dromtesttask.common.interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.SOURCE )
@IntDef( {StatusSavePreferences.OK, StatusSavePreferences.ERROR} )
public @interface StatusSavePreferences
{
    public static final int OK = 0;
    public static final int ERROR = - 1;
}
