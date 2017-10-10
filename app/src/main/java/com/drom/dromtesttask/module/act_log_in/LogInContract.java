package com.drom.dromtesttask.module.act_log_in;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

interface LogInContract
{
    interface View
            extends MvpView
    {
        @StateStrategyType( AddToEndStrategy.class )
        void showWaitDialog();

        @StateStrategyType( AddToEndStrategy.class )
        void hideWaitDialog();

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @StringRes int resId );

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @NonNull String message );

        @StateStrategyType( SingleStateStrategy.class )
        void showErrorRegistration();

        @StateStrategyType( SkipStrategy.class )
        void navigateToMainScreen( boolean isAuthorized );
    }


    interface Presenter
    {
        void onClickBtnSkip();

        void onClickBtnLogin( @NonNull String login, @NonNull String password );
    }
}