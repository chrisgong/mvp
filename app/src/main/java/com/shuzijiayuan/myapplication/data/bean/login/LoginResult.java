package com.shuzijiayuan.myapplication.data.bean.login;

import java.util.ArrayList;

public class LoginResult {
    private boolean success;

    private int code;

    private Result data;

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public Result getData() {
        return data;
    }

    public static class Result {

        public String token;

        public String name;

        public int uid;

        public ArrayList<Device> devices;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public ArrayList<Device> getDevices() {
            return devices;
        }

        public void setDevices(ArrayList<Device> devices) {
            this.devices = devices;
        }

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

        public int getType() {
            return type;
        }

        public String getAddress() {
            return bluetooth;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }
    }

    public class ChildBandProfile {
        private String name;

        private String birthday;

        private int sex;

        private float warningHighTemp;

        private float warningLowTemp;


        public float getWarningHighTemp() {
            return warningHighTemp;
        }

        public float getWarningLowTemp() {
            return warningLowTemp;
        }

        public void setWarningHighTemp(float warningHighTemp) {
            this.warningHighTemp = warningHighTemp;
        }

        public void setWarningLowTemp(float warningLowTemp) {
            this.warningLowTemp = warningLowTemp;
        }

        @Override
        public String toString() {
            return "ChildBandProfile{" +
                    "name='" + name + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", sex=" + sex +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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
