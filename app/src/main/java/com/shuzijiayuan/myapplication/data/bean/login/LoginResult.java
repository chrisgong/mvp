package com.shuzijiayuan.myapplication.data.bean.login;

import com.shuzijiayuan.myapplication.data.bean.BaseResult;

import java.util.ArrayList;

public class LoginResult extends BaseResult<LoginResult.Result> {

    public static class Result {

        public String token;

        public String name;

        public int uid;

        public ArrayList<Device> devices;

        @Override
        public String toString() {
            return "Result{" +
                    "token='" + token + '\'' +
                    ", name='" + name + '\'' +
                    ", token=" + uid +
                    ", devices=" + devices +
                    '}';
        }
    }

    public class Device {
        private int type;

        private String name;

        private String bluetooth;

        private String version;

        @Override
        public String toString() {
            return "Device{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    ", address='" + bluetooth + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
