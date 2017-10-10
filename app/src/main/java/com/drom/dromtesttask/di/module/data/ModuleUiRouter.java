package com.drom.dromtesttask.di.module.data;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.drom.dromtesttask.di.scope.ScopeActivity;
import com.drom.dromtesttask.module.UiRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleUiRouter
{
    @Provides
    @ScopeActivity
    public UiRouter getUiRouter( @NonNull final AppCompatActivity activity ){
        return new UiRouter(activity.getSupportFragmentManager(), activity);
    }
}
