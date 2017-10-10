package com.drom.dromtesttask.module.act_navigation;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.SearchView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.drom.dromtesttask.module.act_navigation.item.RepositoryViewModel;

import java.util.List;

interface NavigationContract
{
    interface View
            extends MvpView
    {
        @StateStrategyType( SkipStrategy.class )
        void showAuthorisedMenu();

        @StateStrategyType( SkipStrategy.class )
        void showUnauthorizedMenu();

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @StringRes int resId );

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @NonNull String message );

        @StateStrategyType( AddToEndSingleStrategy.class )
        void showWaitDialog();

        @StateStrategyType( AddToEndSingleStrategy.class )
        void hideWaitDialog();

        @StateStrategyType( SingleStateStrategy.class )
        void showWarning( @StringRes int resId );

        @StateStrategyType( SingleStateStrategy.class )
        void hideWarning();

        @StateStrategyType( SingleStateStrategy.class )
        void updateRepositories( @NonNull List<RepositoryViewModel> list );

        @StateStrategyType( SkipStrategy.class )
        void updateNextRepositories( @NonNull List<RepositoryViewModel> list );

        @StateStrategyType( SkipStrategy.class )
        void navigateToLogInScreen();
    }


    interface Presenter
    {
        void onClickBtnLogOut();

        void onClickBtnLogIn();

        void onChangeSearchView( @NonNull SearchView searchView );

        void onLoadMore();
    }
}