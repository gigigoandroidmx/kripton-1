package com.kripton.mvp.view.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jgodinez on 7/11/2016.
 */
public abstract class ViewHolderItem<T>
        extends RecyclerView.ViewHolder {

    protected Context mContext;
    private T mItem;

    public ViewHolderItem(View itemView) {
        super(itemView);
        this.mContext = itemView.getContext();
    }

    public void bindItem(T item) {
        if(item == null)
            throw new NullPointerException("Item is required");

        this.mItem = item;
    }

    public T getItem() {
        return this.mItem;
    }

    public Context getContext() {
        return this.mContext;
    }
}
