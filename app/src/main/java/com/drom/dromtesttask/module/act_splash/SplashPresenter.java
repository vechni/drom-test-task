package com.drom.dromtesttask.module.act_splash;

import android.os.CountDownTimer;

import com.arellomobile.mvp.InjectViewState;
import com.drom.dromtesttask.common.mvp.BasePresenter;

@InjectViewState
public class SplashPresenter
        extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter
{
    public static final String TAG = SplashPresenter.class.getSimpleName();
    private final int CONST_DELAY_SPLASH = 1500;

    SplashPresenter(){

    }

    @Override
    protected void onFirstViewAttach(){
        super.onFirstViewAttach();
        timer.start();
    }

    private final CountDownTimer timer = new CountDownTimer(CONST_DELAY_SPLASH, CONST_DELAY_SPLASH)
    {
        public void onTick( long millisUntilFinished ){
        }

        public void onFinish(){
            getViewState().navigateToLogInScreen();
        }
    };

}