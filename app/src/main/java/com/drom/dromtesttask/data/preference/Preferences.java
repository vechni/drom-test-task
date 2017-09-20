package com.drom.dromtesttask.data.preference;

import com.drom.dromtesttask.model.UserDTO;

import io.reactivex.Observable;

public interface Preferences {

    interface ImpPref {

        void saveUser(UserDTO user);

        UserDTO getUser();
    }

    interface RxPref {

        Observable<Integer> saveUser(UserDTO user);

        Observable<UserDTO> getUser();
    }
}
