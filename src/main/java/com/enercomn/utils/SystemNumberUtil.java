package com.enercomn.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wbk on 2018-06-04.
 */
public class SystemNumberUtil {

    /**
     * 功能说明：获取系统单号,年月日时分秒毫秒+3位随机数
     *
     * @return
     */
    public static String getNextSystemNo() {

        String nextOrderNo;
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssS");
        Date now = new Date();
        String str = format1.format(now);
        int i=(int)(Math.random()*900)+100;
        nextOrderNo = str + i;
        return nextOrderNo;
    }

//    public static void main(String[] args) {
//        System.out.println(getNextSystemNo());
//    }

    /**
     * 功能说明：生成推荐码,9位随机数(数字+字母)
     *
     * @return
     */
    public static String getRecommendCode() {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < 9; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 65;
                //int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 功能说明：生成一定位数的随机数字字符串，可作为上传文件的文件名
     *
     * @return
     */
    public static String getRandomNum(int count) {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            int d = r.nextInt(10);
            sb.append(str.charAt(d));
        }
        return sb.toString();
    }
}
