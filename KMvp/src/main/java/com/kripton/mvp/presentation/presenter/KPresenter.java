/* Copyright (c) 2017 Gigigo Android Development Team México
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

import com.kripton.mvp.domain.interactor.IKInteractor;
import com.kripton.mvp.presentation.view.IKView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base base para el presenter.
 * Funcionalidad para el manejo de referencia de la vista
 *
 * @param <T> Representa el tipo de dato que manejará el interactor
 * @param <V> Representa la vista para el despliegue de información
 *
 * @author Juan Godínez Vera - 2/21/2017.
 * @author Daniel Moises Ruiz Pérez - 2/21/2017.
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KPresenter<T, V extends IKView>
        implements IKPresenter<V> {

    protected final String TAG = getClass().getSimpleName();
    protected IKInteractor<T> mInteractor;

    private WeakReference<V> mViewReference;
    private List<Object> mParams;
    private int mOperation;

    public KPresenter(@NonNull IKInteractor<T> interactor) {
        mParams = new ArrayList<>();
        mInteractor = interactor;
    }

    public int getOperationType() {
        return mOperation;
    }

    public void setOperation(int operation) {
        mOperation = operation;
    }

    public List<Object> getParams() {
        return mParams;
    }

    public void addParam(Object param) {
        mParams.add(param);
    }

    //region IKPresenter members

    @Override
    public void attachView(V view) {
        this.mViewReference = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    //endregion

    public boolean isViewAttached(){return mViewReference != null && mViewReference.get() != null;}

    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }
}
