package com.weiweizhang.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.weiweizhang.latte_core.activites.ProxyActivity;
import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_ec.launcher.LauncherDelegate;
import com.weiweizhang.latte_ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

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
        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
    }
}
