package com.gigigo.example.presentation.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gigigo.example.R;
import com.gigigo.example.data.entities.Item;
import com.gigigo.example.data.entities.Thumbnail;
import com.kripton.mvp.presentation.command.IKActionCommand;
import com.kripton.mvp.presentation.adapter.KViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Define la clase para ...
 *
 * @author Juan Godínez Vera - 12/29/2016.
 */
public class ChannelViewHolder
        extends KViewHolder<Item> {

    @BindView(R.id.img_thumbnail)
    ImageView mThumbnail;

    @BindView(R.id.img_play)
    View mPlayIcon;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.txt_description)
    TextView mDescription;

    public ChannelViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setOnExecuteCommand(final IKActionCommand<Item> actionCommand) {
        if(actionCommand != null) {
            mPlayIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionCommand.onExecute(getItem());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Item item) {
        super.onBindViewHolder(item);

        if(item.mSnippet != null) {
            if(item.mSnippet.mThumbnails != null) {
                Thumbnail thumbnail = item.mSnippet.mThumbnails.mMedium;

                if(thumbnail != null) {
                    Glide.with(getContext())
                            .load(thumbnail.mUrl)
                            .into(mThumbnail);
                }
            }

            mTitle.setText(item.mSnippet.mTitle);
            if(!item.mSnippet.mDescription.isEmpty()) {
                mDescription.setVisibility(View.VISIBLE);
                mDescription.setText(item.mSnippet.mDescription);
            } else {
                mDescription.setText("");
                mDescription.setVisibility(View.GONE);
            }
        }
    }
}
