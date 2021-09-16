package com.enercomn.Enum;

/**
 * @Date: 2021/8/31 10:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum RecordLedgerSourceEnum {
    EquipmentFailureCode("故障记录清单"),
    CheckPlanCode("周检完成记录"),;

    private String name;

    RecordLedgerSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
