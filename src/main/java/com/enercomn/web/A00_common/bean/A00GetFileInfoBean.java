package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 获取文件bean
 * author wh
 * data 2019/5/16
 */
@Data
public class A00GetFileInfoBean {


    /**
     * 文件名称
     */
    public String cAttachmentName;

    /**
     * 文件路径
     */
    public String cFilePath;

    public String getcFilePath() {
        return cFilePath;
    }

    public void setcFilePath(String cFilePath) {
        this.cFilePath = cFilePath;
    }

    public String getcAttachmentName() {
        return cAttachmentName;
    }

    public void setcAttachmentName(String cAttachmentName) {
        this.cAttachmentName = cAttachmentName;
    }
}
