package com.gigigo.example.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Daniel on 12/10/2016.
 */
public class PlayListItem {

    @SerializedName("items")
    public ArrayList<Item> items;
}
