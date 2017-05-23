package com.drom.dromtesttask.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.drom.dromtesttask.common.interfaces.StatusSavePreferences;
import com.drom.dromtesttask.common.utils.PreferencesUtils;
import com.drom.dromtesttask.model.User;
import com.google.gson.Gson;

import io.reactivex.Observable;

public class PreferencesRxImpl implements Preferences.RxPref {

    private final SharedPreferences pref;
    private Gson gson;

    public PreferencesRxImpl(Context context) {
        pref = context.getSharedPreferences(PreferencesUtils.PREFERENCES, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public Observable<Integer> saveUser(User user) {
        return Observable.just(saveUserFunc(user));
    }

    private Integer saveUserFunc(User user) {
        SharedPreferences.Editor editor = pref.edit();
        String jsonObj = gson.toJson(user);
        editor.putString(PreferencesUtils.PREF_USER, jsonObj);
        editor.apply();
        return StatusSavePreferences.OK;
    }

    public Observable<User> getUser() {
        return Observable.just(getUserFunc());
    }

    private User getUserFunc() {
        String json = pref.getString(PreferencesUtils.PREF_USER, "");
        return gson.fromJson(json, User.class);
    }
}
