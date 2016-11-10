package com.shuzijiayuan.myapplication.data.repository.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;

import java.util.ArrayList;

/**
 * Created by gc on 2016/11/10.
 */

public interface ProfileDataSource {

    interface IProfileListCallback {
        void onSuccess(ArrayList<ProfileInfo> infos);

        void onFailure(String msg);

        void onDataNotAvailable();
    }

    void getProfiles(@NonNull IProfileListCallback callback);

    void deleteAllProfiles();

    void saveProfiles(@NonNull ArrayList<ProfileInfo> info);

    void refreshProfileList();
}
