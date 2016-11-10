package com.shuzijiayuan.myapplication.data.model;

/**
 * Created by gc on 16/6/16.
 */
public class BaseResult<T> {
    public boolean success;

    public int code;

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public T data;

    public boolean isOk() {
        return code == 1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
