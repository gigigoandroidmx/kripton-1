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

import okhttp3.Interceptor;
import retrofit2.CallAdapter;

/**
 * Define las configuraciones básicas para retrofit.
 *
 * @author Juan Godínez Vera - 12/28/2016
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceSettings {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Context mContext;
    private String mBaseUrl;
    private String mApplicationTag;
    private String mDateFormat = DATE_FORMAT;
    private CallAdapter.Factory mFactory;
    private Interceptor mLoggingInterceptor;
    private Interceptor mConnectivityInterceptor;

    public Context getContext() {
        return mContext;
    }

    public ServiceSettings setContext(Context context) {
        mContext = context;
        return this;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public ServiceSettings setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
        return this;
    }

    public String getApplicationTag() {
        return mApplicationTag;
    }

    public ServiceSettings setApplicationTag(String applicationTag) {
        mApplicationTag = applicationTag;
        return this;
    }

    public ServiceSettings setAdapterFactory(CallAdapter.Factory factory) {
        mFactory = factory;
        return this;
    }

    public CallAdapter.Factory getAdapterFactory() {
        return mFactory;
    }

    public String getDateFormat() {
        return mDateFormat;
    }

    public ServiceSettings setDateFormat(String dateFormat) {
        this.mDateFormat = dateFormat;
        return this;
    }

    public Interceptor getLoggingInterceptor() {
        return mLoggingInterceptor;
    }

    public ServiceSettings setLoggingInterceptor(Interceptor interceptor) {
        mLoggingInterceptor = interceptor;
        return this;
    }

    public Interceptor getConnectivityInterceptor() {
        return mConnectivityInterceptor;
    }

    public ServiceSettings setConnectivityInterceptor(Interceptor interceptor) {
        mConnectivityInterceptor = interceptor;
        return this;
    }
}
