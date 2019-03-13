package com.weiweizhang.latte_ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weiweizhang.latte_core.app.AccountManager;
import com.weiweizhang.latte_ec.database.DatabaseManager;
import com.weiweizhang.latte_ec.database.UserProfile;

public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        if(signListener != null) {
            signListener.onSignInSuccess();
        }
    }


    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        if(signListener != null) {
            signListener.onSignUpSuccess();
        }
    }
}
