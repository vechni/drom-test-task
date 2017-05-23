package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("name") String name;
    @SerializedName("space") int space;
    @SerializedName("collaborators") int collaborators;
    @SerializedName("private_repos") int privateRepos;

    public Plan() {

    }

    // region - get/set -

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(int collaborators) {
        this.collaborators = collaborators;
    }

    public int getPrivateRepos() {
        return privateRepos;
    }

    public void setPrivateRepos(int privateRepos) {
        this.privateRepos = privateRepos;
    }

    // endregion

}
