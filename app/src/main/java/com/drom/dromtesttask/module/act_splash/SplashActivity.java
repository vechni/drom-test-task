package com.drom.dromtesttask.module.act_splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BaseActivity;

public class SplashActivity
        extends BaseActivity
        implements SplashContract.View
{
    public static final String TAG = SplashActivity.class.getSimpleName();
    @InjectPresenter( type = PresenterType.LOCAL ) SplashPresenter presenter;

    @Override
    protected void onCreate( @Nullable final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
    }

    @Override
    public void navigateToLogInScreen(){
        uiRouter.openLogInView();
    }
}
