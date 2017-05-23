package com.drom.dromtesttask.common.mvp;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.StringRes;

public interface BaseView<T> {

    public void openWaitDialog(Activity mActivity, String message, DialogInterface.OnCancelListener listener);

    public void closeWaitDialog();

    public void hideKeyboard();

    public void showKeyboard();

    public void showToastShort(String message);

    public void showToastLong(String message);

    public void showToastShort(@StringRes int resId);

    public void showToastLong(@StringRes int resId);

    public float convertPixToDp(int pixels);

    public float convertDpToPix(int dip);

    public void goBack();
}
