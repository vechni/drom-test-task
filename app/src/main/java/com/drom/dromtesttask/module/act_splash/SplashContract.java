package com.drom.dromtesttask.module.act_splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public class SplashContract {

    public interface View extends MvpView {

        @StateStrategyType(SkipStrategy.class)
        void navigateToLogIn();
    }


    public interface Presenter {

    }
}