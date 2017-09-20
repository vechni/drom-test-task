package com.drom.dromtesttask.common.mvp;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.StringRes;

public interface BaseView<T>
{
    void openWaitDialog( Activity mActivity, String message, DialogInterface.OnCancelListener listener );

    void closeWaitDialog();

    void hideKeyboard();

    void showKeyboard();

    void showToastShort( String message );

    void showToastLong( String message );

    void showToastShort( @StringRes int resId );

    void showToastLong( @StringRes int resId );

    float convertPixToDp( int pixels );

    float convertDpToPix( int dip );

    void goBack();
}
