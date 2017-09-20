package com.drom.dromtesttask.di.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleActivity
{
    private final Activity activity;

    public ModuleActivity( @NonNull final Activity activity ){
        this.activity = activity;
    }

    @Provides
    @Named( "Activity" )
    Activity providesActivity(){
        return activity;
    }
}
