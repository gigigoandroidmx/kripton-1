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

package com.kripton.mvp.presentation.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase para la manipulación animaciones de elementos de mensajes en la vista
 *
 * @author Juan Godínez Vera - 3/13/2017.
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComponentAnimator {

    private final Context mContext;

    private TextView mErrorView;
    private View mProgressView;

    public ComponentAnimator(Context context) {
        mContext = context;
    }

    public void setErrorView(@NonNull TextView errorView) {
        mErrorView = errorView;
    }

    public void setProgressView(@NonNull View progressView) {
        mProgressView = progressView;
    }

    public void hideProgressView() {
        mProgressView.setVisibility(View.GONE);
    }

    public void showProgressView() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    public void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
    }

    public void showErrorView(Throwable throwable) {
        if(throwable != null) {
            showErrorView(throwable.getMessage());
        }
    }

    public void showErrorView(String error) {
        if(error != null && !error.isEmpty()) {
            mErrorView.setText(error);

            //ObjectAnimator show = ObjectAnimator.ofFloat(mErrorView, View.ALPHA, 1f);
            ObjectAnimator show = ObjectAnimator.ofFloat(mErrorView, View.TRANSLATION_Y, -100f, 0f);
            show.setInterpolator(new BounceInterpolator());
            show.setDuration(1000);
            show.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mErrorView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mErrorView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            show.start();
        }
    }

    public void showToastMessage(String message) {
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);

        if(toast != null) {
            toast.show();
        }
    }
}
