package com.enercomn.web.handle.aop;

import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import com.enercomn.web.handle.PublicProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date: 2021/9/15 17:41<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */

@Component
@Aspect
@Slf4j
public class PublicPropertiesAspect {

    @Autowired
    private BaseController baseController;

    @Pointcut("@annotation(com.enercomn.web.handle.PublicProperties)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void around(JoinPoint joinPoint) throws Throwable {
        A02EquipmentInfomationBean user = baseController.getUser();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PublicProperties publicProperties = signature.getMethod().getDeclaredAnnotation(PublicProperties.class);
        String extendsClassPath = publicProperties.extendsClassPath();
        boolean saveFlag = publicProperties.saveSet();
        boolean updateFlag = publicProperties.updateSet();

        Object[] args = joinPoint.getArgs();
        Class<?> baseClass = Class.forName(extendsClassPath);
        for (int i = 0; i < args.length; i++) {
            Class<?> aClass1 = args[i].getClass();
            if (baseClass.isAssignableFrom(aClass1)) {
                ModelCommonProperties modelCommonProperties = (ModelCommonProperties) args[i];
                if (saveFlag) {
                    modelCommonProperties.setCreateDate(new Date());
                    modelCommonProperties.setCreateUser(user.getTeiId());
                }
                if (updateFlag) {
                    modelCommonProperties.setUpdateDate(new Date());
                    modelCommonProperties.setUpdateUser(user.getTeiId());
                }
            }
        }
    }
}