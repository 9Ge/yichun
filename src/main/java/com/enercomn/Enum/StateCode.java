package com.enercomn.Enum;

public enum StateCode{

        SUCCESS("SUCCESS",1, "成功"),

        ERROR("ERROR",0, "错误"),

        PROJECT_OPEN("PROJECT_OPEN",0, "项目启用"),

        PROJECT_CLOSE("PROJECT_CLOSE",1, "项目禁用");

        private String code;
        private int value;
        private String name;

        StateCode(String code, int value, String name) {
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

