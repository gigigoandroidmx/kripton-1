/* Copyright 2016 gigigo México
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kripton.mvp.presentation.presenter;


import android.support.annotation.NonNull;

import com.kripton.mvp.domain.EntryState;
import com.kripton.mvp.domain.IKCallbackContract;
import com.kripton.mvp.domain.interactor.IKInteractor;
import com.kripton.mvp.presentation.view.IKViewContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para el presenter
 *
 * @param <T> Modelo de datos que utilizará el presenter
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public class KPresenter<T>
        implements IKPresenter {

    protected final String TAG = getClass().getSimpleName();

    private WeakReference<IKViewContract.IViewExtended> mViewReference;
    private IKInteractor<T> mInteractor;
    private List<Object> mParams;
    private int mOperation;

    public KPresenter(@NonNull IKInteractor<T> interactor) {
        mParams = new ArrayList<>();
        mInteractor = interactor;
    }

    @Override
    public void attachView(IKViewContract.IViewExtended view) {
        this.mViewReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        detachView();
    }

    public void setOperation(int operation) {
        mOperation = operation;
    }

    public void addParam(Object param) {
        mParams.add(param);
    }

    @Override
    public void loadData(boolean forceUpdate) {
        if(!isViewAttached()) return;

        getView().setProgressIndicator(true);

        if(forceUpdate) {
            mInteractor.refreshData();
        }

        mInteractor.setParams(mParams);
        mInteractor.setOperationType(mOperation);
        mInteractor.getData(new IKCallbackContract.IViewExtendedCallback<T>() {
            @Override
            public void onSuccess(T data) {
                if (!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showData(data);
            }

            @Override
            public void onDataEmpty() {
                if (!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showDataEmpty();
            }

            @Override
            public void onDataNotAvailable(EntryState entryState) {
                getView().setProgressIndicator(false);
                getView().showDataNotAvailable(entryState.getEntryMessage());
            }

            @Override
            public void onError(Throwable exception) {
                if (!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showError(exception);
            }
        });
    }

    private boolean isViewAttached(){return mViewReference != null && mViewReference.get() != null;}

    private IKViewContract.IViewExtended getView() {
        return mViewReference == null ? null : mViewReference.get();
    }
}
