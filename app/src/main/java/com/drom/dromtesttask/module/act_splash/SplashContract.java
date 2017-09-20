package com.drom.dromtesttask.module.act_splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SplashContract
{
    interface View
            extends MvpView
    {

        @StateStrategyType( SkipStrategy.class )
        void navigateToLogInScreen();
    }


    interface Presenter
    {

    }
}