package com.batman.yara.views;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {});
    }
}
