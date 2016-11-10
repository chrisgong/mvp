package com.shuzijiayuan.myapplication.data.repository.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.data.bean.profile.ProfileInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gc on 2016/11/10.
 */

public class ProfileRepository implements ProfileDataSource {


    private static ProfileRepository INSTANCE = null;

    private final ProfileDataSource mProfileRemoteDataSource;

    private final ProfileDataSource mProfileLocalDataSource;

    boolean mCacheIsDirty = false;

    Map<String, ProfileInfo> mCaches;


    // Prevent direct instantiation.
    private ProfileRepository(@NonNull ProfileDataSource profileRemoteDataSource, @NonNull ProfileDataSource profileLocalDataSource) {
        mProfileRemoteDataSource = checkNotNull(profileRemoteDataSource);
        mProfileLocalDataSource = checkNotNull(profileLocalDataSource);
    }

    public static ProfileRepository getInstance(ProfileDataSource profileRemoteDataSource, ProfileDataSource profileLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ProfileRepository(profileRemoteDataSource, profileLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getProfiles(@NonNull final GetProfileCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCaches != null && !mCacheIsDirty) {
            callback.onGetProfile(new ArrayList<>(mCaches.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getProfilesFromRemoteDataSource(callback);
        } else {
            mProfileLocalDataSource.getProfiles(new GetProfileCallback() {
                @Override
                public void onGetProfile(ArrayList<ProfileInfo> infos) {
                    refreshCache(infos);
                    callback.onGetProfile(infos);
                }

                @Override
                public void onDataNotAvailable() {
                    getProfilesFromRemoteDataSource(callback);
                }
            });
        }
    }

    private void refreshCache(ArrayList<ProfileInfo> infos) {
        if (mCaches == null) {
            mCaches = new LinkedHashMap<>();
        }
        mCaches.clear();
        for (ProfileInfo info : infos) {
            mCaches.put(info.iid, info);
        }
        mCacheIsDirty = false;
    }

    private void getProfilesFromRemoteDataSource(final GetProfileCallback callback) {
        mProfileRemoteDataSource.getProfiles(new GetProfileCallback() {
            @Override
            public void onGetProfile(ArrayList<ProfileInfo> infos) {
                refreshCache(infos);
                refreshLocalDataSource(infos);
                callback.onGetProfile(infos);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    private void refreshLocalDataSource(ArrayList<ProfileInfo> infos) {
        mProfileLocalDataSource.deleteAllProfiles();
        mProfileLocalDataSource.saveProfiles(infos);
    }

    @Override
    public void deleteAllProfiles() {
        mProfileLocalDataSource.deleteAllProfiles();
        mProfileRemoteDataSource.deleteAllProfiles();

        if (mCaches == null) {
            mCaches = new LinkedHashMap<>();
        }
        mCaches.clear();
    }

    @Override
    public void saveProfiles(@NonNull ArrayList<ProfileInfo> info) {

    }
}
