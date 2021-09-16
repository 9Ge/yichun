package com.enercomn.Enum;

public enum  ToyoutaCode {
    ELECTRIC("electric",1, "电力"),

    STEAM("steam",2, "蒸汽"),

    NATURAL_GAS("naturalGas",3, "天然气"),

    WATER("water",4, "水"),
    ;


    private String code;
    private int value;
    private String name;

    ToyoutaCode(String code, int value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public static String getCodeByValue(int value) {
        ToyoutaCode[] toyoutaCodes = values();
        for (ToyoutaCode toyoutaCode : toyoutaCodes) {
            if (toyoutaCode.getValue()== value) {
                return toyoutaCode.getCode();
            }
        }
        return null;
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
