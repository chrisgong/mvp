package com.shuzijiayuan.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

/**
 * Created by gc on 2016/11/8.
 */

public class AppContext extends Application {

    private static AppContext INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        if(INSTANCE == null){
            INSTANCE = this;
        }
    }

    public static AppContext getInstance(){
        return INSTANCE;
    }

    public static SharedPreferences getSharedPreferences() {
        SharedPreferences preferences = getInstance().getSharedPreferences("token", Context.MODE_PRIVATE);
        return preferences;
    }
}
