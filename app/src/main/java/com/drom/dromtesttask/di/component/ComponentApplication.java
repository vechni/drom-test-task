package com.drom.dromtesttask.di.component;

import android.content.Context;

import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.di.module.ModuleApplication;
import com.drom.dromtesttask.di.module.data.ModulePreferences;
import com.drom.dromtesttask.di.module.data.ModuleRest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModuleApplication.class, ModulePreferences.class, ModuleRest.class})
public interface ComponentApplication {

    Context context();

    DataLayer dataLayer();
}

