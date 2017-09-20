package com.drom.dromtesttask.module.act_log_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BaseActivity;
import com.drom.dromtesttask.module.act_navigation.NavigationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity
        extends BaseActivity
        implements LogInContract.View
{
    public static final String TAG = LogInActivity.class.getSimpleName();

    @BindView( R.id.act_log_in_et_login ) EditText etLogin;
    @BindView( R.id.act_log_in_et_password ) EditText etPassword;
    @BindView( R.id.act_log_in_ti_login ) TextInputLayout tiLogin;
    @BindView( R.id.act_log_in_ti_password ) TextInputLayout tiPassword;
    @InjectPresenter( type = PresenterType.LOCAL ) LogInPresenter presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_log_in);

        ButterKnife.bind(this);
    }

    @OnClick( R.id.act_log_in_txt_skip )
    public void onClickBtnSkip( View view ){
        presenter.skipLogin();
    }

    @OnClick( R.id.act_log_in_btn_enter )
    public void onClickBtnEnter( View view ){
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        presenter.checkLogin(login, password);
    }

    @Override
    public void startWaitDialog(){
        String message = this.getString(R.string.txt_wait);
        openWaitDialog(message, null);
    }

    @Override
    public void finishWaitDialog(){
        closeWaitDialog();
    }

    @Override
    public void showMessage( String message ){
        showToastShort(message);
    }

    @Override
    public void showErrorRegistration(){
        String warningErrorLogin = getString(R.string.warning_error_login);
        String warningErrorPassword = getString(R.string.warning_error_password);

        tiLogin.setError(warningErrorLogin);
        tiPassword.setError(warningErrorPassword);
    }

    @Override
    public void navigateToMainScreen(){
        showMainScreen();
    }

    private void showMainScreen(){
        Intent mainIntent = new Intent(LogInActivity.this, NavigationActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
