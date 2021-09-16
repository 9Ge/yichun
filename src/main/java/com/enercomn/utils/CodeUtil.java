package com.enercomn.utils;

import java.util.Date;

/**
 * @Date: 2021/9/15 9:43<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class CodeUtil {

    public static String generateCode(String prefix){
        String time = DateTimeUtils.dateFormat(new Date(), "yyyyMMddHHmmss");
        int randomNum = get4RandomNum();
        String code = prefix+time+randomNum;
        return code;
    }


    public static int get4RandomNum(){
        java.util.Random random = new java.util.Random();
        return  random.nextInt(9000) + 1000;
    }
}
