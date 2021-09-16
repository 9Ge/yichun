package com.enercomn.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class IntgobinaryUtil {


    public static void main(String[] args) {



//        StringBuffer buf=new StringBuffer();
//        System.out.println(buf.append(binaryToDecimal(1)).append(binaryToDecimal(0)).append(binaryToDecimal(38018)).append(binaryToDecimal(8678)));
//
//        System.out.println(Long.parseLong(buf.toString(), 2));
//        Double a=2.5;
//        System.err.println(Long.toBinaryString(Double.doubleToLongBits(a)));
//
//        System.out.println(cat(new BigInteger("1"),new BigInteger("0"),new BigInteger("38018"),new BigInteger("8678")));
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("A","1");
//        dataMap.put("B","0");
//        dataMap.put("C","38018");
//        dataMap.put("D","8678");
//        System.out.println(CommUtil.convertToCode("(A*281474976710656+B*4294967296+C*65536+D)/1000", dataMap));
  //      System.out.println(getValueByBit(4,1));
        String data = "201314";

        String s = IntgobinaryUtil.binaryTo16Decimal(Long.parseLong(data));
        log.info("s :" + s);
        data = s.substring(0,8);
        // 11000100 1001100010
        log.info("data :" + data);
        data = StringUtils.getString(Integer.parseInt(data, 2));
        log.info(" data: "+data);
         data = "201314";
         s = IntgobinaryUtil.binaryTo16Decimal(Long.parseLong(data));
        log.info("s :" + s);
        data = s.substring(8,16);
        log.info("data :" + data);
        data = StringUtils.getString(Integer.parseInt(data, 2));
        log.info(" data: "+data);
    }

    public static String binaryToDecimal(long n) {
        return String.format("%032d",Long.valueOf(Long.toBinaryString(n)));
    }

    public static String binaryTo16Decimal(long n) {
        return String.format("%016d",Long.valueOf(Long.toBinaryString(n)));
    }

    public static String getValueByBit(long n,int bit) {
        String s = binaryToDecimal(n);
        //转换顺序 从右往左
        int realBit = s.length() - bit;
        return StringUtils.getString(s.charAt(realBit));
    }



    public static BigInteger cat(BigInteger a,BigInteger b,BigInteger c,BigInteger d) {
        return new BigInteger("281474976710656").multiply(a).add(new BigInteger("4294967296").multiply(b).add(new BigInteger("65536").multiply(c)).add(d));
    }

    public static double bin2DecXiao(String binXStr) {
        double decX = 0.0; //位数

        int k = 0;

        for (int i = 0; i < binXStr.length(); i++) {
            int exp = binXStr.charAt(i) - '0';

            exp = -(i + 1) * exp;

            if (exp != 0) {
                decX += Math.pow(2, exp);

            }

        }

       log.info("二进制小数为;" + binXStr + "。\r\n其对应的十进制为：" + decX);
        return decX;

    }
}
