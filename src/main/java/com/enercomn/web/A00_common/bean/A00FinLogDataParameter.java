package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * 查询日志信息参数类
 * Lyh
 */
@Data
public class A00FinLogDataParameter {
    /**
     * 订阅主题
     */
    private String cTopicalSub;

    /**
     * 时间查询开始
     */
    private String cInfoTimeStart;

    /**
     * 时间查询结束
     */
    private String cInfoTimeEnd;

    /**
     * 项目ID
     */
    //private Integer cTpId;
}
