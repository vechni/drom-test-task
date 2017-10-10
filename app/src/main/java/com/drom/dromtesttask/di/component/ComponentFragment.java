package com.drom.dromtesttask.di.component;

import android.support.v4.app.Fragment;

import com.drom.dromtesttask.common.mvp.BaseFragment;
import com.drom.dromtesttask.di.module.ModuleFragment;
import com.drom.dromtesttask.di.scope.ScopeFragment;

import dagger.Component;

@ScopeFragment
@Component( dependencies = ComponentActivity.class, modules = ModuleFragment.class )
public interface ComponentFragment
{
    Fragment fragment();

    void inject( BaseFragment fragment );
}
