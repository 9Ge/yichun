package com.enercomn.Enum;

import com.enercomn.utils.StringUtils;

public enum CalculationCode {
    CUMULATIVE("cumulative",1,"累计"),

    POLYMERIZE("polymerize",2,"聚合")
    ;

    private String code;
    private int value;
    private String name;

    CalculationCode(String code,int value,  String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return StringUtils.getString(value);
    }

    public String getCode() {
        return code;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCode(String code) {
        this.name = code;
    }
}
