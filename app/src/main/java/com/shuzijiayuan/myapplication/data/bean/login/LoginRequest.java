package com.shuzijiayuan.myapplication.data.bean.login;

/**
 * Created by chrisgong on 16/1/22.
 */
public class LoginRequest {

    public long phone;

    public String password;

    public String imei;

    public int type;

    public LoginRequest(long phone, String password, String imei, int type) {
        this.phone = phone;
        this.password = password;
        this.imei = imei;
        this.type = type;
    }
}
