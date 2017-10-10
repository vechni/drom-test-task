package com.drom.dromtesttask.common.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public class Dips
{
    public static float toPix( @NonNull final Context context, int dip ){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }
}
