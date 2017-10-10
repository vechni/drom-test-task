package com.drom.dromtesttask.data;

import com.drom.dromtesttask.data.preference.Preferences;
import com.drom.dromtesttask.data.rest.RestClient;

import javax.inject.Inject;

public class DataLayer
{
    @Inject public RestClient restApi;
    @Inject public Preferences.RxPref pref;

    @Inject
    public DataLayer(){

    }
}
