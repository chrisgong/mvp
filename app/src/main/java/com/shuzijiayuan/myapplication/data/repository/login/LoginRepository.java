package com.shuzijiayuan.myapplication.data.repository.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.login.UserInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/10.
 */

public class LoginRepository implements LoginDataSource {

    private static LoginRepository INSTANCE = null;

    private final LoginDataSource mLoginRemoteDataSource;

    // Prevent direct instantiation.
    private LoginRepository(@NonNull LoginDataSource loginRemoteDataSource) {
        mLoginRemoteDataSource = checkNotNull(loginRemoteDataSource);
    }

    public static LoginRepository getInstance(LoginDataSource loginRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository(loginRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getUserInfo(@NonNull final long phone, @NonNull final String password, @NonNull final ILoginCallback callback) {
        checkNotNull(callback);
        mLoginRemoteDataSource.getUserInfo(phone, password, new ILoginCallback() {
            @Override
            public void onSuccess(UserInfo info) {
                callback.onSuccess(info);
            }

            @Override
            public void onFailure(String msg) {
                callback.onFailure(msg);
            }
        });
    }

    @Override
    public void saveUserInfo(@NonNull UserInfo task) {

    }
}
