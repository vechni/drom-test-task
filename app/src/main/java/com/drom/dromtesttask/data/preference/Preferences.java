package com.drom.dromtesttask.data.preference;

import com.drom.dromtesttask.model.User;

import io.reactivex.Observable;

public interface Preferences {

    interface ImpPref {

        void saveUser(User user);

        User getUser();
    }

    interface RxPref {

        Observable<Integer> saveUser(User user);

        Observable<User> getUser();
    }
}
