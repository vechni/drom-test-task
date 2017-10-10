package com.drom.dromtesttask.module.act_log_in;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity
        extends BaseActivity
        implements LogInContract.View
{
    public static final String TAG = LogInActivity.class.getSimpleName();

    @BindView( R.id.act_log_in_et_login ) protected EditText etLogin;
    @BindView( R.id.act_log_in_et_password ) protected EditText etPassword;
    @BindView( R.id.act_log_in_ti_login ) protected TextInputLayout tiLogin;
    @BindView( R.id.act_log_in_ti_password ) protected TextInputLayout tiPassword;
    @InjectPresenter( type = PresenterType.LOCAL ) LogInPresenter presenter;

    @Override
    protected void onCreate( @Nullable final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_log_in);

        ButterKnife.bind(this);
    }

    @OnClick( R.id.act_log_in_txt_skip )
    public void onClickBtnSkip( @NonNull final View view ){
        presenter.onClickBtnSkip();
    }

    @OnClick( R.id.act_log_in_btn_enter )
    public void onClickBtnLogin( @NonNull final View view ){
        final String login = etLogin.getText().toString();
        final String password = etPassword.getText().toString();
        presenter.onClickBtnLogin(login, password);
    }

    @Override
    public void showWaitDialog(){
        final String message = this.getString(R.string.txt_wait);
        openWaitDialog(message, null);
    }

    @Override
    public void hideWaitDialog(){
        closeWaitDialog();
    }

    @Override
    public void showMessage( @StringRes final int resId ){
        showToastShort(resId);
    }

    @Override
    public void showMessage( @NonNull final String message ){
        showToastShort(message);
    }

    @Override
    public void showErrorRegistration(){
        final String warningLogin = getString(R.string.warning_error_login);
        final String warningPassword = getString(R.string.warning_error_password);

        tiLogin.setError(warningLogin);
        tiPassword.setError(warningPassword);
    }

    @Override
    public void navigateToMainScreen( boolean isAuthorized ){
        uiRouter.openMainView(isAuthorized);
    }
}
