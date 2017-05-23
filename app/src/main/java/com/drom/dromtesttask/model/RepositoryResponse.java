package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryResponse {

    @SerializedName("total_count") int totalCount;
    @SerializedName("incomplete_results") boolean incompleteResults;
    @SerializedName("items") List<RepositoryItem> items;

    public RepositoryResponse() {

    }

    // region - get/set -

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<RepositoryItem> getItems() {
        return items;
    }

    public void setItems(List<RepositoryItem> items) {
        this.items = items;
    }


    // endregion
}
