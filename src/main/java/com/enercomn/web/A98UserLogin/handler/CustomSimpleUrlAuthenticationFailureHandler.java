package com.enercomn.web.A98UserLogin.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date: 2021/9/13 16:55<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component("customSimpleUrlAuthenticationFailureHandler")
public class CustomSimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        PrintWriter writer = httpServletResponse.getWriter();
        writer.print("1111");
        writer.close();
    }
}
