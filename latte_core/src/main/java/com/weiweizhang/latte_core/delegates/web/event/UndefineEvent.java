package com.weiweizhang.latte_core.delegates.web.event;


import com.weiweizhang.latte_core.util.log.LatteLogger;

/**
 * Created by 傅令杰
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
