package com.shuzijiayuan.myapplication.data.remote.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.model.login.LoginRequest;
import com.shuzijiayuan.myapplication.data.model.login.LoginResult;
import com.shuzijiayuan.myapplication.data.model.login.UserInfo;
import com.shuzijiayuan.myapplication.data.remote.ApiUtils;
import com.shuzijiayuan.myapplication.data.remote.NetworkErrorCodeException;
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
    public void getUserInfo(@NonNull long phone, @NonNull String password, @NonNull final ILoginCallback callback) {
        ApiUtils.getKintonInstance().login(new LoginRequest(phone, password, "A123980123800", 0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginResult>() {
                    @Override
                    public void call(LoginResult loginResult) {
                        if (loginResult != null) {
                            String token = loginResult.getData().token;
                            if (!TextUtils.isEmpty(token)) {
                                UserInfo info = new UserInfo(token);
                                saveUserInfo(info);
                                callback.onSuccess(info);
                            } else {
                                callback.onFailure(new NetworkErrorCodeException(loginResult.getCode()).getMessage());
                            }
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
    public void saveUserInfo(@NonNull UserInfo info) {
        AppContext.getSharedPreferences().edit().putString("token", info.token).commit();
    }
}
