package com.shuzijiayuan.myapplication.login;

/**
 * Created by gc on 2016/11/8.
 */

public interface LoginContract {
    
    interface View {
        void loginSuccess();

        void showLoading();

        void cancelDialog();

        void showToast(String message);
    }

    interface Presenter {

        void login(String phone, String password);
    }
}