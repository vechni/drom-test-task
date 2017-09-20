package com.drom.dromtesttask.data.rest;

import com.drom.dromtesttask.model.RepositoryItemDTO;
import com.drom.dromtesttask.model.UserDTO;

import java.util.List;

import io.reactivex.Observable;

public interface RestClient {

    Observable<Boolean> isNetworkConnectionAsync();

    boolean isNetworkConnection();

    Observable<UserDTO> requestAuth( String login, String password);

    Observable<List<RepositoryItemDTO>> requestSearchRepositories( String param, int page);
}
