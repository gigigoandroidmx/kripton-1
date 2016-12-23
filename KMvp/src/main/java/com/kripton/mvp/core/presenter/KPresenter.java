/* MIT License
 *
 * Copyright (c) 2016 gigigo México
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.kripton.mvp.core.presenter;


import android.support.annotation.NonNull;

import com.kripton.mvp.core.interactor.IKInteractor;
import com.kripton.mvp.core.view.IKViewContract;

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
public class KPresenter<T extends IKInteractor<T>>
        implements IKPresenter {

    protected final String TAG = getClass().getSimpleName();

    private WeakReference<IKViewContract.IViewExtended> viewReference;
    private IKInteractor<T> interactor;
    private List<Object> params;
    private int operation;

    KPresenter(@NonNull IKInteractor<T> interactor) {
        this.params = new ArrayList<>();
        this.interactor = interactor;
    }

    @Override
    public void attachView(IKViewContract.IViewExtended view) {
        this.viewReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
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
        this.operation = operation;
    }

    public void addParam(Object param) {
        this.params.add(param);
    }

    @Override
    public void loadData(boolean forceUpdate) {
        if(!isViewAttached()) return;

        getView().setProgressIndicator(true);

        if(forceUpdate) {
            interactor.refreshData();
        }

        interactor.setParams(params);
        interactor.setOperationType(operation);
        interactor.getData(new IKViewContract.IViewExtendedCallback<T>() {
            @Override
            public void onSuccess(T data) {
                if(!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showData(data);
            }

            @Override
            public void onDataEmpty() {
                if(!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showDataEmpty();
            }

            @Override
            public void onDataNotAvailable(int code, String message) {
                if(!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showDataNotAvailable(code, message);
            }

            @Override
            public void onError(Throwable exception) {
                if(!isViewAttached()) return;

                getView().setProgressIndicator(false);
                getView().showError(exception);
            }
        });
    }

    private boolean isViewAttached(){return viewReference != null && viewReference.get() != null;}

    private IKViewContract.IViewExtended getView() {
        return viewReference == null ? null : viewReference.get();
    }
}
