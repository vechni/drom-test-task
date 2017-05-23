package com.drom.dromtesttask.data.rest;

import com.drom.dromtesttask.common.utils.NetworkUtils;
import com.drom.dromtesttask.model.RepositoryResponse;
import com.drom.dromtesttask.model.User;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RestApi {

    @GET(NetworkUtils.URL_METHOD_AUTHORIZATION)
    Observable<Response<User>> requestAuth(@Header("Authorization") String value);

    @GET(NetworkUtils.URL_METHOD_SEARCH_REPOSITORIES)
    Observable<Response<RepositoryResponse>> requestSearchRepositories(@Query("q") String value,
                                                                       @Query("page") int page);
}






