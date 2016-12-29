package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class Item
        implements Serializable {

    @SerializedName("kind")
    public String mKind;

    @SerializedName("etag")
    public String mEtag;

    @SerializedName("id")
    public Id mId;

    @SerializedName("snippet")
    public Snippet mSnippet;
}
