package com.weiweizhang.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.weiweizhang.latte_core.activites.ProxyActivity;
import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.ui.launcher.ILauncherListener;
import com.weiweizhang.latte_core.ui.launcher.OnLauncherFinishTag;
import com.weiweizhang.latte_ec.launcher.LauncherDelegate;
import com.weiweizhang.latte_ec.launcher.LauncherScrollDelegate;
import com.weiweizhang.latte_ec.main.EcBottomDelegate;
import com.weiweizhang.latte_ec.sign.ISignListener;
import com.weiweizhang.latte_ec.sign.SignUpDelegate;
import com.weiweizhang.latte_ec.sign.SigninDelegate;

import static com.weiweizhang.latte_core.ui.launcher.OnLauncherFinishTag.NOT_SIGNED;
import static com.weiweizhang.latte_core.ui.launcher.OnLauncherFinishTag.SIGNED;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
//        return new SignUpDelegate();
//        return new SigninDelegate();
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SigninDelegate());
                break;
            default:
                break;
        }
    }


}
