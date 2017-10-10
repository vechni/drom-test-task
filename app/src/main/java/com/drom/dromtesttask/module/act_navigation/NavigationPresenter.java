package com.drom.dromtesttask.module.act_navigation;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.data.exeptions.NoConnectivityException;
import com.drom.dromtesttask.data.model.RepositoryItemDTO;
import com.drom.dromtesttask.data.preference.StatusSavePreferences;
import com.drom.dromtesttask.module.act_navigation.item.RepositoryViewModel;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NavigationPresenter
        extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter
{
    public static final String TAG = NavigationPresenter.class.getSimpleName();
    private static final String WARNING_NO_DESCRIPTION = "The author did not leave a description";
    private static final int START_LOAD_PAGE = 1;
    private static final int MIN_LIMIT_SEARCH_REQUEST = 1;

    @Inject DataLayer dataLayer;
    private String paramSearch = null;
    private int currentPage = START_LOAD_PAGE;

    NavigationPresenter(){
        getPresenterComponent().inject(this);
    }

    @Override
    public void onClickBtnLogIn(){
        getViewState().navigateToLogInScreen();
    }

    @Override
    public void onClickBtnLogOut(){
        unsubscribeOnDestroy(processLogOut());
    }

    @NonNull
    private Disposable processLogOut(){
        return dataLayer.pref.removeUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultLogOut, this::processOnErrorLogOut);
    }

    private void processOnResultLogOut( @NonNull final Integer result ){
        if( result == StatusSavePreferences.OK ){
            getViewState().showUnauthorizedMenu();
        }
    }

    private void processOnErrorLogOut( @NonNull final Throwable error ){
        if( error instanceof NoConnectivityException ){
            getViewState().showMessage(error.getMessage());
        }else{
            getViewState().showMessage(R.string.message_error);
        }
    }

    @Override
    public void onLoadMore(){
        unsubscribeOnDestroy(loadMoreRepositories(currentPage));
        currentPage += 1;
    }

    @NonNull
    private Disposable loadMoreRepositories( final int page ){
        getViewState().showWaitDialog();

        return dataLayer.restApi.requestRepositories(paramSearch, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultLoadMoreRepositories, this::processOnErrorLoadMoreRepositories);
    }

    private void processOnResultLoadMoreRepositories( @NonNull final List<RepositoryItemDTO> list ){
        getViewState().hideWaitDialog();
        if( !list.isEmpty() ){
            getViewState().updateNextRepositories(mapToViewModel(list));
        }
    }

    private void processOnErrorLoadMoreRepositories( @NonNull final Throwable error ){
        getViewState().hideWaitDialog();
        if( error instanceof NoConnectivityException ){
            getViewState().showMessage(error.getMessage());
        }
    }

    @Override
    public void onChangeSearchView( @NonNull final SearchView searchView ){
        unsubscribeOnDestroy(requestRepositories(searchView));
    }

    @NonNull
    private Disposable requestRepositories( @NonNull final SearchView searchView ){
        return RxSearchView.queryTextChanges(searchView)
                .subscribeOn(AndroidSchedulers.mainThread())
                .skip(1)
                .debounce(1500, TimeUnit.MILLISECONDS)
                .filter(charSequence->charSequence.length() > MIN_LIMIT_SEARCH_REQUEST)
                .map(CharSequence::toString)
                .filter(query->!TextUtils.isEmpty(query))
                .map(query->paramSearch = query)
                .flatMap(query->dataLayer.restApi.requestRepositories(query, START_LOAD_PAGE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultSearchRequest, this::processOnErrorSearchRequest);
    }

    private void processOnResultSearchRequest( @NonNull final List<RepositoryItemDTO> list ){
        getViewState().hideWaitDialog();

        if( list.isEmpty() ){
            getViewState().showWarning(R.string.warning_no_result_search_query);
        }else{
            getViewState().hideWarning();
            getViewState().updateRepositories(mapToViewModel(list));
        }
    }

    private void processOnErrorSearchRequest( @NonNull final Throwable error ){
        getViewState().hideWaitDialog();
        if( error instanceof NoConnectivityException ){
            getViewState().showMessage(error.getMessage());
        }else{
            getViewState().showWarning(R.string.warning_no_result_search_query);
        }
    }

    @NonNull
    private List<RepositoryViewModel> mapToViewModel( @NonNull final List<RepositoryItemDTO> items ){
        final List<RepositoryViewModel> list = new ArrayList<>();
        for( RepositoryItemDTO item : items ){
            String desc = item.getDescription();
            if( TextUtils.isEmpty(desc) ){
                desc = WARNING_NO_DESCRIPTION;
            }
            list.add(new RepositoryViewModel(item.getFullName(), desc, item.getOwner().getAvatarUrl()));
        }
        return list;
    }
}
