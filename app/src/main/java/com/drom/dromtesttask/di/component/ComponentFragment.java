package com.drom.dromtesttask.di.component;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.drom.dromtesttask.di.module.ModuleFragment;
import com.drom.dromtesttask.di.scope.ScopeFragment;

import javax.inject.Named;

import dagger.Component;

@ScopeFragment
@Component(dependencies = ComponentApplication.class, modules = ModuleFragment.class)
public interface ComponentFragment {

    @Named("FrgActivity")
    Activity activity();

    Fragment fragment();
}
