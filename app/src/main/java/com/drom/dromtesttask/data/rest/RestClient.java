package com.drom.dromtesttask.data.rest;

import android.support.annotation.NonNull;

import com.drom.dromtesttask.data.model.RepositoryItemDTO;
import com.drom.dromtesttask.data.model.UserDTO;

import java.util.List;

import io.reactivex.Observable;

public interface RestClient
{
    Observable<Boolean> isNetworkConnectionAsync();

    boolean isNetworkConnection();

    @NonNull
    Observable<UserDTO> requestAuth( @NonNull String login, @NonNull String password );

    @NonNull
    Observable<List<RepositoryItemDTO>> requestRepositories( @NonNull String param, int page );
}
