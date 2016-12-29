package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class PageInfo
        implements Serializable {

    @SerializedName("totalResults")
    public Integer mTotalResults;

    @SerializedName("resultsPerPage")
    public Integer mResultsPerPage;
}