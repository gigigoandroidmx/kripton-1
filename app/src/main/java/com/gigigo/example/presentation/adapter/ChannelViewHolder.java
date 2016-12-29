package com.gigigo.example.presentation.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gigigo.example.R;
import com.gigigo.example.data.entities.Item;
import com.gigigo.example.data.entities.Thumbnail;
import com.kripton.mvp.presentation.IKCommandContract;
import com.kripton.mvp.presentation.adapter.KViewHolder;

/**
 * Define la clase para ...
 *
 * @author Juan Godínez Vera - 12/29/2016.
 */
public class ChannelViewHolder
        extends KViewHolder<Item> {

    private ImageView mThumbnail;
    private View mPlayIcon;
    private TextView mTitle;
    private TextView mDescription;

    public ChannelViewHolder(View itemView, IKCommandContract.IActionCommand<Item> actionCommand) {
        super(itemView, actionCommand);

        mThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        mPlayIcon = itemView.findViewById(R.id.img_play);
        mTitle = (TextView) itemView.findViewById(R.id.txt_title);
        mDescription = (TextView) itemView.findViewById(R.id.txt_description);
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
