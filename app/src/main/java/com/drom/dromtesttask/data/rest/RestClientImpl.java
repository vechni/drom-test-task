package com.drom.dromtesttask.data.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.drom.dromtesttask.common.utils.EncryptUtils;
import com.drom.dromtesttask.common.utils.NetworkUtils;
import com.drom.dromtesttask.data.model.RepositoryItemDTO;
import com.drom.dromtesttask.data.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import retrofit2.Response;

public class RestClientImpl
        implements RestClient
{
    @NonNull private RestApi restApi;
    @NonNull private ConnectivityManager connMgr;

    public RestClientImpl( @NonNull final Context context, @NonNull final RestApi restApi ){
        this.restApi = restApi;
        this.connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public Observable<Boolean> isNetworkConnectionAsync(){
        return Observable.just(isNetworkConnection());
    }

    @Override
    public boolean isNetworkConnection(){
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    @NonNull
    public Observable<UserDTO> requestAuth( @NonNull final String login, @NonNull final String password ){
        final String value = EncryptUtils.encodeLoginData(login, password);

        return restApi.requestAuth(value)
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    @NonNull
    public Observable<List<RepositoryItemDTO>> requestRepositories( @NonNull final String param, final int page ){
        return restApi.requestRepositories(param, page)
                .map(response->{
                    checkResponse(response);

                    final List<RepositoryItemDTO> listRepository = response.body().getItems();
                    if( listRepository == null ){
                        return new ArrayList<RepositoryItemDTO>();
                    }

                    return listRepository;
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    private void checkResponse( @NonNull final Response response ) throws RuntimeException{
        if( !response.isSuccessful() ){
            throw new RuntimeException(String.valueOf(response.code()));
        }
    }
}
