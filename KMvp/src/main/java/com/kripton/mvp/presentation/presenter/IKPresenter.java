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

package com.kripton.mvp.presentation.presenter;

import android.support.annotation.UiThread;

import com.kripton.mvp.presentation.view.IKView;

/**
 * Interfaz base para el presenter
 *
 * @param <V> Representa la vista para el despliegue de información
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @author Daniel Moises Ruiz Pérez - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IKPresenter<V extends IKView> {
    @UiThread
    void attachView(V view);

    @UiThread
    void detachView();

    @UiThread
    void loadData(boolean forceUpdate);
}
