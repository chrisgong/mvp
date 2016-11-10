package com.shuzijiayuan.myapplication.profile.presenter;

import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileDataSource;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileRepository;
import com.shuzijiayuan.myapplication.profile.ProfileContract;
import com.shuzijiayuan.myapplication.profile.ProfilePresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Created by gc on 2016/11/10.
 */
public class ProfilePresenterImplTest {

    @Mock
    private ProfileRepository mRepository;

    @Mock
    private ProfileContract.View mView;

    @Captor
    private ArgumentCaptor<ProfileDataSource.IProfileListCallback> mCallbackCaptor;

    private ProfilePresenterImpl mPresenter;

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new ProfilePresenterImpl(mRepository, mView);
    }

    @Test
    public void login() throws Exception {
        mPresenter.getProfileList();
        verify(mView).showLoading();
        verify(mRepository).getProfiles(mCallbackCaptor.capture());
        mCallbackCaptor.getValue().onSuccess(new ArrayList<ProfileInfo>());
        verify(mView).cancelDialog();
    }

}