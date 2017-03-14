package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class Snippet
        implements Serializable {

    @SerializedName("publishedAt")
    public String mPublishedAt;

    @SerializedName("channelId")
    public String mChannelId;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("thumbnails")
    public Thumbnails mThumbnails;
}