/* Copyright (c) 2016 Gigigo Android Development Team México
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

package com.kripton.mvp.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kripton.mvp.R;
import com.kripton.mvp.presentation.component.ComponentAnimator;
import com.kripton.mvp.presentation.component.IKComponentView;

/**
 * Clase base para el fragment. Se implementan 4 métodos por defecto
 *
 * <ul>
 *     <li>{@link #getLayoutResourceId()} Recurso para inflar el fragmento.</li>
 *     <li>{@link #initializeComponent()} Permite asegurar que los componentes serán inicializados una vez que la actividad ha sido creada.</li>
 *     <li>{@link #initializePresenter()} Permite inicializar el presenter una vez que la actividad ha sido creada.</li>
 *     <li>{@link #dispose()} Permite liberar los recursos que se utilizan en la implementación de la vista.</li>
 * </ul>
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @author Daniel Moises Ruiz Pérez - 12/22/2016
 * @version 1.0.2
 * @since 1.0.0
 */
public abstract class KFragment
        extends Fragment
        implements IKComponentView {

    protected final String TAG = getClass().getSimpleName();

    protected abstract int getLayoutResourceId();
    protected abstract void initializeComponent();
    protected abstract void initializePresenter();
    protected abstract void dispose();

    private ComponentAnimator componentAnimator;

    private void initializeComponentAnimator() {
        if(getView() == null) return;

        componentAnimator = new ComponentAnimator(getContext());
        TextView errorView = (TextView)getView().findViewById(R.id.error_view);
        ProgressBar progressView = (ProgressBar)getView().findViewById(R.id.progress_view);

        componentAnimator.setErrorView(errorView);
        componentAnimator.setProgressView(progressView);
    }

    //region Handling the Fragment Lifecycle

    // -------------------------------------------------------
    // ----------------------- Created -----------------------
    // -------------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializePresenter();
        initializeComponent();
        initializeComponentAnimator();
    }

    // -------------------------------------------------------
    // ---------------------- Destroyed ----------------------
    // -------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();

        dispose();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //endregion

    //region IKComponentView members

    @Override
    public void showProgressView(boolean active) {
        if(active) {
            componentAnimator.showProgressView();
        } else {
            componentAnimator.hideProgressView();
        }
    }

    @Override
    public void hideErrorView() {
        componentAnimator.hideErrorView();
    }

    @Override
    public void showErrorView(Throwable throwable) {
        componentAnimator.showErrorView(throwable);
    }

    @Override
    public void showErrorView(String message) {
        componentAnimator.showErrorView(message);
    }

    @Override
    public void showToastMessage(String message) {
        componentAnimator.showToastMessage(message);
    }

    //endregion

}
