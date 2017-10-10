package com.drom.dromtesttask.di.module;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleFragment
{
    @NonNull private final Fragment fragment;

    public ModuleFragment( @NonNull final Fragment fragment ){
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment(){
        return fragment;
    }
}
