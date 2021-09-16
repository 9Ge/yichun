package com.enercomn.web.baseManager.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseUpdateBean<T> {

    protected String id;

    protected String cUpdateUser;

    protected List<T> baseDetailBeanList;
}
