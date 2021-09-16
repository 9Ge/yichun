package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 日志查询返回集合对象类
 * Lyh
 */
@Data
public class A00LogDataBean {

    /**
     * 发布主题
     */
    private String cTopicalSub;

    /**
     * JSON
     */
    private String cJsonMesg;

    /**
     * 时间
     */
    private String cInfoTime;
}
