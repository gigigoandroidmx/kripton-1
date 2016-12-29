package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class Thumbnails
        implements Serializable {

    @SerializedName("default")
    public Thumbnail mDefault;

    @SerializedName("medium")
    public Thumbnail mMedium;

    @SerializedName("high")
    public Thumbnail mHigh;
}