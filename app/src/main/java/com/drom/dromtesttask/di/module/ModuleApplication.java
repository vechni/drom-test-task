package com.drom.dromtesttask.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleApplication
{
    @NonNull private final Application application;

    public ModuleApplication( @NonNull final Application application ){
        this.application = application;
    }

    @Provides
    Context provideContext(){
        return application.getApplicationContext();
    }
}
