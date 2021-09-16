package com.enercomn.common;

public class Constants {

    /**
     *  天康json类型静态
     */
    public final static Integer MQTT_TK = 0;

    /**
     *  讯饶json类型静态
     */
    public final static Integer MQTT_XR = 1;

    /**
     *  卓兰json类型静态
     */
    public final static Integer MQTT_ZL = 2;

    /**
     * MQTT获取客户端状态地址
     */
    public final static String GET_MQTT_CLIENTS_URL = "api/v3/clients/?=";

    /**
     * MQTT获取客户端状态 用户名
     */
    public final static String GET_MQTT_CLIENTS_USER = "admin";

    /**
     * MQTT获取客户端状态 密码
     */
    public final static String GET_MQTT_CLIENTS_PWD = "public";

    /**
     *  状态不在线
     */
    public final static Integer COLLECTOR_OFFLINE = 0;

    /**
     *  状态在线
     */
    public final static Integer COLLECTOR_ONLINE = 1;

    /**
     *  钉钉群报警推送urlKey
     */
    public final static String DINGTALK_URLKEY = "dingtalkurl";

    /**
     *  报警状态 启用
     */
    public final static String ALERM_ON = "0";


}
