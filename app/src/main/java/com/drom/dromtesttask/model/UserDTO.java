package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

public class UserDTO
{
    @SerializedName( "login" ) private String login = null;
    @SerializedName( "id" ) private int id;
    @SerializedName( "avatar_url" ) private String avatarUrl;
    @SerializedName( "gravatar_id" ) private String gravatarId;
    @SerializedName( "url" ) private String url;
    @SerializedName( "html_url" ) private String htmlurl;
    @SerializedName( "followers_url" ) private String followersUrl;
    @SerializedName( "gists_url" ) private String gistsUrl;
    @SerializedName( "starred_url" ) private String starredUrl;
    @SerializedName( "subscriptions_url" ) private String subscriptionsUrl;
    @SerializedName( "organizations_url" ) private String organizationsUrl;
    @SerializedName( "repos_url" ) private String reposUrl;
    @SerializedName( "events_url" ) private String eventsUrl;
    @SerializedName( "received_events_url" ) private String receivedEventsUrl;
    @SerializedName( "type" ) private String type;
    @SerializedName( "site_admin" ) private boolean siteAdmin;
    @SerializedName( "name" ) private String name;
    @SerializedName( "company" ) private String company;
    @SerializedName( "blog" ) private String blog;
    @SerializedName( "location" ) private String location;
    @SerializedName( "email" ) private String email;
    @SerializedName( "hireable" ) private String hireable;
    @SerializedName( "bio" ) private String bio;
    @SerializedName( "public_repos" ) private int publicRepos;
    @SerializedName( "public_gists" ) private int publicGists;
    @SerializedName( "followers" ) private int followers;
    @SerializedName( "following" ) private int following;
    @SerializedName( "created_at" ) private String createdAt;
    @SerializedName( "updated_at" ) private String updatedAt;
    @SerializedName( "private_gists" ) private int privateGists;
    @SerializedName( "total_private_repos" ) private int totalPrivateRepos;
    @SerializedName( "owned_private_repos" ) private int ownedPrivateRepos;
    @SerializedName( "disk_usage" ) private int diskUsage;
    @SerializedName( "collaborators" ) private int collaborators;
    @SerializedName( "two_factor_authentication" ) private boolean twoFactorAuthentication;
    @SerializedName( "plan" ) private PlanDTO plan;

    public UserDTO(){

    }

    public String getLogin(){
        return login;
    }

    public int getId(){
        return id;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public String getGravatarId(){
        return gravatarId;
    }

    public String getUrl(){
        return url;
    }

    public String getHtmlurl(){
        return htmlurl;
    }

    public String getFollowersUrl(){
        return followersUrl;
    }

    public String getGistsUrl(){
        return gistsUrl;
    }

    public String getStarredUrl(){
        return starredUrl;
    }

    public String getSubscriptionsUrl(){
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl(){
        return organizationsUrl;
    }

    public String getReposUrl(){
        return reposUrl;
    }

    public String getEventsUrl(){
        return eventsUrl;
    }

    public String getReceivedEventsUrl(){
        return receivedEventsUrl;
    }

    public String getType(){
        return type;
    }

    public boolean isSiteAdmin(){
        return siteAdmin;
    }

    public String getName(){
        return name;
    }

    public String getCompany(){
        return company;
    }

    public String getBlog(){
        return blog;
    }

    public String getLocation(){
        return location;
    }

    public String getEmail(){
        return email;
    }

    public String getHireable(){
        return hireable;
    }

    public String getBio(){
        return bio;
    }

    public int getPublicRepos(){
        return publicRepos;
    }

    public int getPublicGists(){
        return publicGists;
    }

    public int getFollowers(){
        return followers;
    }

    public int getFollowing(){
        return following;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public int getPrivateGists(){
        return privateGists;
    }

    public int getTotalPrivateRepos(){
        return totalPrivateRepos;
    }

    public int getOwnedPrivateRepos(){
        return ownedPrivateRepos;
    }

    public int getDiskUsage(){
        return diskUsage;
    }

    public int getCollaborators(){
        return collaborators;
    }

    public boolean isTwoFactorAuthentication(){
        return twoFactorAuthentication;
    }

    public PlanDTO getPlan(){
        return plan;
    }
}


