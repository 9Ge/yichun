package com.enercomn.Enum;

import com.enercomn.utils.StringUtils;

public enum ReprotSelectTypeCode {
    TYPE_DIFF("diff",1,"差值"),
    TYPE_INSTANS("instans",2,"瞬时值"),
    TYPE_INSTANS_DIFF("instansDiff",3,"瞬时值差值"),
    TYPE_COP_HOUR_AVG("hourAvgCop",4,"cop小时平均值"),
    TYPE_COP_HOUR_SUM("hourSumCop",5,"cop小时和值"),

    ;


    private String code;
    private int value;
    private String name;

    ReprotSelectTypeCode(String code, int value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public static String getCodeByValue(int value) {
        ReprotSelectTypeCode[] reprotSelectTypeCodes = values();
        for (ReprotSelectTypeCode reprotSelectTypeCode : reprotSelectTypeCodes) {
            if (reprotSelectTypeCode.getValue()== value) {
                return reprotSelectTypeCode.getCode();
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return StringUtils.getString(value);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.name = code;
    }
}
