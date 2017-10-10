package com.drom.dromtesttask.data.preference;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.SOURCE )
@IntDef( {StatusSavePreferences.OK, StatusSavePreferences.ERROR} )
public @interface StatusSavePreferences
{
    int OK = 0;
    int ERROR = -1;
}
