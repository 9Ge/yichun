package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 删除文件表bean
 * author wh
 * data 2019/5/16
 */
@Data
public class A00DeleteFileInfoBean {

    /**
     * 文件键值
     */
    public int cTaId;



    public int getcTaId() {
        return cTaId;
    }

    public void setcTaId(int cTaId) {
        this.cTaId = cTaId;
    }

}
