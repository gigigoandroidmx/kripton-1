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

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Permite crear la instancia de retrofit con la cnfiguración establecida {@link ServiceSettings}.
 *
 * @author Juan Godínez Vera - 12/28/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceGenerator {

    private static ServiceSettings sServiceSettings;

    public static ServiceSettings init(@NonNull Context context) {
        sServiceSettings = new ServiceSettings();
        return sServiceSettings.setContext(context);
    }

    public static ServiceSettings getSettings() {
        return sServiceSettings;
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        if(getSettings().getLoggingInterceptor() != null) {
            builder.interceptors().add(getSettings().getLoggingInterceptor());
        }

        if(getSettings().getConnectivityInterceptor() != null) {
            builder.interceptors().add(getSettings().getConnectivityInterceptor());
        }

        return builder.build();
    }

    private static Retrofit.Builder getBuilder() {
        // Define the Gson date formatter
        Gson gson = new GsonBuilder()
                .setDateFormat(getSettings().getDateFormat())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getSettings().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (getSettings().getAdapterFactory() != null) {
            builder.addCallAdapterFactory(getSettings().getAdapterFactory());
        }

        return builder;
    }

    public static Retrofit getRetrofitInstance() {
        Retrofit retrofit = getBuilder()
                .client(getClient())
                .build();

        return retrofit;
    }

    public static <T> T createService(Class<T> classType) {
        Retrofit retrofit = getRetrofitInstance();
        return retrofit.create(classType);
    }

}
