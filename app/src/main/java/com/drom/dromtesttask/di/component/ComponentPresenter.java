package com.drom.dromtesttask.di.component;

import com.drom.dromtesttask.di.scope.ScopePresenter;
import com.drom.dromtesttask.module.act_log_in.LogInPresenter;
import com.drom.dromtesttask.module.act_navigation.NavigationPresenter;
import com.drom.dromtesttask.module.act_splash.SplashPresenter;

import dagger.Component;

@ScopePresenter
@Component(dependencies = ComponentApplication.class)
public interface ComponentPresenter {

    void inject(SplashPresenter presenter);

    void inject(LogInPresenter presenter);

    void inject(NavigationPresenter presenter);
}
