package com.gigigo.example.domain.interactor;

import android.support.annotation.NonNull;

import com.gigigo.example.BuildConfig;
import com.gigigo.example.data.entities.ChannelItem;
import com.gigigo.example.domain.service.IYoutubeService;
import com.gigigo.example.retrofit.ServiceFactory;
import com.kripton.mvp.domain.callback.OnViewCallback;
import com.kripton.mvp.domain.interactor.KInteractor;

/**
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class YoutubeInteractor
        extends KInteractor<ChannelItem> {
    @Override
    public void execute(@NonNull OnViewCallback<ChannelItem> callback) {
        IYoutubeService service = ServiceFactory.createService(IYoutubeService.class);

        String part = "snippet,id",
                channelId = BuildConfig.CHANNEL_ID,
                order = "date",
                maxResults = "40",
                key = BuildConfig.API_KEY;

        String text = tryGetParamValueAs(String.class, 0);
        int valor = tryGetParamValueAs(Integer.class, 1);

        ServiceFactory.createCall(service.getChannelVideoList(part, channelId, order, maxResults, key),
                callback,
                false,
                getOperationType());
    }

    @Override
    public void refreshData() {

    }
}
