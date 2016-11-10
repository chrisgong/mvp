package com.shuzijiayuan.myapplication.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shuzijiayuan.myapplication.R;
import com.shuzijiayuan.myapplication.data.Injection;
import com.shuzijiayuan.myapplication.data.model.profile.ProfileInfo;
import com.shuzijiayuan.myapplication.utils.TextUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gc on 2016/11/10.
 */

public class ProfileListActivity extends Activity implements ProfileContract.View {

    private ProfilePresenterImpl mPresenter;

    private ProgressBar mProgressBar;

    private ListView mLvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        mLvProfile = (ListView) findViewById(R.id.list_profile);
        mPresenter = new ProfilePresenterImpl(Injection.provideProfileListRepository(), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    public void loadData() {
        mPresenter.getProfileList();
    }


    @Override
    public void getSuccess(ArrayList<ProfileInfo> infos) {
        mLvProfile.setAdapter(new ProfileAdapter(infos));
        cancelDialog();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {

    }

    private class ProfileAdapter extends BaseAdapter {

        private ArrayList<ProfileInfo> infos;

        public ProfileAdapter(ArrayList<ProfileInfo> infos) {
            this.infos = infos;
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = ProfileListActivity.this.getLayoutInflater().inflate(R.layout.list_item, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.imgHead = (ImageView) convertView.findViewById(R.id.img_head);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ProfileInfo info = (ProfileInfo) getItem(position);
            String name = info.name;
            if (!TextUtils.isEmpty(name)) {
                holder.tvName.setText(name);
            }

            String head = info.face;
            if(!TextUtils.isEmpty(head)){
                Picasso.with(ProfileListActivity.this).load(head).into(holder.imgHead);
            }
            return convertView;
        }

        class ViewHolder {
            private TextView tvName;
            private ImageView imgHead;
        }
    }
}
