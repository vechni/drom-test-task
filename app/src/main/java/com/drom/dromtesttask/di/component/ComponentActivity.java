package com.drom.dromtesttask.di.component;

import android.support.v7.app.AppCompatActivity;

import com.drom.dromtesttask.common.mvp.BaseActivity;
import com.drom.dromtesttask.di.module.ModuleActivity;
import com.drom.dromtesttask.di.module.data.ModuleToolbar;
import com.drom.dromtesttask.di.module.data.ModuleUiRouter;
import com.drom.dromtesttask.di.scope.ScopeActivity;
import com.drom.dromtesttask.module.AppToolbar;
import com.drom.dromtesttask.module.UiRouter;
import com.drom.dromtesttask.module.act_navigation.NavigationActivity;

import dagger.Component;

@ScopeActivity
@Component( dependencies = ComponentApplication.class, modules = {ModuleActivity.class, ModuleUiRouter.class, ModuleToolbar.class} )
public interface ComponentActivity
{
    AppCompatActivity activity();

    UiRouter uiRouter();

    AppToolbar appToolbar();

    void inject( BaseActivity activity );

    void inject( NavigationActivity activity );
}
