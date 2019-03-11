package com.weiweizhang.fastec.example;

import com.weiweizhang.latte_core.activites.ProxyActivity;
import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_ec.launcher.LauncherDelegate;
import com.weiweizhang.latte_ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
        return new LauncherScrollDelegate();
    }
}
