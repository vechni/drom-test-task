package com.drom.dromtesttask.data.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.drom.dromtesttask.common.utils.NetworkUtils;
import com.drom.dromtesttask.model.RepositoryItemDTO;
import com.drom.dromtesttask.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import retrofit2.Response;

public class RestClientImpl implements RestClient {

    private RestApi restApi;
    private ConnectivityManager connMgr;

    public RestClientImpl(Context context, RestApi restApi) {
        this.restApi = restApi;
        connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    // region - Interface -

    @Override
    public Observable<Boolean> isNetworkConnectionAsync() {
        return Observable.just(isNetworkConnection());
    }

    @Override
    public boolean isNetworkConnection() {
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Observable<UserDTO> requestAuth( String login, String password) {

        String value = generateHeaderAuth(login, password);

        return restApi.requestAuth(value)
                .map(response -> {
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<RepositoryItemDTO>> requestSearchRepositories( String param, int page) {
        return restApi.requestSearchRepositories(param, page)
                .map(response -> {
                    checkResponse(response);

                    List<RepositoryItemDTO> listRepository = response.body().getItems();
                    if (listRepository == null) {
                        return new ArrayList<RepositoryItemDTO>();
                    }

                    return listRepository;
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    // endregion


    // region - Methods -

    private void checkResponse(Response response) throws RuntimeException {
        if (!response.isSuccessful()) {
            throw new RuntimeException(String.valueOf(response.code()));
        }
    }

    private String generateHeaderAuth(String login, String password) {
        String encode = Base64.encodeToString((login + ":" + password)
                .getBytes(), Base64.DEFAULT).replace("\n", "");

        return "Basic " + encode;
    }

    // endregion
}
