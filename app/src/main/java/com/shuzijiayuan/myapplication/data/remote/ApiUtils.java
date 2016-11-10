package com.shuzijiayuan.myapplication.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chrisgong on 16/1/9.
 */
public final class ApiUtils {

    private static ApiSet mApiSet = null;

    private static ApiSet mTestApiSet = null;

    private static ApiSet mDownloadApiSet = null;

    private ApiUtils() {
    }

    public static ApiSet getKintonInstance() {
        if (mApiSet == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new StethoInterceptor())
                    .build();
            mApiSet = new Retrofit.Builder()
                    .baseUrl(ApiSet.KINTON_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build().create(ApiSet.class);
        }
        return mApiSet;
    }
}
