package com.drom.dromtesttask.module.act_log_in;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.model.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class LogInPresenter extends BasePresenter<LogInContract.View> implements LogInContract.Presenter {

    public static final String TAG = "tag_log_in_prs";

    @Inject DataLayer dataLayer;
    @Inject Context context;

    LogInPresenter() {
        getPresenterComponent().inject(this);
    }

    // region - Lifecycle -

    // endregion


    // region - Contract -

    @Override
    public void skipLogin() {
        unsubscribeOnDestroy(saveUserInPref(new User()));
    }

    @Override
    public void checkLogin(String login, String password) {
        if (isNotNetworkConnection()) {
            String message = context.getString(R.string.error_no_network_connection);
            getViewState().showMessage(message);
            return;
        }

        getViewState().startWaitDialog();

        unsubscribeOnDestroy(requestAuth(login, password));
    }

    // endregion


    // region - EventBus Handlers -

    // endregion


    // region - Methods -

    private boolean isNotNetworkConnection() {
        return !dataLayer.restApi.isNetworkConnection();
    }

    private Disposable saveUserInPref(User user) {
        return dataLayer.prefRx.saveUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().navigateToMainScreen();
                }, error -> {
                    String message = context.getString(R.string.message_error);
                    getViewState().showMessage(message);
                });
    }

    private Disposable requestAuth(String login, String password) {
        return dataLayer.restApi.requestAuth(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultRequestAuth, error -> {
                    processOnErrorResultRequestAuth();
                });
    }

    private void processOnResultRequestAuth(User user) {
        getViewState().finishWaitDialog();
        dataLayer.pref.saveUser(user);
        getViewState().navigateToMainScreen();
    }

    private void processOnErrorResultRequestAuth() {
        getViewState().finishWaitDialog();
        String message = context.getString(R.string.error_authorization);
        getViewState().showMessage(message);
        getViewState().showErrorRegistration();
    }

    // endregion
}