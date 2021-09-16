package com.enercomn.Enum;

/**
 * @Date: 2021/8/31 10:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum ResumeSourceEnum {
    EquipmentRecordLedger("设备维修记录"),
    EquipmentFailure("设备故障记录");

    private String name;

    ResumeSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
