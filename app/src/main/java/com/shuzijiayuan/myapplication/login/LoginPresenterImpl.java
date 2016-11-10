package com.shuzijiayuan.myapplication.login;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.login.UserInfo;
import com.shuzijiayuan.myapplication.data.repository.login.LoginDataSource;
import com.shuzijiayuan.myapplication.data.repository.login.LoginRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/08
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginContract.View mView;

    private LoginRepository mRepository;

    public LoginPresenterImpl(@NonNull LoginRepository repository, @NonNull LoginContract.View view) {
        this.mView = checkNotNull(view);
        this.mRepository = checkNotNull(repository);
    }

    @Override
    public void login(String phone, String password) {
        mView.showLoading();
        mRepository.getUserInfo(Long.parseLong(phone), password, new LoginDataSource.ILoginCallback() {
            @Override
            public void onSuccess(UserInfo info) {
                mView.cancelDialog();
                mView.loginSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.cancelDialog();
                mView.showToast(msg);
            }
        });
    }
}