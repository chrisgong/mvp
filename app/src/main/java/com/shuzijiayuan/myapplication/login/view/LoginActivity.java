package com.shuzijiayuan.myapplication.login.view;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shuzijiayuan.myapplication.R;
import com.shuzijiayuan.myapplication.data.Injection;
import com.shuzijiayuan.myapplication.login.contract.LoginContract;
import com.shuzijiayuan.myapplication.login.presenter.LoginPresenterImpl;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private AutoCompleteTextView mTvPhone;
    private EditText mEtPassword;
    private LoginPresenterImpl mPresenter;
    private ProgressBar mPbLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTvPhone = (AutoCompleteTextView) findViewById(R.id.phone);
        mEtPassword = (EditText) findViewById(R.id.password);
        mPbLoading = (ProgressBar) findViewById(R.id.login_progress);
        mPresenter = new LoginPresenterImpl(Injection.provideLoginRepository(), this);

        findViewById(R.id.phone_sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        mPresenter.login(mTvPhone.getText().toString().trim(), mEtPassword.getText().toString());
    }

    @Override
    public void loginSuccess() {
        //登录成功
        Toast.makeText(this, R.string.result_login_success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelDialog() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(this, "login failure message: " + message, Toast.LENGTH_LONG).show();
    }
}

