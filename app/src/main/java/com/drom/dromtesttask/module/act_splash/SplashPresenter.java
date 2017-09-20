package com.drom.dromtesttask.module.act_splash;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.common.mvp.BasePresenter;
import com.drom.dromtesttask.data.DataLayer;

import javax.inject.Inject;

@InjectViewState
public class SplashPresenter
        extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter
{
    public static final String TAG = SplashPresenter.class.getSimpleName();

    @Inject DataLayer dataLayer;
    @Inject Context context;

    SplashPresenter(){
        getPresenterComponent().inject(this);
    }
}