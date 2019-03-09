package com.weiweizhang.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER.name());
    }

}
