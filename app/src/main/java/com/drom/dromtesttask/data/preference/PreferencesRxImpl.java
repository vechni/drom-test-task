package com.drom.dromtesttask.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.common.utils.AppConst;
import com.drom.dromtesttask.common.utils.PreferencesUtils;
import com.drom.dromtesttask.data.model.UserDTO;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class PreferencesRxImpl
        implements Preferences.RxPref
{
    @NonNull private final SharedPreferences pref;
    @NonNull private final Gson gson;

    public PreferencesRxImpl( @NonNull final Context context ){
        this.pref = context.getSharedPreferences(PreferencesUtils.PREFERENCES, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    @NonNull
    public Observable<Integer> saveUser( @NonNull final UserDTO user ){
        return Observable.just(saveUserFunc(user)).subscribeOn(Schedulers.io());
    }

    @NonNull
    private Integer saveUserFunc( UserDTO user ){
        final SharedPreferences.Editor editor = pref.edit();
        final String jsonObj = gson.toJson(user);
        editor.putString(PreferencesUtils.PREF_USER, jsonObj);
        editor.apply();

        return StatusSavePreferences.OK;
    }

    @NonNull
    public Observable<UserDTO> getUser(){
        return Observable.just(getUserFunc()).subscribeOn(Schedulers.io());
    }

    private UserDTO getUserFunc(){
        final String json = pref.getString(PreferencesUtils.PREF_USER, AppConst.EMPTY_STRING);
        return gson.fromJson(json, UserDTO.class);
    }

    @NonNull
    public Observable<Integer> removeUser(){
        return Observable.just(removeUserFunc()).subscribeOn(Schedulers.io());
    }

    @NonNull
    private Integer removeUserFunc(){
        final SharedPreferences.Editor editor = pref.edit();
        editor.remove(PreferencesUtils.PREF_USER);

        return StatusSavePreferences.OK;
    }
}
