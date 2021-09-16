package com.enercomn.web.A99TableExcelCommon.anno;

import java.lang.annotation.*;

/**
 * @Date: 2021/9/16 11:30<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conversion {

    String saveColumn();
    String saveProperties();
    String byTable();
    String showColumn();
    String showProperties();
    SQLInfo saveSql() default  @SQLInfo(sql = "", param ={} );

    boolean saveFlag() default false;
}
