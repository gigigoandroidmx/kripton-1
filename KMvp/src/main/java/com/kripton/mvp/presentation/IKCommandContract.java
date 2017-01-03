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

package com.kripton.mvp.presentation;

import android.support.annotation.NonNull;

/**
 * Define los contratos base de los comandos para los adapters y presenters.
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IKCommandContract {

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
}
