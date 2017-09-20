package com.drom.dromtesttask.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.drom.dromtesttask.common.utils.PreferencesUtils;
import com.drom.dromtesttask.model.UserDTO;
import com.google.gson.Gson;


public class PreferencesImpl implements Preferences.ImpPref {

    private final SharedPreferences pref;
    private Gson gson;

    public PreferencesImpl(Context context) {
        pref = context.getSharedPreferences(PreferencesUtils.PREFERENCES, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(UserDTO user) {
        SharedPreferences.Editor editor = pref.edit();
        String jsonObj = gson.toJson(user);
        editor.putString(PreferencesUtils.PREF_USER, jsonObj);
        editor.apply();
    }

    public UserDTO getUser() {
        String json = pref.getString(PreferencesUtils.PREF_USER, "");
        return gson.fromJson(json, UserDTO.class);
    }
}
