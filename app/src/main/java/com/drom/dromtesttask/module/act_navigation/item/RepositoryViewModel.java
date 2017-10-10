package com.drom.dromtesttask.module.act_navigation.item;

import android.support.annotation.NonNull;

public class RepositoryViewModel
{
    private String name;
    private String description;
    private String avatarUrl;

    public RepositoryViewModel( @NonNull final String name, @NonNull final String description, @NonNull final String avatarUrl ){
        this.name = name;
        this.description = description;
        this.avatarUrl = avatarUrl;
    }

    @NonNull
    public String getName(){
        return name;
    }

    @NonNull
    public String getDescription(){
        return description;
    }

    @NonNull
    public String getAvatarUrl(){
        return avatarUrl;
    }
}
