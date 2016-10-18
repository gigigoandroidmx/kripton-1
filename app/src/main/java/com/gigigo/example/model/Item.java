package com.gigigo.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 12/10/2016.
 */
public class Item {
    @SerializedName("id")
    public String id;
    @SerializedName("snippet")
    public Snippet snippet;
}
