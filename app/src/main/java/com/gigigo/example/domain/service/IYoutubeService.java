package com.gigigo.example.domain.service;

import com.gigigo.example.BuildConfig;
import com.gigigo.example.data.entities.ChannelItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Define la interfaz para los servicios de retrofit de youtube
 *
 * @author Juan Godínez Vera - 12/28/2016.
 */
public interface IYoutubeService {

    @GET(BuildConfig.PATH_CHANNEL)
    Call<ChannelItem> getChannelVideoList(@Query("part") String part,
                             @Query("channelId") String channelId,
                             @Query("order") String order,
                             @Query("maxResults") String maxResults,
                             @Query("key") String key);
}
