package com.kripton.mvp.view;

import android.support.annotation.UiThread;

/**
 * Created by jgodinez on 6/20/2016.
 */
public interface IView<T> {

    @UiThread
    void setProgressIndicator(boolean active);

    @UiThread
    void  showData (T data);

    @UiThread
    void showError(Throwable exception);

    @UiThread
    void showFriendlyMessage(String response);
}