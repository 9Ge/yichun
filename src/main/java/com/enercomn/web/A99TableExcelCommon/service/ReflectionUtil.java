package com.enercomn.web.A99TableExcelCommon.service;

import java.lang.reflect.Field;

/**
 * @Date: 2021/9/16 16:18<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class ReflectionUtil {

    public static String getStringByDeclaredField(Object obj,String declaredFieldName) throws NoSuchFieldException, IllegalAccessException {
        Field propertiesField = obj.getClass().getDeclaredField(declaredFieldName);
        propertiesField.setAccessible(true);
        return String.valueOf(propertiesField.get(obj));
    }

    public static void setString2DeclaredField(Object obj,String declaredFieldName,String value) throws NoSuchFieldException, IllegalAccessException {
        Field propertiesField = obj.getClass().getDeclaredField(declaredFieldName);
        propertiesField.setAccessible(true);
        propertiesField.set(obj,value);
    }
}
