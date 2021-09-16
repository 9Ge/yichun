package com.enercomn.web.baseManager.bean;

import lombok.Data;

/**
 * 更新参数实体
 */
@Data
public class BaseUpdateParamBean {

    private String id;

    protected String cUpdateUser;

    protected String isDel;
}
