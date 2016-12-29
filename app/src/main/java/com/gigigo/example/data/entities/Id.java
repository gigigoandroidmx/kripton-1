package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class Id
        implements Serializable {

    @SerializedName("kind")
    public String mKind;

    @SerializedName("videoId")
    public String mVideoId;
}
