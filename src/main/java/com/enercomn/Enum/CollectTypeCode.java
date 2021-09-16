package com.enercomn.Enum;

public enum CollectTypeCode {

    //采集类型ID 1MQTT /2modbus TCP/ 3OPC Server /4 plc 1200/5 plc 300/6 plc 850
    MQTT("MQTT",1,"mqtt协议"),

    MODBUS_TCP("modbus_TCP",2,"modbus协议"),

    OPC_SERVER("OPC_Server",3,"opc协议"),

    PLC_1200("PLC_1200",4,"西门子 1200协议"),

    PLC_300("PLC_300",5,"西门子 300协议"),

    //PLC_850("PLC 850",6,"西门子 850协议"),

    AB("AB",6,"西门子 AB协议"),

    BACNET("bacnet",7,"bacnet协议"),
    ;

    private String code;
    private int value;
    private String name;

    CollectTypeCode(String code,int value, String name) {
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

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String code) {
        this.value = value;
    }

    public void setCode(String code) {
        this.name = code;
    }

    public static String getCodeByValue(Integer value) {
        for (CollectTypeCode collectTypeCode : CollectTypeCode.values()) {
            if (collectTypeCode.getValue()== value) {
                return collectTypeCode.getCode();
            }
        }
        return null;
    }

}
