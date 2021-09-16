package com.enercomn.utils;


/**
 * @author Oliver
 * @description 验证信息工具类
 * @date   2019/6/13 13:04
 * @version 1.0
 */
public class ValidUtils {

    /**
     * 必填项返回信息
     * */
    public static final String NOT_BLANK_MESSAGE = "必填项不能为空";

    /**
     * 身份证正则验证
     * */
    public static final String PATTERN_IDENTITY_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";

    /**
     *身份证返回信息
     * */
    public static final String PATTERN_IDENTITY_CARD_MESSAGE = "身份证号格式有误";

    /**
     * 电子邮箱正则验证
     * */
    public static final String PATTERN_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 电子邮箱返回信息
     * */
    public static final String PATTERN_EMAIL_MESSAGE = "邮箱格式有误";

}
