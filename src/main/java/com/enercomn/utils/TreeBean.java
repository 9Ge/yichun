package com.enercomn.utils;

import lombok.Data;

import java.util.List;

@Data
public class TreeBean {
    /**
     * id
     */
    public String id;

    /**
     * 父id
     */
    public String cParentId;


    List<TreeBean> childrenList;
}
