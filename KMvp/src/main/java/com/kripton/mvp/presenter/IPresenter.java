package com.kripton.mvp.presenter;

/**
 * Created by jgodinez on 6/20/2016.
 */
public interface IPresenter {
    void loadData(boolean forceUpdate);
    void setParams(Object... params);
}
