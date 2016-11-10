package com.shuzijiayuan.myapplication.data.remote.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.bean.login.LoginRequest;
import com.shuzijiayuan.myapplication.data.bean.login.LoginResult;
import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;
import com.shuzijiayuan.myapplication.data.remote.ApiUtils;
import com.shuzijiayuan.myapplication.data.repository.login.LoginDataSource;
import com.shuzijiayuan.myapplication.utils.TextUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by gc on 2016/11/10.
 */

public class LoginRemoteDataSource implements LoginDataSource {

    private static LoginRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private LoginRemoteDataSource() {

    }

    public static LoginRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getUserInfo(@NonNull long phone, @NonNull String password, @NonNull final GetUserInfoCallback callback) {
        ApiUtils.getKintonInstance().login(new LoginRequest(phone, password, "A123980123800", 0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginResult>() {
                    @Override
                    public void call(LoginResult loginResult) {
                        if (loginResult != null) {
                            String token = loginResult.getData().token;
                            if (!TextUtils.isEmpty(token)) {
                                callback.onGetUserInfo(new UserInfo(token));
                            } else {
                                callback.onDataNotAvailable();
                            }
                        }
                    }
                });
    }

    @Override
    public void deleteAllUserInfo() {
        AppContext.getSharedPreferences().edit().clear().commit();
    }

    @Override
    public void saveUserInfo(@NonNull UserInfo info) {
        AppContext.getSharedPreferences().edit().putString("token", info.token).commit();
    }

    @Override
    public String getToken() {
        return AppContext.getSharedPreferences().getString("token", "");
    }
}
