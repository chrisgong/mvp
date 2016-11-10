package com.shuzijiayuan.myapplication.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileDataSource;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileRepository;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/10
 */

public class ProfilePresenterImpl implements ProfileContract.Presenter {

    private ProfileContract.View mView;

    private ProfileRepository mRepository;

    public ProfilePresenterImpl(@NonNull ProfileRepository repository, @NonNull ProfileContract.View view) {
        this.mView = checkNotNull(view);
        this.mRepository = checkNotNull(repository);
    }

    @Override
    public void getProfileList() {
        mView.showLoading();
        mRepository.refreshProfileList();
        mRepository.getProfiles(new ProfileDataSource.IProfileListCallback() {
            @Override
            public void onSuccess(ArrayList<ProfileInfo> infos) {
                mView.cancelDialog();
                mView.getSuccess(infos);
            }

            @Override
            public void onFailure(String msg) {
                mView.cancelDialog();
                mView.showToast(msg);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}