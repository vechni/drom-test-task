package com.drom.dromtesttask.data.rest;

import com.drom.dromtesttask.model.RepositoryItem;
import com.drom.dromtesttask.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface RestClient {

    Observable<Boolean> isNetworkConnectionAsync();

    boolean isNetworkConnection();

    Observable<User> requestAuth(String login, String password);

    Observable<List<RepositoryItem>> requestSearchRepositories(String param, int page);
}
