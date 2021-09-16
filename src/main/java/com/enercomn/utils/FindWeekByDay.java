package com.enercomn.utils;

import lombok.extern.log4j.Log4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 查询当前时间一周起止时间
 * lyh
 */
@Log4j
public class FindWeekByDay {

    /*public static void main(String[] args) throws ParseException {
        //System.out.println(FindWeekByDay.getMonthTime("2020-02-10 00:00:00"));
        //System.out.println(FindWeekByDay.getDayTime("2020-02-10 00:00:00"));
        //Map<String,String> map = new HashMap();
//        Calendar calendar = Calendar.getInstance(Locale.CHINA);
//
//
//        calendar.set(calendar.get(Calendar.YEAR), 0, calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//
//        map.put("startTime",calendar.getTime().toString());
//        calendar.add(Calendar.YEAR, 1);
//        map.put("stopTime",calendar.getTime().toString());
//
//        System.out.println(map);



//        Map<String,String> map = new HashMap();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse("2020-07-01 00:00:00");
//        //转化为毫秒
//        long timeOne = date.getTime();
//
//        Calendar calendar = Calendar.getInstance(Locale.CHINA);// 获取当前时区
//        calendar.setTimeInMillis(timeOne);
//        calendar.set(calendar.get(Calendar.YEAR), 0, calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//        String startMonth= format.format(calendar.getTime());
//
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));// 获取当前月最后一天
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
//        String endMonth= format.format(calendar.getTime());
//        map.put("startYear",startMonth);
//        map.put("endYear",endMonth);
//        System.out.println(map);
    }*/


    /**
     * 当前时间所在一周的周一和周日时间
     * @param time 当前时间
     * @return 返回map集合
     */
    public static Map<String, String> getWeekDate(String time) throws ParseException {
        Map<String, String> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(time);
        //转化为毫秒
        long timeOne = date.getTime();
        //选择时区
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.setTimeInMillis(timeOne);//当前时间

        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        String mondayDate =format.format(cld.getTime());//开始时间
        //log.info(mondayDate);

        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        String sundayDate=format.format(cld.getTime());//结束时间
        //log.info(sundayDate);

        map.put("mondayDate", mondayDate);
        map.put("sundayDate", sundayDate);
        return map;
    }

    /**
     * 获取年开始时间 结束时间
     * @param timeStamp 年选择时间
     * @return 返回当年开始时间 和结束时间
     */
    public static Map<String, String> getYearTime(String timeStamp) throws ParseException {

        Map<String, String> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(timeStamp);
        //转化为毫秒
        long timeOne = date.getTime();

        Calendar calendar = Calendar.getInstance(Locale.CHINA);// 获取当前时区
        calendar.setTimeInMillis(timeOne);
        calendar.set(calendar.get(Calendar.YEAR), 0, calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String startMonth= format.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        String endMonth= format.format(calendar.getTime());
        map.put("startYear",startMonth);
        map.put("endYear",endMonth);


        return map;
    }

    /**
     * 获取当月开始时间
     * @param timeStamp 当月选择时间
     * @return 返回当月开始时间 和结束时间
     */
    public static Map<String, String> getMonthTime(String timeStamp) throws ParseException {

        Map<String, String> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(timeStamp);
        //转化为毫秒
        long timeOne = date.getTime();

        Calendar calendar = Calendar.getInstance(Locale.CHINA);// 获取当前时区
        calendar.setTimeInMillis(timeOne);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String startMonth= format.format(calendar.getTime());


        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        String endMonth= format.format(calendar.getTime());
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);


        return map;
    }


    /**
     * 获取当天开始时间和结束时间
     * @param timeStamp 当天选择时间
     * @return 返回当天开始时间 和结束时间
     */
    public static Map<String, String> getDayTime(String timeStamp) throws ParseException {

        Map<String, String> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(timeStamp);
        //转化为毫秒
        long timeOne = date.getTime();

        Calendar calendar = Calendar.getInstance(Locale.CHINA);// 获取当前时区
        calendar.setTimeInMillis(timeOne);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        String startDay= format.format(calendar.getTime());


        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        String endDay= format.format(calendar.getTime());
        map.put("startDay",startDay);
        map.put("endDay",endDay);


        return map;
    }

}
