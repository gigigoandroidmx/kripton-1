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


package com.kripton.mvp.core.view;

import android.support.annotation.NonNull;

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
     * <li>{@link #showDataNotAvailable(int, String)}: Permite notificar a la vista cuando el resultado de la consulta no se encuentra disponible.</li>
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
        void showDataNotAvailable(int code, String message);
    }

    /**
     * Interfaz para la ejecución de un comando a través de una vista
     *
     * <ul>
     * <li>{@link #onExecute(Object)}: Permite el manejo de la ejecución de un comando a través de l acción del usuario.</li>
     * </ul>
     *
     * @param <T> Tipo de dato de retorno en la ejecución del comando.
     *
     * @author Juan Godínez Vera - 12/22/2016
     * @version 1.0.0
     * @since 1.0.0
     */
    interface IActionCommand<T> {
        void onExecute(@NonNull T item);
    }

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
     * <li>{@link #onDataNotAvailable(int, String)}: Recibe la notificación cuando el resultado de la tarea no se encuentra disponible.</li>
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
        void onDataNotAvailable(int code, String message);
    }
}
