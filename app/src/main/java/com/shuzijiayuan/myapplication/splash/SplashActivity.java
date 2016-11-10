package com.shuzijiayuan.myapplication.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shuzijiayuan.myapplication.AppContext;
import com.shuzijiayuan.myapplication.R;
import com.shuzijiayuan.myapplication.login.LoginActivity;
import com.shuzijiayuan.myapplication.profile.ProfileListActivity;
import com.shuzijiayuan.myapplication.utils.TextUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by gc on 2016/11/10.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String token = AppContext.getSharedPreferences().getString("token", "");
        Observable.just(token)
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if(!TextUtils.isEmpty(s)){
                            startActivity(new Intent(SplashActivity.this, ProfileListActivity.class));
                        }else{
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }
                    }
                });
    }
}
