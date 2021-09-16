package com.enercomn.web.baseManager.bean;

import lombok.Data;

/**
 * 基础查询参数实体类
 */
@Data
public class BaseSelectParamBean {
    private String id;
    protected int currPage ;
    protected int pageSize ;
}
