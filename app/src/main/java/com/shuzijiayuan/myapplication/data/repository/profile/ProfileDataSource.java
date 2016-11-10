package com.shuzijiayuan.myapplication.data.repository.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.bean.profile.ProfileInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gc on 2016/11/10.
 */

public interface ProfileDataSource {

    interface GetProfileCallback {
        void onGetProfile(ArrayList<ProfileInfo> infos);

        void onDataNotAvailable();
    }

    void getProfiles(@NonNull GetProfileCallback callback);

    void deleteAllProfiles();

    void saveProfiles(@NonNull ArrayList<ProfileInfo> info);
}
