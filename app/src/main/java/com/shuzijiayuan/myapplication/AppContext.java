package com.shuzijiayuan.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gc on 2016/11/8.
 */

public class AppContext extends Application {

    private static AppContext INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if(INSTANCE == null){
            INSTANCE = this;
        }

        RealmConfiguration config = new RealmConfiguration.Builder(AppContext.getInstance().getApplicationContext())
                .name("cim.realm")
                .schemaVersion(1)
                .build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public static AppContext getInstance(){
        return INSTANCE;
    }

    public static SharedPreferences getSharedPreferences() {
        SharedPreferences preferences = getInstance().getSharedPreferences("token", Context.MODE_PRIVATE);
        return preferences;
    }
}
