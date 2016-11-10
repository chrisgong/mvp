package com.shuzijiayuan.myapplication.login.presenter;

import com.shuzijiayuan.myapplication.data.repository.login.LoginDataSource;
import com.shuzijiayuan.myapplication.data.repository.login.LoginRepository;
import com.shuzijiayuan.myapplication.login.LoginContract;
import com.shuzijiayuan.myapplication.data.bean.login.UserInfo;
import com.shuzijiayuan.myapplication.login.LoginPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by gc on 2016/11/10.
 */
public class LoginPresenterImplTest {

    @Mock
    private LoginRepository mRepository;

    @Mock
    private LoginContract.View mView;

    @Captor
    private ArgumentCaptor<LoginDataSource.GetUserInfoCallback> mLoginCallbackCaptor;

    private LoginPresenterImpl mPresenter;

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenterImpl(mRepository, mView);
    }

    @Test
    public void login() throws Exception {
        String phone = "18618416321";
        String password = "880316";
        UserInfo info = new UserInfo("12i0id0i901i2e091i2e09i09");
        mPresenter.login(phone, password);

        verify(mView).showLoading();
        verify(mRepository).getUserInfo(eq(Long.parseLong(phone)), eq(password), mLoginCallbackCaptor.capture());
        mLoginCallbackCaptor.getValue().onGetUserInfo(info);
        verify(mView).cancelDialog();
        verify(mView).loginSuccess();
    }
}