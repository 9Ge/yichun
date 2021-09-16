package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 元件查询实体类
 * 创建时间 2020-02-27
 * Tjw
 */
@Data
public class A11FindComponentManagementBean {
    /**
     * 采集元件类型唯一标识
     */
    private String componentTcid;

    /**
     * 采集元件唯一标识
     */
    private String cTemId;

}
