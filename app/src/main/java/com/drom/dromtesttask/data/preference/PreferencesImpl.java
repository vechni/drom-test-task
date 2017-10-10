package com.drom.dromtesttask.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.common.utils.AppConst;
import com.drom.dromtesttask.common.utils.PreferencesUtils;
import com.drom.dromtesttask.data.model.UserDTO;
import com.google.gson.Gson;

public class PreferencesImpl
        implements Preferences.ImpPref
{
    @NonNull private final SharedPreferences pref;
    @NonNull private final Gson gson;

    public PreferencesImpl( @NonNull final Context context ){
        this.pref = context.getSharedPreferences(PreferencesUtils.PREFERENCES, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public void saveUser( @NonNull final UserDTO user ){
        final SharedPreferences.Editor editor = pref.edit();
        final String jsonObj = gson.toJson(user);
        editor.putString(PreferencesUtils.PREF_USER, jsonObj);
        editor.apply();
    }

    @NonNull
    public UserDTO getUser(){
        final String json = pref.getString(PreferencesUtils.PREF_USER, AppConst.EMPTY_STRING);
        return gson.fromJson(json, UserDTO.class);
    }

    @Override
    public void removeUser(){
        final SharedPreferences.Editor editor = pref.edit();
        editor.remove(PreferencesUtils.PREF_USER);
    }
}
