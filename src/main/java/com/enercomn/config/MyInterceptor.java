package com.enercomn.config;

import com.enercomn.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统访问过滤器配置
 */
public class MyInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("=======================" + request.getHeader("appToken") + "**"+request.getRequestURL());
        String apptoken = request.getHeader("appToken");

        if(request.getRequestURL().indexOf("swagger")>0||
                request.getRequestURL().indexOf("api-docs")>0 ||
                request.getRequestURL().indexOf("" +
                        "")>0){
            return true;
        }

        if(request.getRequestURL().indexOf("A00LoginController")>0){
            return true;
        }

        if(request.getRequestURL().indexOf("uploadFile")>0||request.getRequestURL().indexOf("deleteFile")>0){
            return true;
        }
        if(request.getRequestURL().indexOf("code")>0){
            return true;
        }

//        if(StringUtils.isEmpty(request.getHeader("mobileFlag"))){
//            return false;
//        }
        if(request.getRequestURL().indexOf("LoginController")<0
                &&"1".equals(request.getHeader("mobileFlag"))
                && StringUtils.isEmpty(request.getHeader("token"))){
            logger.info("=======================" + request.getHeader("appToken") + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return false;
        }
//        request.getRequestURL()
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //logger.info(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //logger.info(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
