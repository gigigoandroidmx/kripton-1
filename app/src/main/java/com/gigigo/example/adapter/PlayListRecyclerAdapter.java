package com.gigigo.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigigo.example.R;
import com.gigigo.example.adapter.viewholder.PlayListViewHolder;
import com.gigigo.example.model.Item;
import com.kripton.mvp.view.ui.adapter.ItemListener;
import com.kripton.mvp.view.ui.adapter.ViewHolderItem;

import java.util.ArrayList;

/**
 * Created by eidien on 14/10/16.
 */

public class PlayListRecyclerAdapter extends RecyclerView.Adapter<ViewHolderItem<Item>> {

    private Context mContext;
    private ArrayList<Item> mItemArrayList;
    private ItemListener<Item> mItemListener;

    public PlayListRecyclerAdapter(Context mContext, ArrayList<Item> mItemArrayList, ItemListener<Item> mItemListener) {
        this.mContext = mContext;
        this.mItemArrayList = mItemArrayList;
        this.mItemListener = mItemListener;
    }

    @Override
    public ViewHolderItem<Item> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_playlist_recycler, parent, false);
        ViewHolderItem<Item> viewHolderItem = new PlayListViewHolder(view, mItemListener);
        return viewHolderItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderItem<Item> holder, int position) {
        holder.bindItem(mItemArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemArrayList != null ? mItemArrayList.size() : 0;
    }
}
