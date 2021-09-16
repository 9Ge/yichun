package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 获取文件列表bean
 * author wh
 * data 2019/5/16
 */
@Data
public class A00GetFileListInfoBean {

    /**
     * 文件键值
     */
    private String cTaId;

    /**
     * 文件名称
     */
    private String cAttachmentName;

    /**
     * 文件类型
     */
    private String cFileType;
    /*
    * 文件地址
    * */
    private String cFilePath;

    /**
     * 文件序号
     */
    private int cPriority;

    /**
     * 创建时间
     */
    private String cCreateTime;

}
