package com.enercomn.Enum;

/**
 * @Date: 2021/8/31 10:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum CheckPlanSourceEnum {
    EquipmentMaintenanceCode("预防性维护计划"),
    EquipmentFailureCode("设备故障"),
    EquipmentRepairCode("维修申请工单"),
    CheckExceptionCode("点检过程发现的问题点");

    private String name;

    CheckPlanSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
