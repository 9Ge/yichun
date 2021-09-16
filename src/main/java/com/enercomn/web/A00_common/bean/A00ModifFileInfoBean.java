package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 修改文件文件bean
 * author wh
 * data 2019/5/17
 */
@Data
public class A00ModifFileInfoBean {


    /**
     * 文件键值
     */
    public String cTaId;
    /**
     * 文件名称
     */
    private String cAttachmentName;

    /**
     * 文件序号
     */
    private int cPriority;

}
