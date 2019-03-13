package com.weiweizhang.latte_ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.net.RestClient;
import com.weiweizhang.latte_core.net.callback.IError;
import com.weiweizhang.latte_core.net.callback.IFailure;
import com.weiweizhang.latte_core.net.callback.ISuccess;
import com.weiweizhang.latte_core.util.log.LatteLogger;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if(checkForm()) {
            RestClient.builder()
            .url("user_profile.json")
            .params("name", mName.getText().toString())
            .params("email", mEmail.getText().toString())
            .params("phone", mPhone.getText().toString())
            .params("password", mPassword.getText().toString())
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    LatteLogger.json("USER_PROFILE", response);
                    SignHandler.onSignUp(response, null);
                }
            })
            .error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    LatteLogger.json("USER_PROFILE", msg);
                }
            })
            .failure(new IFailure() {
                @Override
                public void onFailure(String message) {
                    LatteLogger.json("USER_PROFILE", message);
                }
            })
            .build()
            //.post();
            .get();
        }
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SigninDelegate(), SINGLETASK);
    }
}
