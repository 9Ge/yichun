package com.enercomn.web.A99TableExcelCommon.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Enercomn
 */
//@Aspect
//@Component
public class AspectDemo {
    @Pointcut(value = "@annotation(com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey)")
    public void access() {}

    //进来切点世界，先经过的第一个站
    @Before("access()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("-aop 日志记录启动-" + new Date());
    }

    //进来切点这，最后经过的一个站，也是方法正常运行结束后
    @After("access()")
    public void after(JoinPoint joinPoint) {
        System.out.println("-aop 日志记录结束-" + new Date());
    }


}
