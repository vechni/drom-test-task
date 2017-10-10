package com.drom.dromtesttask.module.act_log_in;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.data.DataLayer;
import com.drom.dromtesttask.data.exeptions.NoConnectivityException;
import com.drom.dromtesttask.data.model.UserDTO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class LogInPresenter
        extends BasePresenter<LogInContract.View>
        implements LogInContract.Presenter
{
    public static final String TAG = LogInPresenter.class.getSimpleName();
    @Inject DataLayer dataLayer;

    LogInPresenter(){
        getPresenterComponent().inject(this);
    }

    @Override
    public void onClickBtnSkip(){
        unsubscribeOnDestroy(processClickBtnSkip(new UserDTO()));
    }

    @Override
    public void onClickBtnLogin( @NonNull final String login, @NonNull final String password ){
        getViewState().showWaitDialog();
        unsubscribeOnDestroy(requestAuth(login, password));
    }

    @NonNull
    private Disposable processClickBtnSkip( @NonNull final UserDTO user ){
        return dataLayer.pref.saveUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->processOnResultBtnSkip(),
                           this::processOnErrorBtnSkip);
    }

    private void processOnResultBtnSkip(){
        getViewState().navigateToMainScreen(false);
    }

    private void processOnErrorBtnSkip( @NonNull final Throwable error ){
        if( error instanceof NoConnectivityException ){
            getViewState().showMessage(error.getMessage());
        }else{
            getViewState().showMessage(R.string.message_error);
        }
    }

    @NonNull
    private Disposable requestAuth( @NonNull final String login, @NonNull final String password ){
        return dataLayer.restApi.requestAuth(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processOnResultRequestAuth, this::processOnErrorRequestAuth);
    }

    private void processOnResultRequestAuth( @NonNull final UserDTO user ){
        getViewState().hideWaitDialog();
        dataLayer.pref.saveUser(user);
        getViewState().navigateToMainScreen(true);
    }

    private void processOnErrorRequestAuth( @NonNull final Throwable error ){
        getViewState().hideWaitDialog();
        if( error instanceof NoConnectivityException ){
            getViewState().showMessage(error.getMessage());
        }else{
            getViewState().showMessage(R.string.error_authorization);
            getViewState().showErrorRegistration();
        }
    }
}