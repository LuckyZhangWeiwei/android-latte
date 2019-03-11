package com.weiweizhang.latte_ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_ec.R;

public class SigninDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
