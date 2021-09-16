package com.enercomn.web.A98UserLogin.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtil {
    /**
     * 获取请求媒体类型
     * @param request http请求
     * @return 媒体类型
     */
    public static MediaType getRequestPrimaryAcceptMediaType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        MediaType acceptMediaType;
        if(StringUtils.isEmpty(accept)){
            acceptMediaType = MediaType.APPLICATION_JSON;
        }else{
            String[] str = accept.split(",");
            if(str.length>0){
                try {
                    acceptMediaType = MediaType.valueOf(str[0]);
                }catch (InvalidMediaTypeException ex){
                    acceptMediaType = MediaType.APPLICATION_JSON;
                }
            }else{
                acceptMediaType = MediaType.APPLICATION_JSON;
            }
        }
        return acceptMediaType;
    }
}