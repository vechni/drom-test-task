package com.drom.dromtesttask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryResponseDTO
{
    @SerializedName( "total_count" ) private int totalCount;
    @SerializedName( "incomplete_results" ) private boolean incompleteResults;
    @SerializedName( "items" ) private List<RepositoryItemDTO> items;

    public RepositoryResponseDTO(){

    }

    public int getTotalCount(){
        return totalCount;
    }

    public boolean isIncompleteResults(){
        return incompleteResults;
    }

    public List<RepositoryItemDTO> getItems(){
        return items;
    }
}
