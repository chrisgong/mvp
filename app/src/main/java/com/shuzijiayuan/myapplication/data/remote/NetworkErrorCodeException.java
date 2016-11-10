package com.shuzijiayuan.myapplication.data.remote;

/**
 * Created by gc on 2016/11/8.
 */

public class NetworkErrorCodeException extends Throwable {

    private int mCode;

    public NetworkErrorCodeException(int code) {
        this.mCode = code;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch (mCode) {
            case -1:
                message = "网络异常";
                break;
            case 400:
                message = "Token为null";
                break;
            case 401:
                message = "请求过于频繁";
                break;
            case 402:
                message = "服务器异常";
                break;
            case 403:
                message = "参数错误";
                break;
            default:
                message = super.getMessage();
                break;
        }
        return message;
    }
}
