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

package com.kripton.mvp.domain.callback;

/**
 * Define los contratos base de callback entre el presenter y el interactor.
 *
 * @param <T> Representa el modelo de datos que retornará el interactor
 *
 * @author Juan Godínez Vera - 12/22/2016
 * @version 1.1.2
 * @since 1.0.0
 */
public interface IKViewCallback<T> {
    void onSuccess(T data, Object... params);
    void onError(Throwable exception);
}
