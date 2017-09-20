package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

public class OwnerDTO
{
    @SerializedName( "login" ) private String login;
    @SerializedName( "id" ) private int id;
    @SerializedName( "avatar_url" ) private String avatarUrl;
    @SerializedName( "gravatar_id" ) private String gravatarId;
    @SerializedName( "url" ) private String url;
    @SerializedName( "html_url" ) private String htmlUrl;
    @SerializedName( "followers_url" ) private String followersUrl;
    @SerializedName( "following_url" ) private String followingUrl;
    @SerializedName( "gists_url" ) private String gistsUrl;
    @SerializedName( "starred_url" ) private String starredUrl;
    @SerializedName( "subscriptions_url" ) private String subscriptionsUrl;
    @SerializedName( "organizations_url" ) private String organizationsUrl;
    @SerializedName( "repos_url" ) private String reposUrl;
    @SerializedName( "events_url" ) private String eventsUrl;
    @SerializedName( "received_events_url" ) private String receivedEventsUrl;
    @SerializedName( "type" ) private String type;
    @SerializedName( "site_admin" ) private boolean siteAdmin;

    public OwnerDTO(){

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

    public String getHtmlUrl(){
        return htmlUrl;
    }

    public String getFollowersUrl(){
        return followersUrl;
    }

    public String getFollowingUrl(){
        return followingUrl;
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
}
