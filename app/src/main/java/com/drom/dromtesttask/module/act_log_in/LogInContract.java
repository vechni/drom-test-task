package com.drom.dromtesttask.module.act_log_in;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public class LogInContract {

    public interface View extends MvpView {

        @StateStrategyType(AddToEndStrategy.class)
        void startWaitDialog();

        @StateStrategyType(AddToEndStrategy.class)
        void finishWaitDialog();

        @StateStrategyType(SkipStrategy.class)
        void showMessage(String message);

        @StateStrategyType(SingleStateStrategy.class)
        void showErrorRegistration();

        @StateStrategyType(SkipStrategy.class)
        void navigateToMainScreen();
    }


    public interface Presenter {

        void skipLogin();

        void checkLogin(String login, String password);
    }
}