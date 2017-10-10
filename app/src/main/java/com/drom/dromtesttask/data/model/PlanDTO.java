package com.drom.dromtesttask.data.model;

import com.google.gson.annotations.SerializedName;

public class PlanDTO
{
    @SerializedName( "name" ) private String name;
    @SerializedName( "space" ) private int space;
    @SerializedName( "collaborators" ) private int collaborators;
    @SerializedName( "private_repos" ) private int privateRepos;

    public PlanDTO(){

    }

    public String getName(){
        return name;
    }

    public int getSpace(){
        return space;
    }

    public int getCollaborators(){
        return collaborators;
    }

    public int getPrivateRepos(){
        return privateRepos;
    }
}
