package com.drom.dromtesttask.module.act_splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BaseActivity;
import com.drom.dromtesttask.module.act_log_in.LogInActivity;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    public static final String TAG = "tag_splash_act";
    private final int CONST_DELAY_SPLASH = 1500;

    @InjectPresenter(type = PresenterType.LOCAL) SplashPresenter presenter;

    // region - Lifecycle -

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_splash);

        ButterKnife.bind(this);

        hideStatusBar();

        navigateToLogIn();
    }

    // endregion


    // region - Event handlers -

    // endregion


    // region - Contract -

    @Override
    public void navigateToLogIn() {
        showLogInScreen();
    }

    // endregion


    // region - Methods -

    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void showLogInScreen() {
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, LogInActivity.class);
            startActivity(mainIntent);
            finish();
        }, CONST_DELAY_SPLASH);
    }

    // endregion
}
