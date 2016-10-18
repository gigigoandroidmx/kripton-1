package com.kripton.mvp.interactor;

/**
 * Created by jgodinez on 6/20/2016.
 */
public interface ILoaderCallback<T> {
    void onSuccess(T data);
    void onError(Throwable exception);
}