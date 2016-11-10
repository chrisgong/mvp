package com.shuzijiayuan.myapplication.data.remote;


import com.shuzijiayuan.myapplication.data.bean.login.LoginRequest;
import com.shuzijiayuan.myapplication.data.bean.login.LoginResult;
import com.shuzijiayuan.myapplication.data.bean.profile.ProfileListResult;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chrisgong on 16/1/9.
 */
public interface ApiSet {
    /**
     * 正式线
     */
    public static final String KINTON_HOST = "https://kinton.cimyun.com";

    /**
     * 开发线
     */
    public static final String KINTON_DEV_HOST = "http://kinton.dev.cimyun.com";

    /**
     * 测试线
     */
    public static final String QA_HOST = "http://kinton.qa.cimyun.com";

    /**
     * 服务器地址, 新接口地址
     */
    public static final String TEST_HOST = "http://192.168.2.202:8080";

    /**
     * MOCK地址
     */
    public static final String MOCK_HOST = "http://mock.dev.cim120.cn";


    /**
     * 用户登录 kinton
     *
     * @param body
     * @return
     */
    @POST("/user/login")
    Observable<LoginResult> login(@Body LoginRequest body);

    /**
     * profile列表
     *
     * @return
     */
    @GET("/profile/list/{token}")
    Observable<ProfileListResult> profileList(@Path("token") String token);
}
