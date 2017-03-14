package com.gigigo.example.presentation.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.gigigo.example.R;
import com.gigigo.example.data.entities.Item;
import com.kripton.mvp.presentation.command.IKActionCommand;
import com.kripton.mvp.presentation.adapter.KAdapter;
import com.kripton.mvp.presentation.adapter.KViewHolder;

/**
 * Define la clase para ...
 *
 * @author Juan Godínez Vera - 12/29/2016.
 */
public class ChannelAdapter
        extends KAdapter<Item> {

    private IKActionCommand<Item> mActionCommand;

    public ChannelAdapter(IKActionCommand<Item> actionCommand) {
        mActionCommand = actionCommand;
    }

    @Override
    public KViewHolder<Item> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent, R.layout.item_channel);
        ChannelViewHolder viewHolder = new ChannelViewHolder(view);
        viewHolder.setOnExecuteCommand(mActionCommand);
        return viewHolder;
    }
}
