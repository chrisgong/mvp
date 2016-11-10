package com.shuzijiayuan.myapplication.data.remote.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.bean.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.data.bean.profile.ProfileListResult;
import com.shuzijiayuan.myapplication.data.remote.ApiUtils;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileDataSource;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by gc on 2016/11/10.
 */

public class ProfileRemoteDataSource implements ProfileDataSource {

    private static ProfileRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private ProfileRemoteDataSource() {

    }

    public static ProfileRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProfileRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getProfiles(@NonNull final GetProfileCallback callback) {
        String token = AppContext.getSharedPreferences().getString("token", "");
        ApiUtils.getKintonInstance().profileList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProfileListResult>() {
                    @Override
                    public void call(ProfileListResult profileListResult) {
                        Log.e("cim", "profileListResult:" + profileListResult.toString());
                        if (profileListResult != null) {
                            callback.onGetProfile(profileListResult.getData().getList());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("cim", "profileListResult error:" + throwable.getMessage().toString());
                    }
                });
    }

    @Override
    public void deleteAllProfiles() {

    }

    @Override
    public void saveProfiles(@NonNull ArrayList<ProfileInfo> info) {

    }
}
