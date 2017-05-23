package com.drom.dromtesttask.data;

import com.drom.dromtesttask.data.preference.Preferences;
import com.drom.dromtesttask.data.rest.RestClient;

import javax.inject.Inject;

public class DataLayer {

    @Inject public RestClient restApi;
    @Inject public Preferences.RxPref prefRx;
    @Inject public Preferences.ImpPref pref;

    @Inject
    public DataLayer() {

    }
}
