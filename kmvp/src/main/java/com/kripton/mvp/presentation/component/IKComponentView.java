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

/**
 * Interfaz para la manipulación de elementos de progreso, mensajes y mensajes de error en la vista
 *
 * @author Juan Godínez Vera - 3/13/2017.
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IKComponentView {
    void showProgressView(boolean active);

    void hideErrorView();
    void showErrorView(Throwable throwable);
    void showErrorView(String message);

    void showToastMessage(String message);
}
