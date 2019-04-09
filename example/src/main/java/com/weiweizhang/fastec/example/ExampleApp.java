package com.weiweizhang.fastec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.weiweizhang.fastec.R;
import com.weiweizhang.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.weiweizhang.fastec.example.event.TestEvent;
import com.weiweizhang.latte_core.net.interceptors.DebugInterceptor;
import com.weiweizhang.latte_core.util.callback.CallbackManager;
import com.weiweizhang.latte_core.util.callback.CallbackType;
import com.weiweizhang.latte_core.util.callback.IGlobalCallback;
import com.weiweizhang.latte_ec.database.DatabaseManager;

import cn.jpush.android.api.JPushInterface;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                //.withApiHost("https://cnodejs.org/api/v1/")
//                .withApiHost("http://10.0.2.2:8055/")
                .withApiHost("http://192.168.1.122:8055/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWebEvent("test", new TestEvent())
                .withJavascriptInterface("latte")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
