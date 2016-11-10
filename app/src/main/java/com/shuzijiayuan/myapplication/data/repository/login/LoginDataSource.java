package com.shuzijiayuan.myapplication.data.repository.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;

/**
 * Created by gc on 2016/11/10.
 */

public interface LoginDataSource {

    interface GetUserInfoCallback {
        void onGetUserInfo(UserInfo info);

        void onDataNotAvailable();
    }

    void getUserInfo(@NonNull long phone, @NonNull String password, @NonNull GetUserInfoCallback callback);

    void deleteAllUserInfo();

    void saveUserInfo(@NonNull UserInfo task);

    String getToken();
}
