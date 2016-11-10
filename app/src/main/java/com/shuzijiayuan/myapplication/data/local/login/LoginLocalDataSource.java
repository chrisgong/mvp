package com.shuzijiayuan.myapplication.data.local.login;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.repository.LoginDataSource;
import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;
import com.shuzijiayuan.myapplication.utils.TextUtils;

/**
 * Created by gc on 2016/11/10.
 */

public class LoginLocalDataSource implements LoginDataSource {

    private static LoginLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private LoginLocalDataSource() {

    }

    public static LoginLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUserInfo(@NonNull long phone, @NonNull String password, @NonNull GetUserInfoCallback callback) {
        String token = AppContext.getSharedPreferences().getString("token", "");
        if (TextUtils.isEmpty(token)) {
            callback.onDataNotAvailable();
        } else {
            callback.onGetUserInfo(new UserInfo(token));
        }
    }

    @Override
    public void deleteAllUserInfo() {

    }

    @Override
    public void saveUserInfo(@NonNull UserInfo task) {

    }
}
