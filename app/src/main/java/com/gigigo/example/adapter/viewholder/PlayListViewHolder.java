package com.gigigo.example.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.gigigo.example.R;
import com.gigigo.example.model.Item;
import com.kripton.mvp.view.ui.adapter.ItemListener;
import com.kripton.mvp.view.ui.adapter.ViewHolderItem;

/**
 * Created by eidien on 14/10/16.
 */

public class PlayListViewHolder extends ViewHolderItem<Item> implements View.OnClickListener {

    private TextView mTextView;
    private ItemListener<Item> mItemListener;

    public PlayListViewHolder(View itemView, ItemListener<Item> mItemListener) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text_title);
        itemView.setOnClickListener(this);
        this.mItemListener = mItemListener;
    }

    @Override
    public void bindItem(Item item) {
        super.bindItem(item);
        mTextView.setText(item.snippet.title);
    }

    @Override
    public void onClick(View v) {
        Item item = getItem();
        mItemListener.onItemClick(item);
    }
}
