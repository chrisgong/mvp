package com.shuzijiayuan.myapplication.data.bean.login;

/**
 * Created by gc on 2016/11/8.
 */
public class UserInfo {
    public String token;

    public UserInfo(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "token='" + token + '\'' +
                '}';
    }
}
