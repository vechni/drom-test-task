package com.drom.dromtesttask.module.act_navigation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.interfaces.StatusSavePreferences;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.model.RepositoryItem;
import com.drom.dromtesttask.model.User;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    public static final String TAG = "tag_main_act_prs";
    private static final int START_LOAD_PAGE = 1;

    @Inject DataLayer dataLayer;
    @Inject Context context;
    private String paramSearch = null;

    NavigationPresenter() {
        getPresenterComponent().inject(this);
    }

    // region - Lifecycle -

    // endregion


    // region - Contract -

    @Override
    public void checkAuth() {
        unsubscribeOnDestroy(isExistUserInPref());
    }

    @Override
    public void logOut() {
        unsubscribeOnDestroy(removeUserFromPref(new User()));
    }

    @Override
    public void setTextChangesListenerOnSearchView(SearchView searchView) {
        unsubscribeOnDestroy(requestInputSearchParam(searchView));
    }

    @Override
    public void loadNextData(int page) {
        if (isNotNetworkConnection()) {
            String message = context.getString(R.string.error_no_network_connection);
            getViewState().showMessage(message);
            return;
        }

        getViewState().startWaitDialog();

        unsubscribeOnDestroy(loadNextDataSearchParam(page));
    }

    // endregion


    // region - EventBus Handlers -

    //endregion


    // region - Methods -

    private Disposable isExistUserInPref() {
        return dataLayer.prefRx.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getLogin() != null) {
                        getViewState().showAuthorisedMenuToolbar();
                    } else {
                        getViewState().showNotAuthorisedMenuToolbar();
                    }
                }, error -> {
                    getViewState().showNotAuthorisedMenuToolbar();
                });
    }

    private Disposable removeUserFromPref(User user) {
        return dataLayer.prefRx.saveUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result == StatusSavePreferences.OK) {
                        getViewState().showNotAuthorisedMenuToolbar();
                    }
                }, error -> {
                    String message = context.getString(R.string.message_error);
                    getViewState().showMessage(message);
                });
    }

    private Disposable requestInputSearchParam(SearchView searchView) {
        return RxSearchView.queryTextChanges(searchView)
                .skip(1)
                .debounce(1500, TimeUnit.MILLISECONDS)
                .filter(charSequence -> charSequence.length() > 1)
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

    private void processOnResultSearchRequest(List<RepositoryItem> list) {
        getViewState().finishWaitDialog();

        if (list.isEmpty()) {
            String warning = context.getString(R.string.warning_no_result_search_query);
            getViewState().showWarning(warning);
        } else {
            getViewState().showSearchResult(list);
        }
    }

    private void processOnErrorSearchRequest(Throwable error) {
        getViewState().finishWaitDialog();
        getViewState().showWarning(error.getMessage());
    }

    private Disposable loadNextDataSearchParam(int page) {
        return dataLayer.restApi.requestSearchRepositories(paramSearch, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().finishWaitDialog();
                    if (!result.isEmpty()) {
                        getViewState().addLoadedData(result);
                    }
                }, error -> {
                    getViewState().finishWaitDialog();
                });
    }

    private boolean isNotNetworkConnection() {
        return !dataLayer.restApi.isNetworkConnection();
    }

    private String checkNetworkConnectionAndReturnParamQuery(String query) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (dataLayer.restApi.isNetworkConnection()) {
            handler.post(() -> getViewState().startWaitDialog());
            return query;
        } else {
            String message = context.getString(R.string.error_no_network_connection);
            handler.post(() -> getViewState().showWarning(message));
            return "";
        }
    }

    // endregion
}
