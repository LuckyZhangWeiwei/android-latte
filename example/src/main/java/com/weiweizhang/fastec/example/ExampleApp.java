package com.weiweizhang.fastec.example;

import android.app.Application;

import com.weiweizhang.fastec.R;
import com.weiweizhang.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.weiweizhang.latte_core.net.interceptors.DebugInterceptor;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                .withApiHost("https://cnodejs.org/api/v1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
