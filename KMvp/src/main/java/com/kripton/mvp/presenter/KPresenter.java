package com.kripton.mvp.presenter;


import android.support.annotation.UiThread;

import com.kripton.mvp.interactor.ILoaderCallback;
import com.kripton.mvp.interactor.KInteractor;
import com.kripton.mvp.view.IView;

import java.lang.ref.WeakReference;


/**
 * Created by Daniel on 8/10/16.
 */
public class KPresenter<T> implements IPresenter {

    private KInteractor mKinteractor;
    private WeakReference<IView> viewRef;
    protected Object[] mParams;


    public KPresenter(KInteractor interactor){
        mKinteractor = interactor;
    }

    @Override
    public void loadData(boolean forceUpdate) {
        getView().setProgressIndicator(true);

        if(forceUpdate) {
            mKinteractor.refreshData();
        }

        mKinteractor.setParams(mParams);
        mKinteractor.getData(new ILoaderCallback<T>() {
            @Override
            public void onSuccess(T data) {
                getView().setProgressIndicator(false);
                getView().showData(data);
            }

            @Override
            public void onError(Throwable exception) {
                getView().setProgressIndicator(false);
                getView().showError(exception);
            }
        });
    }



    public void setParams(Object... params) {
        this.mParams = params;
    }


    @UiThread
    public  void attachView(IView view) {
        viewRef = new WeakReference<IView>(view);
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    public boolean isViewAttached(){return viewRef != null && viewRef.get() != null;}

    @UiThread
    public IView getView() {
        return viewRef == null ? null : viewRef.get();
    }



}


