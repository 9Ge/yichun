package com.enercomn.Enum;

public enum  UserLevelCode {
    SUPER_ADMIN("SUPER_ADMIN",2, "超级管理员"),

    ADMIN("ADMIN",1, "企业管理员"),

    OTHER("OTHER",0, "普通用户");


    private String code;
    private int value;
    private String name;

    UserLevelCode(String code, int value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
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
