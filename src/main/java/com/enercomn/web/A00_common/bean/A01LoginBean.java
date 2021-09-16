package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class A01LoginBean {
    /**
     * 用户名
     */
    private String cUsername;
    /**
     * 密码
     */
    private String cPassword;

}
