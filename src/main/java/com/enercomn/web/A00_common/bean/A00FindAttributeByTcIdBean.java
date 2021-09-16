package com.enercomn.web.A00_common.bean;

import lombok.Data;

@Data
public class A00FindAttributeByTcIdBean {
    /**
     * 元件属性集
     */
    private String cTcaIdList;
    
    /**
     * 元件属性唯一标识列
     */
    private String cTcaId;

    /**
     * 元件属性名称
     */
    private String cName;

    /**
     * 元件属性单位
     */
    private String cUnit;

    /**
     * 元件属性数值类型
     */
    private Integer cTntId;

    /**
     * 项目编号
     */
    private String cTpId;

    /**
     * 编号A、B、C
     */
    private String cIndex;
}
