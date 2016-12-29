package com.gigigo.example.presentation;

import android.app.Application;

import com.gigigo.example.BuildConfig;
import com.kripton.extensions.retrofit.ServiceGenerator;

/**
 * Define la clase principal de la aplicación
 *
 * @author Juan Godínez Vera - 12/28/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ServiceGenerator.init(this)
                .setBaseUrl(BuildConfig.APP_HOST);
    }
}
