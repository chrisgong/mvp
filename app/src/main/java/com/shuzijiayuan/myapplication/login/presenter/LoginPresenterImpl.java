package com.shuzijiayuan.myapplication.login.presenter;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;
import com.shuzijiayuan.myapplication.data.repository.LoginDataSource;
import com.shuzijiayuan.myapplication.data.repository.LoginRepository;
import com.shuzijiayuan.myapplication.login.contract.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/08
 */

public class LoginPresenterImpl implements LoginContract.Presenter, LoginContract.ILoginCallback {

    private LoginContract.View mView;

    private LoginRepository mRepository;

    public LoginPresenterImpl(@NonNull LoginRepository repository, @NonNull LoginContract.View view) {
        this.mView = checkNotNull(view);
        this.mRepository = checkNotNull(repository);
    }

    @Override
    public void login(String phone, String password) {
        mView.showLoading();
        mRepository.getUserInfo(Long.parseLong(phone), password, new LoginDataSource.GetUserInfoCallback() {
            @Override
            public void onGetUserInfo(UserInfo info) {
                mView.cancelDialog();
                mView.loginSuccess();
            }

            @Override
            public void onDataNotAvailable() {
                mView.cancelDialog();
            }
        });
    }

    @Override
    public void success(UserInfo info) {
        mView.cancelDialog();
        mView.loginSuccess();
        mRepository.saveUserInfo(info);
    }

    @Override
    public void failure(String msg) {
        mView.showToast(msg);
        mView.cancelDialog();
    }
}