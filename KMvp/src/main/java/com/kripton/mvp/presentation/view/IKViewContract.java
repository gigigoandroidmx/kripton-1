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

package com.kripton.mvp.presentation.view;

/**
 * Define los contratos base de interacción entre la vista y el presenter.
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IKViewContract {

    /**
     * Interfaz base para el despliegue de información en la vista
     *
     * <ul>
     * <li>{@link #showData(Object)}: Permite mostrar la información obtenida a partir de la ejecución de la tarea.</li>
     * <li>{@link #showError(Throwable)}: Permite mostrar un error derivado de la ejecución de la tarea.</li>
     * <li>{@link #setProgressIndicator(boolean)}: Permite notificar a la vista que existe una tarea en ejecución mediante un indicador de progreso.</li>
     * </ul>
     *
     * @param <T> Representa el modelo de datos que utilizará la vista
     *
     * @author Juan Godínez Vera - 12/22/2016
     * @version 1.0.0
     * @since 1.0.0
     */
    interface IViewSimple<T> {
        void showData(T data);
        void showError(Throwable exception);
        void setProgressIndicator(boolean active);
    }

    /**
     * Interfaz extendida para el despliegue de información en la vista
     *
     * <ul>
     * <li>{@link #showData(Object)}: Permite mostrar la información obtenida a partir de la ejecución de la tarea.</li>
     * <li>{@link #showError(Throwable)}: Permite mostrar un error derivado de la ejecución de la tarea.</li>
     * <li>{@link #setProgressIndicator(boolean)}: Permite notificar a la vista que existe una tarea en ejecución mediante un indicador de progreso.</li>
     * <li>{@link #showDataEmpty()}: Permite notificar a la vista cuando el resultado de la tarea no tiene contenido.</li>
     * <li>{@link #showDataNotAvailable(String)}: Permite notificar a la vista cuando el resultado de la consulta no se encuentra disponible.</li>
     * </ul>
     *
     * @param <T> Representa el modelo de datos que utilizará la vista
     *
     * @author Juan Godínez Vera - 12/22/2016
     * @version 1.0.0
     * @since 1.0.0
     */
    interface IViewExtended<T>
            extends IViewSimple<T> {
        void showDataEmpty();
        void showDataNotAvailable(String message);
    }
}
