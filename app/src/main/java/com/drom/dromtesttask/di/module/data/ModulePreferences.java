package com.drom.dromtesttask.di.module.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.data.preference.Preferences;
import com.drom.dromtesttask.data.preference.PreferencesImpl;
import com.drom.dromtesttask.data.preference.PreferencesRxImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModulePreferences
{
    @Provides
    @Singleton
    Preferences.ImpPref providesPreferencesImpl( @NonNull final Context context ){
        return new PreferencesImpl(context);
    }

    @Provides
    @Singleton
    Preferences.RxPref PreferencesRxImpl( @NonNull final Context context ){
        return new PreferencesRxImpl(context);
    }
}
