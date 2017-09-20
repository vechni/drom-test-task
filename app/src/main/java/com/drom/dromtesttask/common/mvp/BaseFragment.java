package com.drom.dromtesttask.common.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.drom.dromtesttask.GitNavDromApplication;
import com.drom.dromtesttask.di.component.ComponentFragment;
import com.drom.dromtesttask.di.component.DaggerComponentFragment;
import com.drom.dromtesttask.di.module.ModuleFragment;

public abstract class BaseFragment
        extends MvpAppCompatFragment
{
    protected Activity activity;
    private ComponentFragment component;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    protected ComponentFragment getFragmentComponent(){
        if( component == null ){
            component = DaggerComponentFragment.builder()
                    .moduleFragment(new ModuleFragment(this))
                    .componentApplication(GitNavDromApplication.getComponentApplication())
                    .build();
        }
        return component;
    }

    protected void showOnBackPressedToolbar(){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    protected void hideOnBackPressedToolbar(){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    protected void openWaitDialog( String message, DialogInterface.OnCancelListener listener ){
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

    protected void showToastShort( String message ){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong( String message ){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    protected void showToastLong( @StringRes int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void showToastShort( @StringRes int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    protected void hideKeyboard(){
        View view = activity.getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    protected void goBack(){
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
