package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class Thumbnail
        implements Serializable {

    @SerializedName("url")
    public String mUrl;

    @SerializedName("width")
    public Integer mWidth;

    @SerializedName("height")
    public Integer mHeight;
}
