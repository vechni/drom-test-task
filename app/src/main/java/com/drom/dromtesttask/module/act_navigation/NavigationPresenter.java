package com.drom.dromtesttask.module.act_navigation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.interfaces.StatusSavePreferences;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.common.utils.AppConst;
import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.model.RepositoryItemDTO;
import com.drom.dromtesttask.model.UserDTO;
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
    private static final int START_LOAD_PAGE = 1;
    public static final int NUMBER_SHORT_STRING = 1;

    @Inject DataLayer dataLayer;
    @Inject Context context;
    private String paramSearch = null;

    NavigationPresenter(){
        getPresenterComponent().inject(this);
    }

    @Override
    public void checkAuth(){
        unsubscribeOnDestroy(isExistUserInPref());
    }

    @Override
    public void logOut(){
        unsubscribeOnDestroy(removeUserFromPref(new UserDTO()));
    }

    @Override
    public void setTextChangesListenerOnSearchView( SearchView searchView ){
        unsubscribeOnDestroy(requestInputSearchParam(searchView));
    }

    @Override
    public void loadNextData( int page ){
        if( isNotNetworkConnection() ){
            String message = context.getString(R.string.error_no_network_connection);
            getViewState().showMessage(message);
            return;
        }

        getViewState().startWaitDialog();

        unsubscribeOnDestroy(loadNextDataSearchParam(page));
    }

    private Disposable isExistUserInPref(){
        return dataLayer.prefRx.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if( result.getLogin() != null ){
                        getViewState().showAuthorisedMenuToolbar();
                    }else{
                        getViewState().showNotAuthorisedMenuToolbar();
                    }
                }, error -> {
                    getViewState().showNotAuthorisedMenuToolbar();
                });
    }

    private Disposable removeUserFromPref( @NonNull final UserDTO user ){
        return dataLayer.prefRx.saveUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if( result == StatusSavePreferences.OK ){
                        getViewState().showNotAuthorisedMenuToolbar();
                    }
                }, error -> {
                    String message = context.getString(R.string.message_error);
                    getViewState().showMessage(message);
                });
    }

    private Disposable requestInputSearchParam( @NonNull final SearchView searchView ){
        return RxSearchView.queryTextChanges(searchView)
                .skip(1)
                .debounce(1500, TimeUnit.MILLISECONDS)
                .filter(charSequence -> charSequence.length() > NUMBER_SHORT_STRING)
                .map(CharSequence::toString)
                .map(this::checkNetworkConnectionAndReturnParamQuery)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .filter(query -> !TextUtils.isEmpty(query))
                .map(query -> paramSearch = query)
                .flatMap(query -> dataLayer.restApi.requestSearchRepositories(query, START_LOAD_PAGE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultSearchRequest, this::processOnErrorSearchRequest);
    }

    private void processOnResultSearchRequest( @NonNull final List<RepositoryItemDTO> list ){
        getViewState().finishWaitDialog();

        if( list.isEmpty() ){
            String warning = context.getString(R.string.warning_no_result_search_query);
            getViewState().showWarning(warning);
        }else{
            getViewState().showSearchResult(mapToViewModel(list));
        }
    }

    private void processOnErrorSearchRequest( @NonNull final Throwable error ){
        getViewState().finishWaitDialog();
        getViewState().showWarning(error.getMessage());
    }

    private Disposable loadNextDataSearchParam( int page ){
        return dataLayer.restApi.requestSearchRepositories(paramSearch, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().finishWaitDialog();
                    if( !result.isEmpty() ){
                        getViewState().addLoadedData(mapToViewModel(result));
                    }
                }, error -> {
                    getViewState().finishWaitDialog();
                });
    }

    private boolean isNotNetworkConnection(){
        return ! dataLayer.restApi.isNetworkConnection();
    }

    private String checkNetworkConnectionAndReturnParamQuery( @NonNull final String query ){
        Handler handler = new Handler(Looper.getMainLooper());
        if( dataLayer.restApi.isNetworkConnection() ){
            handler.post(() -> getViewState().startWaitDialog());
            return query;
        }else{
            String message = context.getString(R.string.error_no_network_connection);
            handler.post(() -> getViewState().showWarning(message));
            return AppConst.EMPTY_STRING;
        }
    }

    private List<RepositoryViewModel> mapToViewModel( @NonNull final List<RepositoryItemDTO> items ){
        List<RepositoryViewModel> list = new ArrayList<>();
        for( RepositoryItemDTO item : items ){
            list.add(new RepositoryViewModel(item.getFullName(), item.getDescription(), item.getOwner().getAvatarUrl()));
        }
        return list;
    }
}
