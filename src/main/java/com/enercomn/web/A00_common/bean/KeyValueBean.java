package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 实体bean
 *
 * Created by lzr on 2018/1/30.
 */
@Data
public class KeyValueBean  {

    /**
     * id
     */
    public String id;

    /**
     * name
     */
    public String name;

    private String cTcId;

    private String cTcName;

}
