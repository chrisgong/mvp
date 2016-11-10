package com.shuzijiayuan.myapplication.data.repository.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/10.
 */

public class LoginRepository implements LoginDataSource {

    private static LoginRepository INSTANCE = null;

    private final LoginDataSource mLoginRemoteDataSource;

    private final LoginDataSource mLoginLocalDataSource;

    boolean mCacheIsDirty = false;

    Map<String, UserInfo> mCaches;

    // Prevent direct instantiation.
    private LoginRepository(@NonNull LoginDataSource loginRemoteDataSource, @NonNull LoginDataSource loginLocalDataSource) {
        mLoginRemoteDataSource = checkNotNull(loginRemoteDataSource);
        mLoginLocalDataSource = checkNotNull(loginLocalDataSource);
    }

    public static LoginRepository getInstance(LoginDataSource loginRemoteDataSource, LoginDataSource loginLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository(loginRemoteDataSource, loginLocalDataSource);
        }
        return INSTANCE;
    }

    private void getUserInfoFromRemoteDataSource(long phone, String password, final GetUserInfoCallback callback) {
        mLoginRemoteDataSource.getUserInfo(phone, password, new GetUserInfoCallback() {
            @Override
            public void onGetUserInfo(UserInfo info) {
                refreshCache(info);
                refreshLocalDataSource(info);
                callback.onGetUserInfo(new ArrayList<>(mCaches.values()).get(0));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(UserInfo info) {
        if (mCaches == null) {
            mCaches = new LinkedHashMap<>();
        }
        mCaches.clear();
        mCaches.put(info.token, info);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(UserInfo info) {
        mLoginLocalDataSource.deleteAllUserInfo();
        mLoginLocalDataSource.saveUserInfo(info);
    }

    @Override
    public void getUserInfo(@NonNull final long phone, @NonNull final String password, @NonNull final GetUserInfoCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCaches != null && !mCacheIsDirty) {
            callback.onGetUserInfo(new ArrayList<>(mCaches.values()).get(0));
            return;
        }


        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getUserInfoFromRemoteDataSource(phone, password, callback);
        } else {
            mLoginLocalDataSource.getUserInfo(phone, password, new GetUserInfoCallback() {
                @Override
                public void onGetUserInfo(UserInfo info) {
                    refreshCache(info);
                    callback.onGetUserInfo(info);
                }

                @Override
                public void onDataNotAvailable() {
                    getUserInfoFromRemoteDataSource(phone, password, callback);
                }
            });
        }
    }

    @Override
    public void deleteAllUserInfo() {
        mLoginRemoteDataSource.deleteAllUserInfo();
        mLoginLocalDataSource.deleteAllUserInfo();

        if (mCaches == null) {
            mCaches = new LinkedHashMap<>();
        }
        mCaches.clear();
    }

    @Override
    public void saveUserInfo(@NonNull UserInfo task) {

    }

    @Override
    public String getToken() {
        return mLoginLocalDataSource.getToken();
    }
}
