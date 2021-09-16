package com.enercomn.jwt;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CooL
 * @date 2018/1/19
 * @author yc
 * 之前未GetUserIdUtils
 * 调整为TokenResolver
 */
@Component
public class TokenResolver {

    @Autowired
    private Audience audience;

    public String getUserId(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return map.get(JwtHelper.USERID) + "";
        }else {
            return "-1";
        }
    }

    public String getAreaId(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return  map.get(JwtHelper.AREAID) + "";
        }else {
            return "-1";
        }
    }

    public String getLoginName(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return  map.get(JwtHelper.LOGINNAME) + "";
        }else {
            return "-1";
        }
    }

    public String getProjectId(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return  map.get(JwtHelper.PROJECTID) + "";
        }else {
            return "-1";
        }
    }

    public String getUserLevel(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return  map.get(JwtHelper.USETLEVEL) + "";
        }else {
            return "-1";
        }
    }

    public String getRoleId(@NonNull String token) {
        Claims map = JwtHelper.parseJWT(token,audience.getBase64Secret());
        if(map!=null){
            return  map.get(JwtHelper.ROLEID) + "";
        }else {
            return "-1";
        }
    }
}
