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

package com.kripton.mvp.domain.interactor;

import android.support.annotation.NonNull;

import com.kripton.mvp.domain.IKCallbackContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para el interactor
 *
 * @param <T> Modelo de datos que utilizará el interactor
 *
 * @author Juan Godínez Vera - 12/28/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KInteractor<T>
        implements IKInteractor<T> {

    protected static final String EMPTY_PARAMS = "Params not found. Params are required for HTTP request";

    protected int mOperationType;
    protected List<Object> mParams;

    public boolean hasParams() {
        return mParams != null && mParams.size() > 0;
    }

    @Override
    public void setOperationType(int operationType) {
        mOperationType = operationType;
    }

    @Override
    public void setParams(Object... params) {
        if(params != null && params.length > 0) {
            mParams = new ArrayList<>();
            for(Object item : params) {
                mParams.add(item);
            }
        }
    }

    public <T> T tryGetParamValueAs(Class<T> classType, int index) {
        return tryGetParamValueAs(classType, index, null);
    }

    public <T> T tryGetParamValueAs(Class<T> classType, int index, T defaultValue) {
        if(hasParams()) {
            try {
                if(index < mParams.size()) {
                    return as(classType, mParams.get(index), defaultValue);
                } else {
                    return defaultValue;
                }
            } catch (Exception exception) {
                return defaultValue;
            }
        }

        return null;
    }

    private <T> T as(Class<T> classType, Object object, T defaultValue) {
        if(object != null && classType.isInstance(object)) {
            return classType.cast(object);
        }

        return defaultValue;
    }

    @Override
    public void getData(@NonNull IKCallbackContract.IViewExtendedCallback<T> callback) { }

    @Override
    public void refreshData() { }
}
