package com.enercomn.web.baseManager.controller;

import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.service.A02EquipmentInfomationService;
import com.enercomn.web.A98UserLogin.jwt.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date: 2021/8/18 9:40<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@SuppressWarnings({"all"})
public class BaseController {

    /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

    @Autowired
    private A02EquipmentInfomationService a02EquipmentInfomationService;

    /**
     * @Details: 在高并发情况下，保障每个线程都能获取自己的req，resp
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }
    /**
     * @Details: 线程安全/获取req
     */
    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }
    /**
     * @Details: 线程安全/获取resp
     */
    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }
    /**
     * @Description 获取请求头String
     */
    public String getToken(){
        String appToken = request().getHeader("Authorization");
        if(StringUtils.isEmpty(appToken)){
            return null;
        }
        return appToken;
    }
    /**
     * @Details: 获取账户id
     */
    public String getAccount(){
        Claims claims = JwtTokenUtils.checkJWT(getToken());
        String jobNo = claims.get("username").toString();
        return jobNo;
    }

    /**
     * 获取登录用户
     * @return
     */
    public A02EquipmentInfomationBean getUser() {
        String account = this.getAccount();
        A02EquipmentInfomationBean infomationBean = a02EquipmentInfomationService.findByJobNo(account);
        return infomationBean;
    }



}
