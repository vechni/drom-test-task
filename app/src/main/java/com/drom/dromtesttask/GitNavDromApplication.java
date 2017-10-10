package com.drom.dromtesttask;

import android.app.Application;

import com.drom.dromtesttask.di.component.ComponentApplication;
import com.drom.dromtesttask.di.component.DaggerComponentApplication;
import com.drom.dromtesttask.di.module.ModuleApplication;
import com.drom.dromtesttask.di.module.data.ModulePreferences;
import com.drom.dromtesttask.di.module.data.ModuleRetrofit;

public class GitNavDromApplication
        extends Application
{
    private static ComponentApplication component;

    @Override
    public void onCreate(){
        super.onCreate();

        component = DaggerComponentApplication
                .builder()
                .moduleApplication(new ModuleApplication(this))
                .modulePreferences(new ModulePreferences())
                .moduleRetrofit(new ModuleRetrofit(this))
                .build();
    }

    public static ComponentApplication getComponentApplication(){
        return component;
    }
}
