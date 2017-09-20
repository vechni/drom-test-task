package com.drom.dromtesttask.module.act_log_in;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface LogInContract
{
    interface View
            extends MvpView
    {
        @StateStrategyType( AddToEndStrategy.class )
        void startWaitDialog();

        @StateStrategyType( AddToEndStrategy.class )
        void finishWaitDialog();

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @NonNull String message );

        @StateStrategyType( SingleStateStrategy.class )
        void showErrorRegistration();

        @StateStrategyType( SkipStrategy.class )
        void navigateToMainScreen();
    }


    interface Presenter
    {
        void skipLogin();

        void checkLogin( @NonNull String login, @NonNull String password );
    }
}