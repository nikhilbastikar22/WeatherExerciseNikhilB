package com.demo.weatherexercisenikhilbastikar;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    /* Properties */
    private static AppController mInstance;

    public static AppController getInstance()
    {
        return mInstance;
    }

    /* Life-cycle callbacks */
    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
    }
}

