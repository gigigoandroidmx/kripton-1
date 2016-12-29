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

package com.kripton.mvp.domain;


/**
 * Define los contratos base de callback entre el presenter y el interactor.
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IKCallbackContract {
    /**
     * Interfaz base para el callback entre el presenter e interactor
     *
     * <ul>
     * <li>{@link #onSuccess(Object)}: Recibe la información obtenida a partir de la ejecución de la tarea.</li>
     * <li>{@link #onError(Throwable)}: Recibe el error derivado de la ejecución de la tarea.</li>
     * </ul>
     *
     * @param <T> Representa el modelo de datos que retornará el interactor
     *
     * @author Juan Godínez Vera - 12/22/2016
     * @version 1.0.0
     * @since 1.0.0
     */
    interface IViewSimpleCallback<T> {
        void onSuccess(T data);
        void onError(Throwable exception);
    }

    /**
     * Interfaz extendida para el callback entre el presenter e interactor
     *
     * <ul>
     * <li>{@link #onSuccess(Object)}: Recibe la información obtenida a partir de la ejecución de la tarea.</li>
     * <li>{@link #onError(Throwable)}: Recibe el error derivado de la ejecución de la tarea.</li>
     * <li>{@link #onDataEmpty()}: Recibe la notificación cuando el resultado de la tarea no tiene contenido.</li>
     * <li>{@link #onDataNotAvailable(EntryState)}: Recibe la notificación cuando el resultado de la tarea no se encuentra disponible.</li>
     * </ul>
     *
     * @param <T> Representa el modelo de datos que retornará el interactor
     *
     * @author Juan Godínez Vera - 12/22/2016
     * @version 1.0.0
     * @since 1.0.0
     */
    interface IViewExtendedCallback<T>
            extends IViewSimpleCallback<T> {
        void onDataEmpty();
        void onDataNotAvailable(EntryState entryState);
    }
}
