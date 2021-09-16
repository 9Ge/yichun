package com.enercomn.Enum;

public enum AgreementCode {

    MATT_CODE("MATT_CODE",1, "MQTT代码"),
    MODBUS_CODE("MODBUS_CODE",2, "modbus代码"),
    S7_CODE("S7_CODE",3, "s7代码"),
    PLC_800_CODE("PLC_800_CODE",4, "PLC800代码");

    private String code;
    private int value;
    private String name;

    AgreementCode(String code, int value, String name) {
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
