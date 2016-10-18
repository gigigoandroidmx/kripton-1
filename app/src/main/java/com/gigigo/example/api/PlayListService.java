package com.gigigo.example.api;

import com.gigigo.example.model.PlayListItem;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Daniel on 12/10/2016.
 */
public interface PlayListService {

    @GET("playlists")
    Observable<Response<PlayListItem>> getAllPlayList(@Query("part")String part, @Query("channelId")String channel, @Query("key") String key);

}
