package com.drom.dromtesttask.di.component;

import android.app.Activity;

import com.drom.dromtesttask.di.module.ModuleActivity;
import com.drom.dromtesttask.di.scope.ScopeActivity;

import javax.inject.Named;

import dagger.Component;

@ScopeActivity
@Component( dependencies = ComponentApplication.class, modules = ModuleActivity.class )
public interface ComponentActivity
{
    @Named( "Activity" )
    Activity activity();
}
