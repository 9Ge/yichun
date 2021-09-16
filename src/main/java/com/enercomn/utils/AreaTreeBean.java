package com.enercomn.utils;

import lombok.Data;

import java.util.List;

@Data
public class AreaTreeBean {
    /**
     * 区域id
     */
    public String cTamId;

    /**
     * 区域名
     */
    public String cName;

    /**
     * 父区域id
     */
    public String cParentId;


    List<AreaTreeBean> children;
}