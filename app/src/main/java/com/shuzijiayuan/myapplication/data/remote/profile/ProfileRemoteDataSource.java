package com.shuzijiayuan.myapplication.data.remote.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.data.model.profile.ProfileListResult;
import com.shuzijiayuan.myapplication.data.remote.ApiUtils;
import com.shuzijiayuan.myapplication.data.remote.NetworkErrorCodeException;
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
    public void getProfiles(@NonNull final IProfileListCallback callback) {
        String token = AppContext.getSharedPreferences().getString("token", "");
        ApiUtils.getKintonInstance().profileList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProfileListResult>() {
                    @Override
                    public void call(ProfileListResult profileListResult) {
                        if (profileListResult != null) {
                            callback.onSuccess(profileListResult.getData().getList());
                        } else {
                            callback.onFailure(new NetworkErrorCodeException(profileListResult.getCode()).getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onFailure(new NetworkErrorCodeException(-1).getMessage());
                    }
                });
    }

    @Override
    public void deleteAllProfiles() {

    }

    @Override
    public void saveProfiles(@NonNull ArrayList<ProfileInfo> info) {

    }

    @Override
    public void refreshProfileList() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }
}
