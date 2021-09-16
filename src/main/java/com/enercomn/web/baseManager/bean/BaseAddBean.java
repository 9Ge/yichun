package com.enercomn.web.baseManager.bean;

import lombok.Data;

import java.util.List;

/**
 * 基础新增实体
 */
@Data
public class BaseAddBean<T> {
    protected String id;
    protected String cCreateUser;
    protected String cUpdateUser;
    protected List<T> baseDetailBeanList;
}
