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

package com.kripton.mvp.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kripton.R;
import com.kripton.mvp.presentation.presenter.IKPresenter;
import com.kripton.mvp.presentation.view.IKViewContract;

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

    protected P mPpresenter;

    protected abstract P createPresenter();

    protected void showProgressBar(boolean active) {
        if(getView() == null) return;

        ProgressBar loader = (ProgressBar)getView().findViewById(R.id.progress_view);

        if(loader != null) {
            if(active) hideErrorView();
            loader.setVisibility(active ? View.VISIBLE : View.GONE);
        }
    }

    protected  void hideErrorView() {
        if(getView() == null) return;

        TextView errorView = (TextView)getView().findViewById(R.id.error_view);

        if(errorView != null) {
            errorView.setText("");
            errorView.setVisibility(View.GONE);
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
            errorView.setVisibility(View.VISIBLE);
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

        if(mPpresenter == null) {
            mPpresenter = createPresenter();
        }

        mPpresenter.attachView(this);
    }

    // -------------------------------------------------------
    // ---------------------- Destroyed ----------------------
    // -------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPpresenter != null) {
            mPpresenter.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //endregion
}
