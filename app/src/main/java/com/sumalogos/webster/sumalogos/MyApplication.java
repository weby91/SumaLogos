package com.sumalogos.webster.sumalogos;

import android.app.Application;
import android.content.Context;

/**
 * Created by webster on 20/01/18.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
