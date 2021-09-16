package com.enercomn.web.A00_common.bean;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaojy on 2018-02-02.
 */
@Data
public class A00UserBean {

    private String loginName;

    private String password;

    private String role;

    private Set<String> permissions = new HashSet<>();
}
