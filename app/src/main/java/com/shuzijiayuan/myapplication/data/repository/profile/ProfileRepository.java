package com.shuzijiayuan.myapplication.data.repository.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;

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

    /**
     * 缓存
     */
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
    public void getProfiles(@NonNull final IProfileListCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCaches != null && !mCacheIsDirty) {
            callback.onSuccess(new ArrayList<>(mCaches.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getProfilesFromRemoteDataSource(callback);
        } else {
            mProfileLocalDataSource.getProfiles(new IProfileListCallback() {
                @Override
                public void onSuccess(ArrayList<ProfileInfo> infos) {
                    refreshCache(infos);
                    callback.onSuccess(new ArrayList<>(mCaches.values()));
                }

                @Override
                public void onFailure(String msg) {

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

    private void getProfilesFromRemoteDataSource(final IProfileListCallback callback) {
        mProfileRemoteDataSource.getProfiles(new IProfileListCallback() {
            @Override
            public void onSuccess(ArrayList<ProfileInfo> infos) {
                refreshCache(infos);
                refreshLocalDataSource(infos);
                callback.onSuccess(new ArrayList<>(mCaches.values()));
            }

            @Override
            public void onFailure(String msg) {
                callback.onFailure(msg);
            }

            @Override
            public void onDataNotAvailable() {

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
    public void saveProfiles(@NonNull ArrayList<ProfileInfo> infos) {
        checkNotNull(infos);
        mProfileLocalDataSource.saveProfiles(infos);

        // Do in memory cache update to keep the app UI up to date
        if (mCaches == null) {
            mCaches = new LinkedHashMap<>();
        }

        for (ProfileInfo info : infos) {
            mCaches.put(info.iid, info);
        }
    }

    @Override
    public void refreshProfileList() {
        mCacheIsDirty = true;
    }
}
