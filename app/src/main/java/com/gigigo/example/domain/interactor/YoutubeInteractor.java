package com.gigigo.example.domain.interactor;

import android.support.annotation.NonNull;

import com.gigigo.example.BuildConfig;
import com.gigigo.example.data.entities.ChannelItem;
import com.gigigo.example.domain.service.IYoutubeService;
import com.kripton.extensions.retrofit.ServiceFactory;
import com.kripton.mvp.domain.IKCallbackContract;
import com.kripton.mvp.domain.interactor.KInteractor;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class YoutubeInteractor
        extends KInteractor<ChannelItem> {

    @Override
    public void getData(@NonNull IKCallbackContract.IViewExtendedCallback<ChannelItem> callback) {
        IYoutubeService service = ServiceFactory.createService(IYoutubeService.class);

        String part = "snippet,id",
                channelId = BuildConfig.CHANNEL_ID,
                order = "date",
                maxResults = "40",
                key = BuildConfig.API_KEY;

        ServiceFactory.createCall(service.getChannelVideoList(part, channelId, order, maxResults, key),
                callback,
                false);
    }

    @Override
    public void refreshData() {

    }
}
