package com.drom.dromtesttask.module;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.drom.dromtesttask.common.utils.AppKeys;
import com.drom.dromtesttask.module.act_log_in.LogInActivity;
import com.drom.dromtesttask.module.act_navigation.NavigationActivity;

public class UiRouter
{
    @NonNull private final FragmentManager fragmentManager;
    @NonNull private final Activity activity;

    public UiRouter( @NonNull final FragmentManager fragmentManager, @NonNull final Activity activity ){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    /*private void showFragment( @NonNull final Fragment frg ){
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, frg, frg.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }*/

    public void openMainView( final boolean isAuthorized ){
        final Intent intent = new Intent(activity, NavigationActivity.class);
        intent.putExtra(AppKeys.IS_AUTHORIZED, isAuthorized);
        activity.startActivity(intent);
        activity.finish();
    }

    public void openLogInView(){
        final Intent mainIntent = new Intent(activity, LogInActivity.class);
        activity.startActivity(mainIntent);
        activity.finish();
    }
}
