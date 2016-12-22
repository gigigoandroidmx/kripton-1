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

package com.kripton.mvp.core.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kripton.R;
import com.kripton.mvp.core.presenter.IKPresenter;
import com.kripton.mvp.core.view.IKViewContract;

/**
 * Interfaz base para el fragment
 *
 * @param <T> Modelo de datos que utilizará la vista
 * @param <P> Presenter para la interacción de la vista
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KFragment<T, P extends IKPresenter>
        extends Fragment implements IKViewContract.IViewExtended<T> {
    protected final String TAG = getClass().getSimpleName();

    protected P presenter;

    protected abstract P createPresenter();

    protected void showProgressBar(boolean active) {
        if(getView() == null) return;

        ProgressBar loader = (ProgressBar)getView().findViewById(R.id.progress_bar);

        if(loader != null) {
            loader.setVisibility(active ? View.VISIBLE : View.GONE);
        }
    }

    protected void showErrorView(Throwable exception) {
        if(exception != null) {
            showErrorView(exception.getMessage());
        }
    }

    protected void showErrorView(String message) {
        if(getView() == null) return;

        showProgressBar(false);
        TextView errorView = (TextView)getView().findViewById(R.id.error_view);

        if(errorView != null) {
            errorView.setText(message);
        }
    }

    protected void showToastMessage(String message) {
        if(getView() == null) return;

        showProgressBar(false);
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);

        if(toast != null) {
            toast.show();
        }
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

        if(presenter == null) {
            presenter = createPresenter();
        }

        presenter.attachView(this);
    }

    // -------------------------------------------------------
    // ---------------------- Destroyed ----------------------
    // -------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();

        if(presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //endregion
}
