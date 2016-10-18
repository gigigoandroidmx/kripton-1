package com.gigigo.example.app;

import android.app.Application;

import com.gigigo.example.BuildConfig;

import kripton.extensions.retrofit.RetrofitFactoryService;

/**
 * Created by Daniel on 12/10/2016.
 */
public class ApplicationKMvp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitFactoryService.initialize(BuildConfig.HOST_APP);
    }
}
