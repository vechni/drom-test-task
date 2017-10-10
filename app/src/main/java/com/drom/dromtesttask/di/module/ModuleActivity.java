package com.drom.dromtesttask.di.module;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleActivity
{
    @NonNull private final AppCompatActivity activity;

    public ModuleActivity( @NonNull final AppCompatActivity activity ){
        this.activity = activity;
    }

    @Provides
    AppCompatActivity provideActivity(){
        return activity;
    }
}
