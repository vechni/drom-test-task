package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

public class RepositoryItemDTO
{

    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("full_name") private String fullName;
    @SerializedName("owner") private OwnerDTO owner;
    @SerializedName("private") private boolean THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
    @SerializedName("html_url") private String htmlUrl;
    @SerializedName("description") private String description;
    @SerializedName("fork") private boolean fork;
    @SerializedName("url") private String url;
    @SerializedName("forks_url") private String forksUrl;
    @SerializedName("keys_url") private String keysUrl;
    @SerializedName("collaborators_url") private String collaboratorsUrl;
    @SerializedName("teams_url") private String teamsUrl;
    @SerializedName("hooks_url") private String hooksUrl;
    @SerializedName("issue_events_url") private String issueEventsUrl;
    @SerializedName("events_url") private String eventsUrl;
    @SerializedName("assignees_url") private String assigneesUrl;
    @SerializedName("branches_url") private String branchesUrl;
    @SerializedName("tags_url") private String tagsUrl;
    @SerializedName("blobs_url") private String blobsUrl;
    @SerializedName("git_tags_url") private String gitTagsUrl;
    @SerializedName("git_refs_url") private String gitRefsUrl;
    @SerializedName("trees_url") private String treesUrl;
    @SerializedName("statuses_url") private String statusesUrl;
    @SerializedName("languages_url") private String languagesUrl;
    @SerializedName("stargazers_url") private String stargazersUrl;
    @SerializedName("contributors_url") private String contributorsUrl;
    @SerializedName("subscribers_url") private String subscribersUrl;
    @SerializedName("subscription_url") private String subscriptionUrl;
    @SerializedName("commits_url") private String commitsUrl;
    @SerializedName("git_commits_url") private String gitCommitsUrl;
    @SerializedName("comments_url") private String commentsUrl;
    @SerializedName("issue_comment_url") private String issueCommentUrl;
    @SerializedName("contents_url") private String contentsUrl;
    @SerializedName("compare_url") private String compareUrl;
    @SerializedName("merges_url") private String mergesUrl;
    @SerializedName("archive_url") private String archiveUrl;
    @SerializedName("downloads_url") private String downloadsUrl;
    @SerializedName("issues_url") private String issuesUrl;
    @SerializedName("pulls_url") private String pullsUrl;
    @SerializedName("milestones_url") private String milestonesUrl;
    @SerializedName("notifications_url") private String notificationsUrl;
    @SerializedName("labels_url") private String labelsUrl;
    @SerializedName("releases_url") private String releasesUrl;
    @SerializedName("deployments_url") private String deploymentsUrl;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("pushed_at") private String pushedAt;
    @SerializedName("git_url") private String gitUrl;
    @SerializedName("ssh_url") private String sshUrl;
    @SerializedName("clone_url") private String cloneUrl;
    @SerializedName("svn_url") private String svnUrl;
    @SerializedName("homepage") private String homepage;
    @SerializedName("size") private int size;
    @SerializedName("stargazers_count") private int stargazersCount;
    @SerializedName("watchers_count") private int watchersCount;
    @SerializedName("language") private String language;
    @SerializedName("has_issues") private boolean hasIssues;
    @SerializedName("has_projects") private boolean hasProjects;
    @SerializedName("has_downloads") private boolean hasDownloads;
    @SerializedName("has_wiki") private boolean hasWiki;
    @SerializedName("has_pages") private boolean hasPages;
    @SerializedName("forks_count") private int forksCount;
    @SerializedName("mirror_url") private String mirrorUrl;
    @SerializedName("open_issues_count") private int openIssuesCount;
    @SerializedName("forks") private int forks;
    @SerializedName("open_issues") private int openIssues;
    @SerializedName("watchers") private int watchers;
    @SerializedName("default_branch") private String defaultBranch;
    @SerializedName("score") private double score;

    public RepositoryItemDTO() {

    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getFullName(){
        return fullName;
    }

    public OwnerDTO getOwner(){
        return owner;
    }

    public boolean isTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE(){
        return THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
    }

    public String getHtmlUrl(){
        return htmlUrl;
    }

    public String getDescription(){
        return description;
    }

    public boolean isFork(){
        return fork;
    }

    public String getUrl(){
        return url;
    }

    public String getForksUrl(){
        return forksUrl;
    }

    public String getKeysUrl(){
        return keysUrl;
    }

    public String getCollaboratorsUrl(){
        return collaboratorsUrl;
    }

    public String getTeamsUrl(){
        return teamsUrl;
    }

    public String getHooksUrl(){
        return hooksUrl;
    }

    public String getIssueEventsUrl(){
        return issueEventsUrl;
    }

    public String getEventsUrl(){
        return eventsUrl;
    }

    public String getAssigneesUrl(){
        return assigneesUrl;
    }

    public String getBranchesUrl(){
        return branchesUrl;
    }

    public String getTagsUrl(){
        return tagsUrl;
    }

    public String getBlobsUrl(){
        return blobsUrl;
    }

    public String getGitTagsUrl(){
        return gitTagsUrl;
    }

    public String getGitRefsUrl(){
        return gitRefsUrl;
    }

    public String getTreesUrl(){
        return treesUrl;
    }

    public String getStatusesUrl(){
        return statusesUrl;
    }

    public String getLanguagesUrl(){
        return languagesUrl;
    }

    public String getStargazersUrl(){
        return stargazersUrl;
    }

    public String getContributorsUrl(){
        return contributorsUrl;
    }

    public String getSubscribersUrl(){
        return subscribersUrl;
    }

    public String getSubscriptionUrl(){
        return subscriptionUrl;
    }

    public String getCommitsUrl(){
        return commitsUrl;
    }

    public String getGitCommitsUrl(){
        return gitCommitsUrl;
    }

    public String getCommentsUrl(){
        return commentsUrl;
    }

    public String getIssueCommentUrl(){
        return issueCommentUrl;
    }

    public String getContentsUrl(){
        return contentsUrl;
    }

    public String getCompareUrl(){
        return compareUrl;
    }

    public String getMergesUrl(){
        return mergesUrl;
    }

    public String getArchiveUrl(){
        return archiveUrl;
    }

    public String getDownloadsUrl(){
        return downloadsUrl;
    }

    public String getIssuesUrl(){
        return issuesUrl;
    }

    public String getPullsUrl(){
        return pullsUrl;
    }

    public String getMilestonesUrl(){
        return milestonesUrl;
    }

    public String getNotificationsUrl(){
        return notificationsUrl;
    }

    public String getLabelsUrl(){
        return labelsUrl;
    }

    public String getReleasesUrl(){
        return releasesUrl;
    }

    public String getDeploymentsUrl(){
        return deploymentsUrl;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getPushedAt(){
        return pushedAt;
    }

    public String getGitUrl(){
        return gitUrl;
    }

    public String getSshUrl(){
        return sshUrl;
    }

    public String getCloneUrl(){
        return cloneUrl;
    }

    public String getSvnUrl(){
        return svnUrl;
    }

    public String getHomepage(){
        return homepage;
    }

    public int getSize(){
        return size;
    }

    public int getStargazersCount(){
        return stargazersCount;
    }

    public int getWatchersCount(){
        return watchersCount;
    }

    public String getLanguage(){
        return language;
    }

    public boolean isHasIssues(){
        return hasIssues;
    }

    public boolean isHasProjects(){
        return hasProjects;
    }

    public boolean isHasDownloads(){
        return hasDownloads;
    }

    public boolean isHasWiki(){
        return hasWiki;
    }

    public boolean isHasPages(){
        return hasPages;
    }

    public int getForksCount(){
        return forksCount;
    }

    public String getMirrorUrl(){
        return mirrorUrl;
    }

    public int getOpenIssuesCount(){
        return openIssuesCount;
    }

    public int getForks(){
        return forks;
    }

    public int getOpenIssues(){
        return openIssues;
    }

    public int getWatchers(){
        return watchers;
    }

    public String getDefaultBranch(){
        return defaultBranch;
    }

    public double getScore(){
        return score;
    }
}
