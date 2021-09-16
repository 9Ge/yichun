package com.enercomn.utils;

import java.math.BigDecimal;
import java.util.Comparator;

public class TimeComparator implements Comparator<String> {

    @Override
    public int compare(String time1, String time2){
        //按年龄降序排序
        int n = 0 ;
        try {
            n= new BigDecimal(DateTimeUtils.dateToStamp(time1)).subtract(new BigDecimal(DateTimeUtils.dateToStamp(time2))).intValue();   //如果age1>age2,返回正数，反之返回负数，相等返回0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;

    }


}
