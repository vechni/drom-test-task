package com.drom.dromtesttask.common.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.drom.dromtesttask.GitNavDromApplication;
import com.drom.dromtesttask.di.component.ComponentActivity;
import com.drom.dromtesttask.di.component.DaggerComponentActivity;
import com.drom.dromtesttask.di.module.ModuleActivity;
import com.drom.dromtesttask.module.UiRouter;

import javax.inject.Inject;

public abstract class BaseActivity
        extends MvpAppCompatActivity
{
    private ComponentActivity component;
    private ProgressDialog progressDialog;
    @Inject protected UiRouter uiRouter;

    @CallSuper
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    protected ComponentActivity getComponent(){
        if( component == null ){
            component = DaggerComponentActivity
                    .builder()
                    .moduleActivity(new ModuleActivity(this))
                    .componentApplication(GitNavDromApplication.getComponentApplication())
                    .build();
        }
        return component;
    }

    protected void openWaitDialog( @NonNull final String message, @Nullable final DialogInterface.OnCancelListener listener ){
        closeWaitDialog();

        progressDialog = new ProgressDialog(this);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong( @NonNull final String message ){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void showToastLong( @StringRes final int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void showToastShort( @StringRes final int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void hideKeyboard(){
        final View view = getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(){
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
