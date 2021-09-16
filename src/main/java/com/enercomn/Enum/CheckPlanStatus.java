package com.enercomn.Enum;

/**
 * @Date: 2021/8/31 10:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum CheckPlanStatus {
    UNFINISHED("未完成","10141"),
    FINISHED("已完成","10142");

    private String name;
    private String statusCode;

    CheckPlanStatus(String name, String statusCode) {
        this.name = name;
        this.statusCode = statusCode;
    }

    public String getName() {
        return name;
    }

    public String getStatus  () {
        return statusCode;
    }
}
