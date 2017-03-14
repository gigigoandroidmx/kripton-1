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

package com.gigigo.example.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kripton.mvp.domain.EntryState;
import com.kripton.mvp.domain.callback.OnViewCallback;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Permite crear los métodos de retrofit para la interacción vía HTTP.
 *
 * @author Juan Godínez Vera - 12/28/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceFactory {
    private static final String TAG = ServiceFactory.class.getName();

    public static <T> T createService(Class<T> classType) {
        return ServiceGenerator.createService(classType);
    }

    public static <T> void createCall(Call<T> call,
                                      OnViewCallback callback,
                                      boolean cancelable,
                                      Object... params) {
        if(call == null) {
            throw new NullPointerException("Call is required");
        }

        if(callback ==  null) {
            throw new NullPointerException("Callback is required");
        }

        try {
            if(cancelable) {
                call.cancel();
            }

            Callback<T> enqueueCallback = getEnqueueCallback(callback, params);
            call.enqueue(enqueueCallback);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            callback.onError(e);
        }
    }

    @NonNull
    private static <T> Callback<T> getEnqueueCallback(final OnViewCallback callback, final Object... params) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response != null) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body(), params);
                    } else {
                        EntryState entryState = handleErrorResponse(response.code());
                        callback.onDataNotAvailable(entryState);
                    }
                } else {
                    callback.onDataEmpty();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if(!call.isCanceled()) {
                    callback.onError(t);
                }
            }
        };
    }

    @NonNull
    private static EntryState handleErrorResponse(int statusCode) {
        String message;

        // Error handling for error responses
        // ******************************
        // 4xx: Client Error responses
        // ******************************
        // Bad Request
        // Unauthorized
        // Forbidden
        // Not Found
        // Method Not Allowed
        // Request Timeout
        // Conflict
        // ******************************
        // 5×× Server Error responses
        // ******************************
        // Internal Server Error
        // Not Implemented
        // Bad Gateway
        // Service Unavailable
        switch (statusCode) {
            // Client error responses
            case 400:
                message = "Bad Request - The server could not understand the request due to invalid syntax.";
                break;
            case 401:
                message = "Unauthorized - Authentication is needed to get requested response.";
                break;
            case 403:
                message = "Forbidden - Client does not have access rights to the content so server is rejecting to give proper response.";
                break;
            case 404:
                message = "Not Found - Server can not find requested resource.";
                break;
            case 405:
                message = "Method Not Allowed - The request method is known by the server but has been disabled and cannot be used.";
                break;
            case 408:
                message = "Request Timeout - This response is sent on an idle connection by some servers, even without any previous request by the client.";
                break;
            case 409:
                message = "Conflict - This response would be sent when a request conflict with current state of server.";
                break;

            // Server error responses
            case 500:
                message = "Internal Server Error - The server has encountered a situation it doesn't know how to handle.";
                break;
            case 501:
                message = "Not Implemented - The request method is not supported by the server and cannot be handled.";
                break;
            case 502:
                message = "Bad Gateway - The server, while working as a gateway to get a response needed to handle the request, got an invalid response.";
                break;
            case 503:
                message = "Service Unavailable - The server is not ready to handle the request.";
                break;
            default:
                message = "The specific HTTP request has been interrupted";
                break;
        }

        return new EntryState(message, statusCode);
    }

    private static  <T> T parseError(Class<T> type, Response<?> response) {
        if (response == null || response.errorBody() == null) {
            return null;
        }

        Converter<ResponseBody, T> converter =
                ServiceGenerator.getRetrofitInstance()
                        .responseBodyConverter(type, new Annotation[0]);

        T error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return null;
        }

        return error;
    }
}
