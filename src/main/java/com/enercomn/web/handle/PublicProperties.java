package com.enercomn.web.handle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Date: 2021/9/15 17:38<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublicProperties {
    String extendsClassPath() default  "com.enercomn.web.baseManager.model.ModelCommonProperties";

    boolean saveSet() default  false;
    boolean updateSet() default  false;

}
