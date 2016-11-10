package com.shuzijiayuan.myapplication.data;

import com.shuzijiayuan.myapplication.data.local.login.LoginLocalDataSource;
import com.shuzijiayuan.myapplication.data.remote.login.LoginRemoteDataSource;
import com.shuzijiayuan.myapplication.data.repository.LoginRepository;

/**
 * Created by gc on 2016/11/10.
 */

public class Injection {

    public static LoginRepository provideLoginRepository() {
        return LoginRepository.getInstance(LoginRemoteDataSource.getInstance(),
                LoginLocalDataSource.getInstance());
    }
}
