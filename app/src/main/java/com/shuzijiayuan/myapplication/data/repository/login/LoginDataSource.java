package com.shuzijiayuan.myapplication.data.repository.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.login.UserInfo;

/**
 * Created by gc on 2016/11/10.
 */

public interface LoginDataSource {

    interface ILoginCallback {
        void onSuccess(UserInfo info);

        void onFailure(String msg);
    }

    void getUserInfo(@NonNull long phone, @NonNull String password, @NonNull ILoginCallback callback);

    void saveUserInfo(@NonNull UserInfo task);
}
