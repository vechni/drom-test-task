package com.drom.dromtesttask.common.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.drom.dromtesttask.di.component.ComponentFragment;
import com.drom.dromtesttask.di.component.DaggerComponentFragment;
import com.drom.dromtesttask.di.module.ModuleFragment;
import com.drom.dromtesttask.module.AppToolbar;
import com.drom.dromtesttask.module.UiRouter;

import javax.inject.Inject;

public abstract class BaseFragment
        extends MvpAppCompatFragment
{
    protected Activity activity;
    private ComponentFragment component;
    private ProgressDialog progressDialog;
    @Inject protected UiRouter uiRouter;
    @Inject protected AppToolbar toolbar;

    @Override
    public void onCreate( @Nullable final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        activity = getActivity();
        getComponent().inject(this);
    }

    protected ComponentFragment getComponent(){
        if( component == null ){
            component = DaggerComponentFragment.builder()
                    .moduleFragment(new ModuleFragment(this))
                    .componentActivity(((BaseActivity) activity).getComponent())
                    .build();
        }
        return component;
    }

    protected void openWaitDialog( @NonNull final String message, @Nullable final DialogInterface.OnCancelListener listener ){
        closeWaitDialog();

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setOnCancelListener(listener);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void closeWaitDialog(){
        if( progressDialog != null && progressDialog.isShowing() ){
            progressDialog.dismiss();
        }
    }

    protected void showToastShort( @NonNull final String message ){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong( @NonNull final String message ){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    protected void showToastLong( @StringRes final int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void showToastShort( @StringRes final int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void hideKeyboard(){
        final View view = activity.getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(){
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
