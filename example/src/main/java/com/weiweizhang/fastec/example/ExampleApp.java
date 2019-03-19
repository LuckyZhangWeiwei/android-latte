package com.weiweizhang.fastec.example;

import android.app.Application;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.weiweizhang.fastec.R;
import com.weiweizhang.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.weiweizhang.latte_core.net.interceptors.DebugInterceptor;
import com.weiweizhang.latte_ec.database.DatabaseManager;
import com.weiweizhang.latte_ec.sign.ISignListener;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                //.withApiHost("https://cnodejs.org/api/v1/")
                .withApiHost("http://10.0.2.2:8055/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
