package com.shuzijiayuan.myapplication.profile;

import com.shuzijiayuan.myapplication.data.bean.profile.ProfileInfo;

import java.util.ArrayList;

/**
 * Created by gc on 2016/11/10.
 * 约束接口
 */
public interface ProfileContract {

    public interface View {
        void getSuccess(ArrayList<ProfileInfo> infos);

        void showLoading();

        void cancelDialog();

        void showToast(String message);
    }

    public interface Presenter {

        void getProfileList();
    }
}