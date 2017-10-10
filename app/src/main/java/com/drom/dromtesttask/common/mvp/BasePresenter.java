package com.drom.dromtesttask.common.mvp;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.drom.dromtesttask.GitNavDromApplication;
import com.drom.dromtesttask.di.component.ComponentPresenter;
import com.drom.dromtesttask.di.component.DaggerComponentPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends MvpView>
        extends MvpPresenter<V>
{
    private ComponentPresenter component;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    protected ComponentPresenter getPresenterComponent(){
        if( component == null ){
            component = DaggerComponentPresenter.builder()
                    .componentApplication(GitNavDromApplication.getComponentApplication())
                    .build();
        }

        return component;
    }

    protected void unsubscribeOnDestroy( @NonNull final Disposable disposable ){
        compositeSubscription.add(disposable);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        compositeSubscription.clear();
    }
}
