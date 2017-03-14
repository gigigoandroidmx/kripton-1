package com.gigigo.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class ChannelItem
        implements Serializable {

    @SerializedName("kind")
    public String mKind;

    @SerializedName("etag")
    public String mEtag;

    @SerializedName("nextPageToken")
    public String mNextPageToken;

    @SerializedName("regionCode")
    public String mRegionCode;

    @SerializedName("pageInfo")
    public PageInfo mPageInfo;

    @SerializedName("items")
    public List<Item> mItems = null;

    public boolean hasItems() {
        return mItems != null && mItems.size() > 0;
    }
}
