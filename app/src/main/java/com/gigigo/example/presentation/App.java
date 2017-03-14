package com.gigigo.example.presentation;

import android.app.Application;

import com.gigigo.example.BuildConfig;
import com.gigigo.example.R;
import com.gigigo.example.retrofit.ServiceGenerator;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Define la clase principal de la aplicación
 *
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initRetrofitServiceGenerator();
    }

    private void initRetrofitServiceGenerator() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final Connectivity connectivity = new Connectivity(this);
        Interceptor connectivityInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (connectivity != null &&
                        connectivity.isConnected()) {
                    Request request = chain.request()
                            .newBuilder()
                            .build();

                    return chain.proceed(request);
                } else {
                    throw new IOException(getString(R.string.error_message_connection_off));
                }
            }
        };

        ServiceGenerator.init(this)
                .setBaseUrl(BuildConfig.APP_HOST)
                .setLoggingInterceptor(loggingInterceptor)
                .setConnectivityInterceptor(connectivityInterceptor);
    }
}
