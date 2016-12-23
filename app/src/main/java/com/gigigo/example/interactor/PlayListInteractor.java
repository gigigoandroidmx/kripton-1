package com.gigigo.example.interactor;

import android.support.annotation.NonNull;

import com.gigigo.example.api.PlayListService;
import com.gigigo.example.model.PlayListItem;
import com.kripton.mvp.interactor.KInteractor;

import kripton.extensions.retrofit.RetrofitFactoryService;

/**
 * Created by Daniel on 12/10/2016.
 */
public class PlayListInteractor extends KInteractor {

    public final static int GET_ALL_PLAY_LIST = 0;
    @Override
    public void getData(@NonNull ILoaderCallback callback) {
        switch (mOperationtype){
            case GET_ALL_PLAY_LIST  : getAllPlayList(callback); break;
        }
        
    }

    private void getAllPlayList(final ILoaderCallback callback) {

        PlayListService service = RetrofitFactoryService.createRetrofitService(PlayListService.class);

        String user = "";
        String key  = "";

        if(mParams != null && mParams.length == 2) {
            user = String.valueOf(mParams[0]);
            key = String.valueOf(mParams[1]);
        }

        RetrofitFactoryService.getEntityResponseCallback(new ILoaderCallback<PlayListItem>() {
                                                             @Override
                                                             public void onSuccess(PlayListItem data) {

                                                                 callback.onSuccess(data);
                                                             }

                                                             @Override
                                                             public void onError(Throwable exception) {
                                                                 callback.onError(exception);
                                                             }
                                                         },
                null,
                service.getAllPlayList("snippet",user,key));
    }
}
