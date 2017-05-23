package com.drom.dromtesttask.module.act_navigation;

import android.widget.SearchView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.drom.dromtesttask.model.RepositoryItem;

import java.util.List;

public class NavigationContract {

    public interface View extends MvpView {

        @StateStrategyType(SkipStrategy.class)
        void showAuthorisedMenuToolbar();

        @StateStrategyType(SkipStrategy.class)
        void showNotAuthorisedMenuToolbar();

        @StateStrategyType(SkipStrategy.class)
        void showMessage(String message);

        @StateStrategyType(AddToEndSingleStrategy.class)
        void startWaitDialog();

        @StateStrategyType(AddToEndSingleStrategy.class)
        void finishWaitDialog();

        @StateStrategyType(SingleStateStrategy.class)
        void showWarning(String message);

        @StateStrategyType(SingleStateStrategy.class)
        void showSearchResult(List<RepositoryItem> list);

        @StateStrategyType(SkipStrategy.class)
        void addLoadedData(List<RepositoryItem> list);
    }

    public interface Presenter {

        void checkAuth();

        void logOut();

        void setTextChangesListenerOnSearchView(SearchView searchView);

        void loadNextData(int page);
    }
}