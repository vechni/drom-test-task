package com.drom.dromtesttask.module.act_navigation;

import android.support.annotation.NonNull;
import android.widget.SearchView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface NavigationContract
{
    interface View
            extends MvpView
    {
        @StateStrategyType( SkipStrategy.class )
        void showAuthorisedMenuToolbar();

        @StateStrategyType( SkipStrategy.class )
        void showNotAuthorisedMenuToolbar();

        @StateStrategyType( SkipStrategy.class )
        void showMessage( @NonNull String message );

        @StateStrategyType( AddToEndSingleStrategy.class )
        void startWaitDialog();

        @StateStrategyType( AddToEndSingleStrategy.class )
        void finishWaitDialog();

        @StateStrategyType( SingleStateStrategy.class )
        void showWarning( @NonNull String message );

        @StateStrategyType( SingleStateStrategy.class )
        void showSearchResult( @NonNull List<RepositoryViewModel> list );

        @StateStrategyType( SkipStrategy.class )
        void addLoadedData( @NonNull List<RepositoryViewModel> list );
    }


    interface Presenter
    {
        void checkAuth();

        void logOut();

        void setTextChangesListenerOnSearchView( SearchView searchView );

        void loadNextData( int page );
    }
}