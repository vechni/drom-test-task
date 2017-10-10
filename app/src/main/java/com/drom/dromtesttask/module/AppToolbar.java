package com.drom.dromtesttask.module;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.drom.dromtesttask.R;

public class AppToolbar
{
    @NonNull
    private final Toolbar toolbar;

    public AppToolbar( @NonNull final AppCompatActivity activity ){
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void enableBackButton( @NonNull final AppCompatActivity activity ){
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(arrow->activity.onBackPressed());
    }
}
