package com.enercomn.web.A00_common.bean;

import lombok.Data;

@Data
public class A00DictionariesSelectBean {
    /**
     * 字典表id
     */
    private String cTdId;

    /**
     * 数据类型
     */
    private String cType;

    /**
     * 数据键
     */
    private String cKey;

    /**
     * 数据值
     */
    private String cValue;

}
