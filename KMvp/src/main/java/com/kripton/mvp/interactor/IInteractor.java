package com.kripton.mvp.interactor;

import android.support.annotation.NonNull;

/**
 * Created by jgodinez on 6/20/2016.
 */
public interface IInteractor {
    String MESSAGE_FORMAT = "Status: %1$s\nMessage: %2$s";

    void getData(@NonNull ILoaderCallback callback);
    void refreshData();


    void setOperationType(int operationType);
    void setParams(Object... params);
}
