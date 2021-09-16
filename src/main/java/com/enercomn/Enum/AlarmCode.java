package com.enercomn.Enum;

public enum AlarmCode {
    TYPE_COMMON("typeComom","0", "普通规则"),

    TYPE_EXPRESSION("typeExpression","1", "表达式规则"),

    TYPE_ELE_EXPRESSION("typeEleExpression","2", "带元件id的表达式规则"),

    TIME_WEEK("week","1", "周"),
    TIME_MONTH("month","2", "月"),

    ALARM_STATE_UNDEL("alarmStateUndel","0", "未处理"),

    ALARM_STATE_DEL("alarmStateDel","1", "处理"),

    ALARM_STATE_OTHER_REASON("alarmStateOtherReason","2", "其他原因"),

    ALARM_STATE_CONFIRM("alarmStateConfirm","3", "确认"),

    ALARM_STATE_RECOVER("alarmStateRecover","4", "恢复"),

    ;

    private String code;
    private String value;
    private String name;

    AlarmCode(String code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.name = code;
    }
}
