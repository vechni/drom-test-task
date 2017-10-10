package com.drom.dromtesttask.data.preference;

import android.support.annotation.NonNull;

import com.drom.dromtesttask.data.model.UserDTO;

import io.reactivex.Observable;

public interface Preferences
{

    interface ImpPref
    {

        void saveUser( @NonNull UserDTO user );

        @NonNull
        UserDTO getUser();

        void removeUser();
    }


    interface RxPref
    {

        @NonNull
        Observable<Integer> saveUser( @NonNull UserDTO user );

        @NonNull
        Observable<UserDTO> getUser();

        @NonNull
        Observable<Integer> removeUser();
    }
}
