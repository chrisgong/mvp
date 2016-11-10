package com.shuzijiayuan.myapplication.login.contract;

import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;

/**
 * Created by gc on 2016/11/8.
 */

public interface LoginContract {
    
    public interface View {
        void loginSuccess();

        void showLoading();

        void cancelDialog();

        void showToast(String message);
    }

    public interface Presenter {

        void login(String phone, String password);
    }

    public interface Model {

        void login(long phone, String password, ILoginCallback callback);

        void saveUserInfo(UserInfo info);

        UserInfo getUserInfo();
    }

    public interface ILoginCallback {
        void success(UserInfo info);

        void failure(String message);
    }
}