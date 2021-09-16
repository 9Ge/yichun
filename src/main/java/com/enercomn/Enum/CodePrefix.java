package com.enercomn.Enum;

/**
 * @Date: 2021/9/15 9:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum CodePrefix {
    YFWH("YFWH"),
    DJ("DJ"),
    ZJJH("ZJJH"),
    GZBX("GZBX"),
    WXSQ("WXSQ"),
    WXJL("WXJL");

    private String prefix;

    CodePrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
