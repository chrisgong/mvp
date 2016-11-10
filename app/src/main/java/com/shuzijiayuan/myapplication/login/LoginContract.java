package com.shuzijiayuan.myapplication.login;

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

        String getToken();
    }
}