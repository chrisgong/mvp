package com.shuzijiayuan.myapplication.data.local.profile;

import android.support.annotation.NonNull;

import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.data.repository.profile.ProfileDataSource;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gc on 2016/11/10.
 */

public class ProfileLocalDataSource implements ProfileDataSource {

    private static ProfileLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private ProfileLocalDataSource() {

    }

    public static ProfileLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProfileLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getProfiles(@NonNull IProfileListCallback callback) {
        RealmResults<ProfileInfo> infos = Realm.getDefaultInstance().where(ProfileInfo.class).findAll();
        if(infos != null && infos.size() != 0) {
            callback.onSuccess((ArrayList<ProfileInfo>) Realm.getDefaultInstance().copyFromRealm(infos));
        }else{
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void deleteAllProfiles() {
        Realm.getDefaultInstance().where(ProfileInfo.class).findAll().deleteAllFromRealm();
    }

    @Override
    public void saveProfiles(@NonNull final ArrayList<ProfileInfo> infos) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (ProfileInfo info : infos) {
                    realm.copyToRealmOrUpdate(info);
                }
            }
        });
    }

    @Override
    public void refreshProfileList() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }
}
