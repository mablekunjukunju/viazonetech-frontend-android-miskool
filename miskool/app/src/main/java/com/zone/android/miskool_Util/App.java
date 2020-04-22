package com.zone.android.miskool_Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Inspiron on 07-03-2018.
 */

public class App extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
    public static Context getAppContext() {
        return appContext;
    }
}