package com.enercomn.Enum;

import com.enercomn.utils.StringUtils;

public enum EnergyAnalysisCode {

        ANALYSIS_TYPE_1("analysis_type_1",1, "id型"),

        ANALYSIS_TYPE_2("analysis_type_2",2, "公式类型"),

        INSTANCE_DATA("instance_data",1, "瞬时值"),

        DIFF_DATA("diff_data",2, "差值"),

        SUM_DATA("sum_data",3, "和值"),

        AVG_DATA("avg_data",4, "平均值"),

        COP_PERCENT("cop_percent",5, "COP百分比"),

        PIE_CHART("pie_chart",6, "饼图"),

        TOTAL_DATA("total_data",7, "总值"),

        REALTIME_DATA("realTime_data",8, "实时数据"),

        TIME_TYPE_DAY("time_type_day",1, "日"),

        TIME_TYPE_WEEK("time_type_week",2, "周"),

        TIME_TYPE_MONTH("time_type_month",3, "月"),

        TIME_TYPE_YEAR("time_type_year",4, "年"),


    ;

        private String code;
        private int value;
        private String name;

        EnergyAnalysisCode(String code, int value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getStringValue() {
        return StringUtils.getString(value);
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

