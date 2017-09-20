package com.drom.dromtesttask.common.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.drom.dromtesttask.GitNavDromApplication;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.di.component.ComponentActivity;
import com.drom.dromtesttask.di.component.DaggerComponentActivity;
import com.drom.dromtesttask.di.module.ModuleActivity;

public abstract class BaseActivity
        extends MvpAppCompatActivity
{
    private ComponentActivity component;
    private ProgressDialog progressDialog;

    protected ComponentActivity getComponentActivity(){
        if( component == null ){
            component = DaggerComponentActivity
                    .builder()
                    .moduleActivity(new ModuleActivity(this))
                    .componentApplication(GitNavDromApplication.getComponentApplication())
                    .build();
        }
        return component;
    }

    protected void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    protected void openWaitDialog( String message, DialogInterface.OnCancelListener listener ){
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

    protected void showToastShort( String message ){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong( String message ){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void showToastLong( @StringRes int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void showToastShort( @StringRes int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void hideKeyboard(){
        View view = getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    protected void goBack(){
        finish();
    }
}
