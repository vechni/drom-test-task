package com.drom.dromtesttask.di.module.data;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.drom.dromtesttask.di.scope.ScopeActivity;
import com.drom.dromtesttask.module.AppToolbar;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleToolbar
{
    @Provides
    @ScopeActivity
    public AppToolbar getToolbar( @NonNull final AppCompatActivity activity ){
        return new AppToolbar(activity);
    }
}
