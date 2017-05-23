package com.drom.dromtesttask.di.module.data;

import android.content.Context;

import com.drom.dromtesttask.data.rest.RestApi;
import com.drom.dromtesttask.data.rest.RestClient;
import com.drom.dromtesttask.data.rest.RestClientImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ModuleRetrofit.class)
public class ModuleRest {

    @Provides
    @Singleton
    RestClient providesRestClient(Context context, RestApi restApi) {
        return new RestClientImpl(context, restApi);
    }
}
