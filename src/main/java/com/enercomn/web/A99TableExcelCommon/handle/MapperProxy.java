package com.enercomn.web.A99TableExcelCommon.handle;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用于Excel批量新增
 * @author yangcheng
 */
public class MapperProxy implements InvocationHandler {

    private Object target;

    public MapperProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }


}