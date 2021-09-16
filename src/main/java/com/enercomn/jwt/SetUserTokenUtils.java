package com.enercomn.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by CooL on 2018/1/19.
 */
@Component
public class SetUserTokenUtils {

    @Autowired
    private Audience audience;

    public String getUserToken(String loginName, String userId, long TTLMillis) {
        String appToken = JwtHelper.createJWTApp(loginName, userId, TTLMillis ,audience.getBase64Secret());
        if(appToken!=null){
            return appToken;
        }else {
            return "-1";
        }
    }
}
