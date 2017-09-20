package com.drom.dromtesttask.module.act_navigation;

public class RepositoryViewModel
{
    private String name;
    private String description;
    private String avatarUrl;

    public RepositoryViewModel( String name, String description, String avatarUrl ){
        this.name = name;
        this.description = description;
        this.avatarUrl = avatarUrl;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }
}
