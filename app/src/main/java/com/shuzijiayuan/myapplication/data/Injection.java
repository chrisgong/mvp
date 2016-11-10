package com.shuzijiayuan.myapplication.data;

import com.shuzijiayuan.myapplication.data.local.login.LoginLocalDataSource;
import com.shuzijiayuan.myapplication.data.local.profile.ProfileLocalDataSource;
import com.shuzijiayuan.myapplication.data.remote.login.LoginRemoteDataSource;
import com.shuzijiayuan.myapplication.data.remote.profile.ProfileRemoteDataSource;
import com.shuzijiayuan.myapplication.data.repository.login.LoginRepository;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileRepository;

/**
 * Created by gc on 2016/11/10.
 */

public class Injection {

    public static LoginRepository provideLoginRepository() {
        return LoginRepository.getInstance(LoginRemoteDataSource.getInstance(),
                LoginLocalDataSource.getInstance());
    }

    public static ProfileRepository provideProfileListRepository() {
        return ProfileRepository.getInstance(ProfileRemoteDataSource.getInstance(), ProfileLocalDataSource.getInstance());
    }
}
