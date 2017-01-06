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

package com.kripton.mvp.presentation.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kripton.mvp.R;
import com.kripton.mvp.presentation.presenter.IKPresenter;
import com.kripton.mvp.presentation.view.IKViewContract;

/**
 * Clase base para el activity
 *
 * @param <T> Modelo de datos que utilizará la vista
 * @param <P> Presenter para la interacción de la vista
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KActivity<T, P extends IKPresenter>
        extends AppCompatActivity
        implements IKViewContract.IViewExtended<T> {

    protected final String TAG = getClass().getSimpleName();

    protected P mPresenter;

    protected abstract P createPresenter();

    protected void showProgressBar(boolean active) {
        ProgressBar loader = (ProgressBar)findViewById(R.id.progress_view);

        if(loader != null) {
            loader.setVisibility(active ? View.VISIBLE : View.GONE);
        }
    }
    protected void hideErrorView() {
        TextView errorView = (TextView)findViewById(R.id.error_view);

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
        showProgressBar(false);
        TextView errorView = (TextView)findViewById(R.id.error_view);

        if(errorView != null) {
            errorView.setText(message);
        }
    }

    protected void showToastMessage(String message) {
        showProgressBar(false);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        if(toast != null) {
            toast.show();
        }
    }

    //region Handling the Activity Lifecycle

    // -------------------------------------------------------
    // ----------------------- Created -----------------------
    // -------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        if(mPresenter == null) {
            mPresenter = createPresenter();
        }

        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    //endregion
}
