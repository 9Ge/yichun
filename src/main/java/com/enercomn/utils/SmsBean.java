package com.enercomn.utils;

import lombok.Data;

@Data
public class SmsBean {



    /**
     * 报警区域
     */
    private String area;
    /**
     * 传感器名
     */
    private String name;
    /**
     * 详情
     */
    private String detail;
}
