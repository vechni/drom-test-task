package com.drom.dromtesttask.common.mvp;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public interface BaseView<T>
{
    void openWaitDialog( @NonNull Activity mActivity, @NonNull String message, @Nullable DialogInterface.OnCancelListener listener );

    void closeWaitDialog();

    void hideKeyboard();

    void showKeyboard();

    void showToastShort( @NonNull String message );

    void showToastLong( @NonNull String message );

    void showToastShort( @StringRes int resId );

    void showToastLong( @StringRes int resId );
}
