package com.weiweizhang.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.weiweizhang.latte_core.activites.ProxyActivity;
import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_ui.launcher.ILauncherListener;
import com.weiweizhang.latte_ui.launcher.OnLauncherFinishTag;
import com.weiweizhang.latte_ec.launcher.LauncherDelegate;
import com.weiweizhang.latte_ec.main.EcBottomDelegate;
import com.weiweizhang.latte_ec.sign.ISignListener;
import com.weiweizhang.latte_ec.sign.SigninDelegate;

import me.yokeyword.fragmentation.ISupportFragment;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        onLauncherFinish(OnLauncherFinishTag.SIGNED);
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SigninDelegate());
                break;
            default:
                break;
        }
    }
}
